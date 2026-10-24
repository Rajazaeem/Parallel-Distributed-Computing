public class FIXOrderEvent extends TradeEvent {
    private String clOrdId;

    public FIXOrderEvent(String senderId, long timestamp, double basePrice, String clOrdId) {
        super(senderId, timestamp, basePrice);
        this.clOrdId = clOrdId;
    }

    @Override
    public void dispatch() {
        double fee = basePrice * 0.0015;
        double total = basePrice + fee;
        System.out.println("Order ID: " + clOrdId + ", Base price: " + basePrice + ", Fee: " + fee + ", Total: " + total);
    }
}