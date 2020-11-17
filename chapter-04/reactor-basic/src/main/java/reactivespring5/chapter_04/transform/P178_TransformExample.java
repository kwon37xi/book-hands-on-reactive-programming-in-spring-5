package reactivespring5.chapter_04.transform;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.function.Function;

/**
 * transform 은 구독을 여러번 해도 변환을 한 번만한다.
 */
@Slf4j
public class P178_TransformExample {
    public static void main(String[] args) {

        // 재사용 가능한 Flux 변환 함수
        Function<Flux<String>, Flux<String>> logUserInfo = (Flux<String> stream) -> {
            log.info("변환 함수 호출됐다!!!");
            return stream
                .index() // -> Tuple2 index 번호화 스트림 값으로 이루어짐.
                .doOnNext((Tuple2<Long, String> tp) -> log.info("TRANSFORM [{}] User: {}", tp.getT1(), tp.getT2()))
                .map(Tuple2::getT2);
        }; // 다시 String으로.

        Flux.range(1000, 3)
            .map(i -> "user-" + i)
            .transform(logUserInfo)
            .subscribe(e -> log.info("onNext: {}", e));

        // 또 다른 Flux 에 또 적용
        log.info("=".repeat(150));

        Flux<String> numberCounter = Flux.just("하나", "둘", "셋", "넷", "다섯")
            .transform(logUserInfo);

        numberCounter.subscribe(e -> log.info("숫자세기 첫번째 onNext: {}", e));
        numberCounter.subscribe(e -> log.info("숫자세기 두번째 onNext: {}", e));
    }
}
