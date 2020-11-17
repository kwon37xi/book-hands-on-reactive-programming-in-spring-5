package reactivespring5.chapter_04.coldhot;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * cache() 로 쉽게 캐시 구현 가능
 * - 내부적으로 ConnectableFlux 사용.
 * - 데이터양, 캐시 항목의 만료시간 조정가능.
 */
@Slf4j
public class P175_FluxCacheExample {

    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> source = Flux.range(0, 2)
            .doOnSubscribe(s -> {
                log.info("Cold Publisher 에 신규 구독 생성");
            });

        Flux<Integer> cachedSource = source.cache(Duration.ofSeconds(1)); // TTL 1초

        cachedSource.subscribe(e -> log.info("[S 1] onNext: {}", e));
        cachedSource.subscribe(e -> log.info("[S 2] onNext: {}", e));

        // 1.2 초 지연
        Thread.sleep(1200);

        // 캐시 항목 만료시간이 1초 이므로, 1.2초가 지난뒤의 구독은 캐시가 아닌 신규 구독 생성을 통해 처리된다.
        // 어쨌든 앞선 구독자들과 동일한 데이터를 받기는 한다.
        cachedSource.subscribe(e -> log.info("[S 3] onNext: {}", e));
    }
}
