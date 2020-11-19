package reactivespring5.chapter_04.deepdive;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
public class P196_ThreadLocalProblemShowcaseExample {
    public static void main(String[] args) {
        ThreadLocal<Map<Object, Object>> threadLocal =
            new ThreadLocal<>();
        threadLocal.set(new HashMap<>());


        Flux
            .range(0, 10)
            .doOnNext(k -> {
                log.info("onOnNext for {}", k);
                threadLocal.get().put(k, new Random(k).nextGaussian()); // main thread 에서 ThreadLocal에 값 저장
            })
            .publishOn(Schedulers.parallel())
            .map(k -> {
                log.info("map for k : {}", k);
                return threadLocal.get().get(k); // 여기서는 parallel 쓰레드로 변경되면서 threadLocal에 아무 값도 없음.
            })
            .blockLast();
    }
    /*
    Exception in thread "main" java.lang.NullPointerException
	at reactivespring5.chapter_04.deepdive.P196_ThreadLocalProblemShowcaseExample.lambda$main$1(P196_ThreadLocalProblemShowcaseExample.java:24)
	at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:107)
	at reactor.core.publisher.FluxPublishOn$PublishOnSubscriber.runAsync(FluxPublishOn.java:439)
	at reactor.core.publisher.FluxPublishOn$PublishOnSubscriber.run(FluxPublishOn.java:526)
	at reactor.core.scheduler.WorkerTask.call(WorkerTask.java:84)
	at reactor.core.scheduler.WorkerTask.call(WorkerTask.java:37)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)
	Suppressed: java.lang.Exception: #block terminated with an error
		at reactor.core.publisher.BlockingSingleSubscriber.blockingGet(BlockingSingleSubscriber.java:99)
		at reactor.core.publisher.Flux.blockLast(Flux.java:2497)
		at reactivespring5.chapter_04.deepdive.P196_ThreadLocalProblemShowcaseExample.main(P196_ThreadLocalProblemShowcaseExample.java:25)
     */
}
