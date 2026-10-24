# Q6 — Inventory Catalog Concurrency Benchmark (Marks 5)

Coarse-grained (synchronizedMap) vs fine-grained (ConcurrentHashMap) locking.

## Required files
- InventoryCatalogManager.java — shared catalog (String productId, Integer stockCount); benchmarkSynchronizedMap() wraps HashMap with Collections.synchronizedMap(); benchmarkConcurrentMap() uses ConcurrentHashMap; each spawns 10 worker threads doing 50,000 concurrent lookup/update ops; print throughput (ops/sec) and execution time for both.

Note: an earlier variant exists in the repo's Task6/ folder (ConcurrentStudentMap / SynchronizedStudentMap).

Status: TODO
