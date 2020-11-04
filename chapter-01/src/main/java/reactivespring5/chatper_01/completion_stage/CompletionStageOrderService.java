package reactivespring5.chatper_01.completion_stage;

import reactivespring5.chatper_01.common.Input;

public class CompletionStageOrderService {
    private final CompletionStageShoppingCardService shoppingCardService;

    public CompletionStageOrderService(CompletionStageShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
    }

    void process() {
        Input input = new Input();

        shoppingCardService.calculate(input)
            .thenAccept(v -> System.out.println(shoppingCardService.getClass().getSimpleName()));

        System.out.println(shoppingCardService.getClass().getSimpleName() + " calculate called");
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        CompletionStageOrderService orderService1 = new CompletionStageOrderService(new CompletionStageShoppingCardServiceImpl());

        //  " calculate called" 가 먼저 호출되고 나중에 thenAccept 의 내용이 출력된다.
        orderService1.process();
        orderService1.process();

        System.out.println("Total elapsed time in millis is : " + (System.currentTimeMillis() - start));

        Thread.sleep(1000);
    }
}
