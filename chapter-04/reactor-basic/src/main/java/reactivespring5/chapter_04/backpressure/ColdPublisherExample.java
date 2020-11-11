package reactivespring5.chapter_04.backpressure;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * 콜드 퍼블리셔
 */
public class ColdPublisherExample {
    public static void main(String[] args) {
        Flux<String> coldPublisher = Flux.defer(() -> {
            System.out.println("신규 아이템 생성중");

            return Flux.just(UUID.randomUUID().toString());
        });

        System.out.println("아직 까지는 아무 데이터도 생성되지 않음.");
        coldPublisher.subscribe(e -> System.out.printf("onNext: %s%n", e));
        coldPublisher.subscribe(e -> System.out.printf("onNext: %s%n", e));

        System.out.println("두 구독자에 대해 두번 데이터가 생성되었다.");
    }
}
