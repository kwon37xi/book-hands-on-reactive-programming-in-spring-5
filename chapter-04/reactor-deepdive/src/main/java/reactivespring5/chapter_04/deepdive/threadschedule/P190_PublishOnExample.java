package reactivespring5.chapter_04.deepdive.threadschedule;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * publishOn() 은 publishOn 시점 이전과 이후를 동시에 실행할 수 있게 해준다.
 * publishOn() 이후의 실행을 지정된 워커로 이동시킬 수 있다.(이 워커는 쓰레드가 될 수도 있다.)
 */
@Slf4j
public class P190_PublishOnExample {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Scheduler scheduler = Schedulers.elastic(); // unbounded thread pool

        CountDownLatch countDownLatch = new CountDownLatch(90);

        Flux.range(0, 100)
            .map(String::valueOf)
            .filter(s -> {
                log.info("Running filter for {}", s);
                try {
                    // publishOn 상단에서 지연이 발생하는 것을 시뮬레이션해야만
                    // publishOn을 사용한 동시 처리가 느껴짐.
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return s.length() > 1;
            })
            // publishOn 위 지점까지 main 쓰레드에서 이벤트 발행을 순차적으로 모두 완료한다.
//            .publishOn(scheduler) // *주석처리 해볼 것.*
            // publishOn 이하부분은 위에서 받은 이벤트 순서를 모두 지키면서 실행되지만, 해당 값들은 이미
            // 어딘가에 순서대로 queue 로 저장돼 있다. 비록 calculateHash가 50ms 씩 sleep을 해도,
            // 상우 이벤트 발행은 모두 그에 무관하게 진행되고,
            // 그 이후 느린 처리는 scheduler 에 의해 순차적으로 순서대로 관리되면서 실행된다.
            .map(P190_PublishOnExample::calculateHash)
            .map(P190_PublishOnExample::onBusinessLogic)
            .subscribe(result -> {
                log.info("subscriber : {}", result);
                countDownLatch.countDown();
            });

        countDownLatch.await();
        log.info("### the END - {} ms ###", System.currentTimeMillis() - start);
    }

    private static String calculateHash(String s) {
        try {
            log.info("calculateHash for {} sleep 50ms", s);
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hash for " + s;
    }

    private static String onBusinessLogic(String s) {
        log.info("business logic for {}", s);
        return "business logic for " + s;
    }
}
