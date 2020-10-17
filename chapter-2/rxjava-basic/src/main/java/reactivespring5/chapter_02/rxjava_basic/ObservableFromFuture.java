package reactivespring5.chapter_02.rxjava_basic;

import rx.Observable;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ObservableFromFuture {
    public static void main(String[] args) {
        Observable<String> hello = Observable.fromCallable(() -> "Hello ");

        Future<String> future = Executors.newCachedThreadPool().submit(() -> "World");

        Observable<String> world = Observable.from(future);

        Observable.concat(hello, world, Observable.just("!"))
            .forEach(System.out::print);
    }
}
