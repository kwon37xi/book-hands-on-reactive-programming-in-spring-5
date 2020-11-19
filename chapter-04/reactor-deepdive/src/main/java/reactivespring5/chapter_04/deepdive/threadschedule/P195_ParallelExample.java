package reactivespring5.chapter_04.deepdive.threadschedule;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class P195_ParallelExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(9900);
        Flux.range(0, 10000)
            .parallel() // 작업이 여러 쓰레드로 분배된다.
            .runOn(Schedulers.parallel())
            .map(i -> {
                log.info("map : {}", i);
                return String.valueOf(i);
            })
            .filter(s -> {
                log.info("filter : {}", s);
                return s.length() > 2;
            })
            .subscribe(s -> {
                log.info("subscribe : {}", s);
                countDownLatch.countDown();
            });

        countDownLatch.await();
        log.info("### the END ###");
    }
}
