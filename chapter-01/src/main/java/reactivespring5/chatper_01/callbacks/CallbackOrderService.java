package reactivespring5.chatper_01.callbacks;

import reactivespring5.chatper_01.common.Input;

public class CallbackOrderService {
    private final CallbackShoppingCardService scService;

    public CallbackOrderService(CallbackShoppingCardService scService) {
        this.scService = scService;
    }

    void process() {
        Input input = new Input();
        scService.calculate(input, output -> {
            System.out.println(scService.getClass().getSimpleName() + " execution completed");
        });
    }

    /**
     * 비동기 부분은 호출만하고 실제 실행은 뒤늦게 되고, 동기부분은 먼저 호출된 뒤에 Total elapsed가 출력된다.
     * <pre>
     * SyncShoppingCardService execution completed
     * Total elapsed time in millis is : 26
     * AsyncShoppingCardService execution completed
     * AsyncShoppingCardService execution completed
     * </pre>
     */
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        CallbackOrderService orderServiceAsync = new CallbackOrderService(new AsyncShoppingCardService());
        CallbackOrderService orderServiceSync = new CallbackOrderService(new SyncShoppingCardService());

        orderServiceAsync.process();
        orderServiceAsync.process();
        orderServiceSync.process();

        System.out.println("Total elapsed time in millis is : " + (System.currentTimeMillis() - start));

        Thread.sleep(1000);
    }
}
