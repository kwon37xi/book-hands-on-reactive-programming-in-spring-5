package reactivespring5.chatper_01.completion_stage;

import reactivespring5.chatper_01.common.Input;
import reactivespring5.chatper_01.common.Output;

import java.util.concurrent.CompletionStage;

public interface CompletionStageShoppingCardService {
    CompletionStage<Output> calculate(Input value);

}
