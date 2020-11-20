package reactivespring5.chapter_04.deepdive.context;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * 현재 Context 를 수정할 수 있는 유일한 연산자는 subscriberContext 이다.
 * 액세스 가능한 Context 객체가 스트림의 다른 지점에서는 동일한 객체가 아닐 수 있다는 것을 의미한다.
 */
@Slf4j
public class P199_ReactiveContextSwitchingExample {
    public static void main(String[] args) {
        new P199_ReactiveContextSwitchingExample()
            .showcaseContext();
    }

    public void showcaseContext() {
        printCurrentContext("top")
            .subscriberContext(Context.of("top", "context"))
            .flatMap((Context __) -> printCurrentContext("middle"))
            .subscriberContext(Context.of("middle", "context"))
            .flatMap(__ -> printCurrentContext("bottom"))
            .subscriberContext(Context.of("bottom", "context"))
            .flatMap(__ -> printCurrentContext("initial"))
            .block();
    }

    void print(String id, Context context) {
        System.out.println(id + " {");
        System.out.print("  ");
        System.out.println(context);
        System.out.println("}");
        System.out.println();
    }

    Mono<Context> printCurrentContext(String id) {
        return Mono
            .subscriberContext()
            .doOnNext(context -> print(id, context));
    }
}
