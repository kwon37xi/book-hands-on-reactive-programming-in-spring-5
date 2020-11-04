package reactivespring5.chatper_01.callbacks;

import reactivespring5.chatper_01.common.Input;
import reactivespring5.chatper_01.common.Output;

import java.util.function.Consumer;

public class SyncShoppingCardService implements CallbackShoppingCardService {
    @Override
    public void calculate(Input value, Consumer<Output> c) {
        c.accept(new Output());
    }
}
