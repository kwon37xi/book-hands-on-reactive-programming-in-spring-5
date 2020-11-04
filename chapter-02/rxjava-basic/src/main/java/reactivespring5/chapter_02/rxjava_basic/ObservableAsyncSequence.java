package reactivespring5.chapter_02.rxjava_basic;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableAsyncSequence {
    public static void main(String[] args) throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe(e -> System.out.println("Received: " + e));
        Thread.sleep(5000); // 이게 없으면 그냥 종료됨.
    }
}
