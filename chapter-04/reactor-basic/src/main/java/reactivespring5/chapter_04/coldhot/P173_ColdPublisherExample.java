package reactivespring5.chapter_04.coldhot;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * 콜드 퍼블리셔
 * - 구독자가 나타날 때마다 해당 구독자에 대해 모든 시퀀스 데이터 생성.
 * - 구독자 없이는 데이터 생성 안된다.
 */
@Slf4j
public class P173_ColdPublisherExample {
    public static void main(String[] args) {
        Flux<String> coldPublisher = Flux.defer(() -> {
            log.info("신규 아이템 생성중");

            return Flux.just(UUID.randomUUID().toString());
        });

        log.info("아직 까지는 아무 데이터도 생성되지 않음.");
        coldPublisher.subscribe(e -> log.info("onNext: {}", e));
        coldPublisher.subscribe(e -> log.info("onNext: {}", e));

        log.info("두 구독자에 대해 두번 데이터가 생성되었다.");
    }
}
