# Q6 — Inventory Catalog Concurrency Benchmark

## Objective
Measure how coarse-grained locking (`Collections.synchronizedMap`) compares with fine-grained locking (`ConcurrentHashMap`) under heavy multi-threaded access.

## Files
- `InventoryCatalogManager.java` — a shared product catalog and two benchmark methods: `benchmarkSynchronizedMap()` wraps a `HashMap` with `Collections.synchronizedMap()`, and `benchmarkConcurrentMap()` uses a `ConcurrentHashMap`. Each spawns 10 worker threads performing 50,000 lookup/update operations and reports time and throughput.

## Java constructs used
- **`Collections.synchronizedMap(HashMap)`** — one lock guards the whole map (coarse-grained).
- **`ConcurrentHashMap`** — bucket/bin-level locking plus CAS (fine-grained).
- **Thread pool pattern** — 10 worker threads created and joined.
- **Lambda `Runnable`** — each worker is defined as `() -> { ... }`.
- **Throughput metric** — total operations divided by elapsed seconds.

## Run
```
javac *.java
java InventoryCatalogManager
```

## Sample output
```
synchronizedMap (coarse full-table lock)
  Time: 170.00 ms  |  Throughput: 2900000 ops/sec
ConcurrentHashMap (fine-grained bucket lock)
  Time: 75.00 ms  |  Throughput: 6700000 ops/sec
```

## How the output verifies correctness
`ConcurrentHashMap` shows roughly double the throughput of `synchronizedMap`. The synchronized map serializes every access on a single lock (full-table contention), while the concurrent map lets threads operate on different buckets simultaneously, reducing contention.
