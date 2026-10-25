# Q7 — Cache Locality Benchmark

## Objective
Show how the traversal order of a 2D array affects performance because of CPU cache behaviour (spatial locality and cache lines).

## Files
- `CacheLocalityBenchmark.java` — builds a 2000 x 2000 `double` matrix; `traverseRowMajor()` iterates with the row index outer and column index inner, `traverseColumnMajor()` swaps the loops; both are timed with `System.nanoTime()` over 10 iterations and the speedup ratio is computed.

## Java constructs used
- **2D array** — `double[2000][2000]`.
- **Nested loops with swapped order** — the only difference between the two methods is which index is the outer loop.
- **`System.nanoTime()`** — high-resolution timing, averaged over 10 iterations.
- **Speedup ratio** — column-major time divided by row-major time.

## Run
```
javac *.java
java CacheLocalityBenchmark
```

## Sample output
```
Row-major average:    (smaller) ms
Column-major average: (larger) ms
Speedup (row over column): ~Nx
```

## How the output verifies correctness
Row-major traversal is consistently faster. A row of the matrix is contiguous in memory, and the CPU loads ~64-byte cache lines (8 doubles) at a time; walking along a row hits data already in cache (spatial locality), while column-major jumps to a new row each step, causing a cache miss on nearly every access. The speedup ratio quantifies this difference.
