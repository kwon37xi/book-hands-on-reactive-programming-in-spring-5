package reactivespring5.chapter_02.rxjava_basic;

import rx.Observable;

public class ZipOperator {
    public static void main(String[] args) {
        Observable.zip(
            Observable.just("A", "B", "C"),
            Observable.just("1", "2", "3"),
            (x, y) -> x + y
            ).forEach(System.out::println);
    }
}
