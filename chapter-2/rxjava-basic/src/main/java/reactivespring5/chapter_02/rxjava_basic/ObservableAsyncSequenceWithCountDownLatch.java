package reactivespring5.chapter_02.rxjava_basic;

import rx.Observable;
import rx.Subscription;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ObservableAsyncSequenceWithCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch externalSignal = new CountDownLatch(3);

        Subscription subscription = Observable
            .interval(100, TimeUnit.MILLISECONDS)
            .subscribe(System.out::println);

        externalSignal.await(900, TimeUnit.MILLISECONDS);
        subscription.unsubscribe();
    }
}
