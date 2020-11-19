package reactivespring5.chapter_04.deepdive.threadschedule;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class P197_ReactorContextExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(0, 10)
            .flatMap((Integer k) -> Mono.subscriberContext()
                .doOnNext(context -> {

                    Map<Object, Object> map = context.get("randoms");
                    double value = new Random(k).nextGaussian();
                    map.put(k, value);
                    log.info("put for context {} -> {}", k, value);
                })
                .thenReturn(k)
            )
            .publishOn(Schedulers.parallel())
            .flatMap((Integer k) ->
                Mono.subscriberContext()
                    .map(context -> {
                        log.info("get from context key {}", k);
                        Map<Object, Object> map = context.get("randoms");
                        return map.get(k);
                    }))
            .subscriberContext(context -> context.put("randoms", new HashMap<>()))
            .subscribe(o -> {
                log.info("subscribe result : {}", o);
            });
        TimeUnit.SECONDS.sleep(5);
    }
}
