public class TradingEngineApp {
    public static void main(String[] args) {
        TradeEvent[] events = {
                new FIXOrderEvent("BrokerA", 1720000000L, 1000.00, "ORD-001"),
                new ReutersQuoteEvent("Reuters", 1720000001L, 99.50, 99.25, 99.75)
        };

        for (TradeEvent e : events) {
            e.dispatch();
        }
    }
}
