package reactivespring5.chapter_04.reactor_basic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class FluxMonoBasic {
    public static void main(String[] args) throws InterruptedException {
        Flux.just("Hello", "World").subscribe(s -> {
            System.out.println("Flux just : " + s);
        });

        Flux.fromArray(new Integer[]{1, 2, 3})
            .subscribe(i -> {
                System.out.println("Flux fromArray : " + i);
            });

        Flux.fromIterable(Arrays.asList(9, 8, 7))
            .subscribe(i -> {
                System.out.println("Flux fromIterable : " + i);
            });

        Flux.range(2010, 9) // 2010 부터 9개
            .subscribe(i -> {
                System.out.println("Flux range : " + i);
            });

        Mono.just("One")
            .subscribe(s -> System.out.println("Mono just : " + s));

        Mono.justOrEmpty(null)
            .subscribe(o -> {
                System.out.println("Mono justOrEmpty(null) : " + o); // 호출 안된다. empty Mono 이기 때문
            });

        Mono.justOrEmpty(Optional.empty())
            .subscribe(o -> System.out.println("Mono justOrEmpty(empty Optional) : " + o)); // 호출 안된다. Optional.empty() 는 empty Mono를 만든다.

        // Callable과 subscribe 모두 main 쓰레드. Scheduler 를 사용해야 쓰레드 변경될듯.
        Mono.fromCallable(() -> {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Callable 내부 쓰레드 : " + Thread.currentThread().getName());
            return "fake HTTP request result.";
        }).subscribe(s -> {
            System.out.println("Mono fromCallable : " + s + ", subscribe 쓰레드 : " + Thread.currentThread().getName());
        });
    }
}
