package reactivespring5.chapter_04.backpressure;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class ColdPublisherToHotPublisherWithShareExample {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> source = Flux.range(0, 5)
            .delayElements(Duration.ofMillis(100))
            .doOnSubscribe(s -> System.out.println("콜드 퍼블리셔에 신규 구독 발생."));

        Flux<Integer> cachedSource = source.share();

        cachedSource.subscribe(e -> System.out.printf("[S 1] onNext: %s%n", e));
        Thread.sleep(400);
        cachedSource.subscribe(e -> System.out.printf("[S 2] onNext: %s%n", e));

        Thread.sleep(2000);
    }
}
