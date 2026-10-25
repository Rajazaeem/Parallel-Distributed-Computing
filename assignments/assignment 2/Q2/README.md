# Q2 — Packet Symmetry Auditor

## Objective
Detect whether a network packet payload is bi-directionally symmetric (a palindrome) by comparing a LIFO stack stream against a FIFO queue stream, using custom array-backed data structures (no built-in collections).

## Files
- `TransactionStack.java` — array-backed stack (LIFO) with a fixed capacity and `top` pointer; methods `push(char)`, `pop()`, `peek()`, `isEmpty()`.
- `RequestQueue.java` — array-backed queue (FIFO) with `front`, `rear`, `size` pointers; methods `enqueue(char)`, `dequeue()`, `isEmpty()`.
- `PacketSymmetryAuditor.java` — `main()` pushes each character onto the stack while enqueuing it into the queue, then pops/dequeues together and compares.

## Java constructs used
- **Custom data structures** — stack and queue implemented directly on fixed-size arrays.
- **Encapsulation** — pointers and the backing array are private; access is only through the methods.
- **Algorithm** — a stack reverses the input (LIFO) and a queue preserves it (FIFO); comparing them element by element detects a palindrome.

## Run
```
javac *.java
java PacketSymmetryAuditor
```

## Sample output
```
"racecar" is a palindrome.
```

## How the output verifies correctness
Popping the stack yields the payload reversed and dequeuing the queue yields it in original order. When every popped/dequeued pair matches, the payload reads the same both ways and is reported as a palindrome; any mismatch reports it as not symmetric.
