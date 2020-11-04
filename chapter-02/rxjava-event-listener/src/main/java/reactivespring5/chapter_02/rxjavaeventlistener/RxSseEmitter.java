package reactivespring5.chapter_02.rxjavaeventlistener;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Subscriber;

import java.io.IOException;

public class RxSseEmitter extends SseEmitter {
    static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;
    private final Subscriber<Temperature> subscriber;

    RxSseEmitter() {
        super(SSE_SESSION_TIMEOUT);

        this.subscriber = new Subscriber<Temperature>() {

            @Override
            public void onNext(Temperature temperature) {
                try {
                    RxSseEmitter.this.send(temperature);
                } catch (IOException e) {
                    unsubscribe();
                }
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }
        };

        onCompletion(subscriber::unsubscribe);
        onTimeout(subscriber::unsubscribe);
    }

    Subscriber<Temperature> getSubscriber() {
        return subscriber;
    }
}
