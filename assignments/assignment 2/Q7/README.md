# Q7 — Cache Locality Benchmark (Marks 5)

Row-major vs column-major 2D traversal and CPU cache behaviour.

## Required files
- CacheLocalityBenchmark.java — create a 2000x2000 double matrix; traverseRowMajor() (for i; for j) and traverseColumnMajor() (for j; for i) summing all elements; measure with System.nanoTime() over 10 iterations; output times in ms and the row-major/column-major speedup ratio.

Report must explain how CPU cache lines (~64 bytes) and spatial locality govern the results.

Status: TODO
