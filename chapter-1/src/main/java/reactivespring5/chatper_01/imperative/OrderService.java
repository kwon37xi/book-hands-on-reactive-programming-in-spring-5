package reactivespring5.chatper_01.imperative;

public class OrderService {
    private final ShoppingCardService scService;

    public OrderService(ShoppingCardService scService) {
        this.scService = scService;
    }

    void process() {
        Input input = new Input();
        Output output =scService.calculate(input);

        System.out.println(scService.getClass().getSimpleName() + " execution completed");
    }

    /**
     * Blocking 호출을 하기 때문에 각 <code>process()</code>에 걸리는 1초씩, 총 2초가 걸린다.
     * <pre>
     * Total elapsed time in millis is : 2022
     * </pre>
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        new OrderService(new BlockingShoppingCardService()).process();
        new OrderService(new BlockingShoppingCardService()).process();

        System.out.println("Total elapsed time in millis is : " + (System.currentTimeMillis() - start));
    }
}
