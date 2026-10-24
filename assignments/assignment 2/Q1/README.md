# Q1 — Trade Event Dispatcher (Marks 2)

Dynamic dispatch of heterogeneous trade orders via inheritance + method overriding.

## Required files
- TradeEvent.java — superclass; protected senderId (String), timestamp (long), basePrice (double); parameterized constructor; baseline dispatch() logging generic details.
- FIXOrderEvent.java — extends TradeEvent; adds clOrdId (String); calls super(...); overrides dispatch() to execute a stock purchase with a 0.15% exchange transaction fee.
- ReutersQuoteEvent.java — extends TradeEvent; adds bidPrice, askPrice; overrides dispatch() to output bid-ask spread analytics.
- TradingEngineApp.java — main(); instantiate subclass objects using TradeEvent reference variables; call dispatch() on each to demonstrate runtime dynamic method overriding (polymorphism).

Status: TODO
