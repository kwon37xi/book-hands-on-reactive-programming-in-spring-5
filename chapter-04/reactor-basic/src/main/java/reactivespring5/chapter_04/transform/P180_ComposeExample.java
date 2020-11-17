package reactivespring5.chapter_04.transform;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.function.Function;

/**
 * compose()는 구독이 일어날 때마다 스트림 변환을 재수행한다.
 */
@Slf4j
public class P180_ComposeExample {
    public static void main(String[] args) {

        Random random = new Random();

        Function<Flux<String>, Flux<String>> logUserInfo = (stream) -> {
            log.info("변환 함수 호출됨!!!");

            if (random.nextBoolean()) {
                return stream.doOnNext(e -> log.info("[path A] User : {}", e));
            } else {
                return stream.doOnNext(e -> log.info("[path B] User : {}", e));
            }
        };

        Flux<String> publisher = Flux.just("1", "2")
            .transformDeferred(logUserInfo);

        publisher.subscribe();
        publisher.subscribe();
    }
}
