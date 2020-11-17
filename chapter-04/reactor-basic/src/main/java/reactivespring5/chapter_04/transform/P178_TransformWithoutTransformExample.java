package reactivespring5.chapter_04.transform;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.function.Function;

@Slf4j
public class P178_TransformWithoutTransformExample {
    public static void main(String[] args) {
        Flux.range(1000, 3)
            .map(i -> "user-" + i)
            .index()
            .doOnNext(tp -> log.info("TRANSFORM [{}] User : {}", tp.getT1(), tp.getT2()))
            .map(Tuple2::getT2)
            .subscribe(e -> log.info("onNext: {}", e));
    }
}
