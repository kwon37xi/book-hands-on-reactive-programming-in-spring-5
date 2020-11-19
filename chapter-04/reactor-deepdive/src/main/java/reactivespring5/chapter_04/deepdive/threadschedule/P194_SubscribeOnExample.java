package reactivespring5.chapter_04.deepdive.threadschedule;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

@Slf4j
public class P194_SubscribeOnExample {
    public static void main(String[] args) throws InterruptedException {
        Scheduler publishOnScheduler = Schedulers.fromExecutor(Executors.newSingleThreadExecutor(r -> new Thread(r, "publishOnThread")));
        Scheduler subscribeOnScheduler = Schedulers.fromExecutor(Executors.newSingleThreadExecutor(r -> new Thread(r, "subscribeOnThread")));

        CountDownLatch countDownLatch = new CountDownLatch(1);
        Mono.fromCallable(() -> {
            log.info("callable 호출됨");
            return "안녕 세상아!!";
        })
            .map(s -> {
                log.info("map 실행됨. before publishOn {}", s);
                return "!" + s + "!";
            })
            .publishOn(publishOnScheduler) // 주석처리 해볼 것
            .map(s -> {
                log.info("map 실행됨. after publishOn {}", s);
                return "[" + s + "]";
            })
            .subscribeOn(subscribeOnScheduler) // 주석처리해볼것.
            .subscribe(s -> {
                log.info("최종 subscribe 실행됨 - {}", s);
                countDownLatch.countDown();
            });

        countDownLatch.await();
        log.info("### the END ###");
        publishOnScheduler.dispose();
        subscribeOnScheduler.dispose();
    }
}
