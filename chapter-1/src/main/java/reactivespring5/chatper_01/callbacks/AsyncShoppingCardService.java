package reactivespring5.chatper_01.callbacks;

import reactivespring5.chatper_01.common.Input;
import reactivespring5.chatper_01.common.Output;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class AsyncShoppingCardService implements CallbackShoppingCardService {
    @Override
    public void calculate(Input value, Consumer<Output> c) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c.accept(new Output());
        }).start();
    }
}
