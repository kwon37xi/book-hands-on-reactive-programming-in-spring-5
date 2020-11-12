package reactivespring5.chapter_04.backpressure;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ConnectableFluxExample {
    public static void main(String[] args) {
        Flux<Integer> source = Flux.range(0, 3)
            .doOnSubscribe(s -> {
                System.out.println("콜드 퍼블리셔에 신규 구독 발생.");
            });

        ConnectableFlux<Integer> conn = source.publish();

        // subscribe 를 두 번 했기 때문에 doOnSubscribe()가 두 번 호출될거 같지만,
        // ConnectableFlux 를 사용하면 한 번만 호출된다.

        // 그러나 각 subscriber 는 데이터를 각자 받는다.
        conn.subscribe(e -> System.out.printf("[Subscriber 1] onNext: %s%n", e));
        conn.subscribe(e -> System.out.printf("[Subscriber 2] onNext: %s%n", e));

        System.out.println("모든 서브스크라이버가 준비됨. connecting...");
        conn.connect();
    }
}
