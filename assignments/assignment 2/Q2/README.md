# Q2 — Packet Symmetry Auditor (Marks 2)

Compare LIFO vs FIFO streams to detect a palindrome (bi-directional symmetry).

## Required files
- TransactionStack.java — custom array-backed stack, fixed capacity, top pointer; push(char c), pop(), peek(), isEmpty().
- RequestQueue.java — custom array-backed queue with front, rear, size pointers; enqueue(char c), dequeue(), isEmpty().
- PacketSymmetryAuditor.java — main(); ingest a packet payload string, push chars onto the stack while enqueuing onto the queue, then pop/dequeue together to verify symmetry; print the verified log.

Status: TODO
