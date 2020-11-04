package reactivespring5.chatper_01.callbacks;

import reactivespring5.chatper_01.common.Input;
import reactivespring5.chatper_01.common.Output;

import java.util.function.Consumer;

public interface CallbackShoppingCardService {
    void calculate(Input value, Consumer<Output> c);
}
