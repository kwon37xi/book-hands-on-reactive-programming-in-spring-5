package reactivespring5.chapter_02.rxjava_basic;


import rx.Observable;
import rx.Subscriber;

public class ObservableBasic {
    public static void main(String[] args) {
        Observable<String> observable = Observable.create(subscriber -> {
            subscriber.onNext("Hello, reactive world!");
            subscriber.onCompleted();
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
                System.out.println("Done!");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println(e);
            }
        };

        observable.subscribe(subscriber);

        // -- just
        Observable.just("1", "2", "3", "4")
            .subscribe(s -> System.out.println("Just print : " + s));

        Observable.from(new String[] { "A", "B", "C"})
            .subscribe(s -> System.out.println("from print : " + s));
    }
}
