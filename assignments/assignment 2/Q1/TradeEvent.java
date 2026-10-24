public class TradeEvent {
    // fields shared by every event type - 'protected' so child classes can use them
    protected String senderId;
    protected long timestamp;
    protected double basePrice;

    // constructor: takes values and stores them in the fields
    public TradeEvent(String senderId, long timestamp, double basePrice) {
        this.senderId = senderId; // 'this.senderId' = the field, 'senderId' = the parameter
        this.timestamp = timestamp;
        this.basePrice = basePrice;
    }

    // the method each child will override with its own version
    public void dispatch() {
        System.out.println("[TRADE EVENT] sender=" + senderId + " ts=" + timestamp + " price=" + basePrice);
    }
}