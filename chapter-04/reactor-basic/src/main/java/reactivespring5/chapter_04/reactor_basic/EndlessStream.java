package reactivespring5.chapter_04.reactor_basic;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class EndlessStream {
    public static void main(String[] args) {
        // 무한 스트림이므로 끝나지 않는다.
        Flux.interval(Duration.ofMillis(1))
            .collectList()
            .block();
    }
}
