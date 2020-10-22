package reactivespring5.chatper_01.imperative;

import java.util.concurrent.TimeUnit;

public class BlockingShoppingCardService implements ShoppingCardService {
    @Override
    public Output calculate(Input value) {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Output();
    }
}
