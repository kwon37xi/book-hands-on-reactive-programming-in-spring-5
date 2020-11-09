package reactivespring5.chapter_04.reactor_basic;

import reactor.core.publisher.Flux;

public class FluxRepeat {
    // 무한 스트림을 collection 으로 만들기 때문에 OOM발생한다.
    // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    public static void main(String[] args) {
        Flux.range(1, 100)
            .repeat() // 1..100 을 다시 무한 반복
            .collectList() // Mono<List<Integer>> 반환
            .block(); // 스트림이 무한하므로 제대로 동작 안하고 멈춘다.
    }
}
