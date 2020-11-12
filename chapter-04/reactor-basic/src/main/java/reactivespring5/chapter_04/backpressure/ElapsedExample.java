package reactivespring5.chapter_04.backpressure;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class ElapsedExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(0, 5)
            .delayElements(Duration.ofMillis(100))
            .elapsed()
            .subscribe(e -> System.out.printf("Elapsed %d ms: %d%n", e.getT1(), e.getT2()));
        // 출력된 시간 간격이 정확히 100ms 가 아니다.
        // Java 의 ScheduledExecutorService 를 사용하여 delayElements를 처리하는데,
        // 이 기능 자체가 완벽한 시간 스케줄링을 보장하지 않는다.
        Thread.sleep(5000);
    }
}
