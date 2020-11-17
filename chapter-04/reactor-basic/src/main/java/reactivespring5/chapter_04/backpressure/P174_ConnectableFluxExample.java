package reactivespring5.chapter_04.backpressure;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

/**
 * ConnectableFlux
 * - 가장 수요가 많은 데이터를 생성하고, 다른 모든 가입자가 데이터를 처리할 수 있도록 캐싱.
 */
@Slf4j
public class P174_ConnectableFluxExample {
    public static void main(String[] args) {
        Flux<Integer> source = Flux.range(0, 3)
            .doOnSubscribe(s -> {
                log.info("콜드 퍼블리셔에 신규 구독 발생.");
            });

        ConnectableFlux<Integer> conn = source.publish();

        // subscribe 를 두 번 했기 때문에 doOnSubscribe()가 두 번 호출될거 같지만,
        // ConnectableFlux 를 사용하면 한 번만 호출된다.

        // 그러나 각 subscriber 는 데이터를 각자 동일하게 받는다.
        conn.subscribe(e -> log.info("[Subscriber 1] onNext: {}", e));
        conn.subscribe(e -> log.info("[Subscriber 2] onNext: {}", e));

        log.info("모든 서브스크라이버가 준비됨. connecting...");
        conn.connect();
    }
}
