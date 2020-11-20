package reactivespring5.chapter_04.coldhot;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * share() 를 사용해 Cold Publisher를 Hot Publisher 로 전환 가능함.
 *
 */
@Slf4j
public class P177_ColdPublisherToHotPublisherWithShareExample {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> source = Flux.range(0, 5) // Cold Publisher
            .delayElements(Duration.ofMillis(100)) // 100ms 마다 이벤트 발생.
            .doOnSubscribe(s -> log.info("콜드 퍼블리셔에 신규 구독 발생."));

        Flux<Integer> cachedSource = source.share(); // -> Hot Publisher 로 전환됨

        cachedSource.subscribe(e -> log.info("[S 1] onNext: {}", e));
        Thread.sleep(400); // 이걸 위로 올려도 최초 구독자가 나타날 때까지 publish 안됨. 구독을 해야만 시작됨.
        cachedSource.subscribe(e -> log.info("[S 2] onNext: {}", e)); // 이미 지나간 이벤트는 받지 못함.

        Thread.sleep(2000);
    }
}
