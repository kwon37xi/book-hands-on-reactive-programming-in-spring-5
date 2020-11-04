package reactivespring5.chatper_01.completion_stage;

import reactivespring5.chatper_01.common.Input;
import reactivespring5.chatper_01.common.Output;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class CompletionStageShoppingCardServiceImpl implements CompletionStageShoppingCardService {
    @Override
    public CompletionStage<Output> calculate(Input value) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return new Output();
        });
    }
}
