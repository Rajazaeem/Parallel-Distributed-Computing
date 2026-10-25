# Q1 — Trade Event Dispatcher

## Objective
Demonstrate runtime polymorphism (dynamic method dispatch) in an object-oriented trade-event system built across four separate Java files.

## Files
- `TradeEvent.java` — superclass with protected fields `senderId` (String), `timestamp` (long), `basePrice` (double), a parameterized constructor, and a baseline `dispatch()` method.
- `FIXOrderEvent.java` — extends `TradeEvent`, adds `clOrdId`, calls `super(...)`, overrides `dispatch()` to apply a 0.15% exchange fee.
- `ReutersQuoteEvent.java` — extends `TradeEvent`, adds `bidPrice`/`askPrice`, overrides `dispatch()` to print the bid-ask spread.
- `TradingEngineApp.java` — `main()` stores subclass objects in a `TradeEvent[]` array and calls `dispatch()` on each.

## Java constructs used
- **Inheritance** — both event types extend the common `TradeEvent` superclass.
- **`super(...)`** — each child constructor forwards the shared fields to the parent constructor as its first statement.
- **Method overriding (`@Override`)** — each subclass provides its own `dispatch()`.
- **Polymorphism** — the array is typed as the parent `TradeEvent`, yet each element runs its own overridden `dispatch()` at runtime.

## Run
```
javac *.java
java TradingEngineApp
```

## Sample output
```
Order ID: ORD-001, Base price: 1000.0, Fee: 1.5, Total: 1001.5
Bid price: 99.25, Ask price: 99.75, Spread: 0.5
```

## How the output verifies correctness
One `dispatch()` call yields two different formats: the FIX order applies the 0.15% fee (1000 x 0.0015 = 1.5, total 1001.5) and the Reuters quote prints the spread (99.75 - 99.25 = 0.5). Since each call runs through a `TradeEvent` reference but executes the subclass method, this proves dynamic method dispatch.
