# Q4 — Fork/Join Portfolio Assessment

## Objective
Use the Fork/Join framework to compute a portfolio risk sum in parallel (divide and conquer) and to apply an in-place stress-test penalty, then compare against a serial baseline.

## Files
- `ParallelRiskCalculator.java` — extends `RecursiveTask<Double>`; sums a segment directly when below `THRESHOLD = 1000`, otherwise splits in half, `fork()`s the sub-tasks and `join()`s their results.
- `PortfolioThresholdTask.java` — extends `RecursiveAction`; applies a 5% penalty (`value * 0.95`) to every asset above a risk cap, recursing with `invokeAll(...)`.
- `ForkJoinPortfolioApp.java` — `main()` builds a 10,000-element array, runs both tasks on a `ForkJoinPool`, and times parallel vs serial.

## Java constructs used
- **`RecursiveTask<V>`** — returns a value (the risk sum).
- **`RecursiveAction`** — returns nothing (modifies the array in place).
- **`fork()` / `join()` / `invokeAll()`** — schedule and wait for sub-tasks.
- **`ForkJoinPool`** — the work-stealing pool that executes the tasks.
- **`THRESHOLD`** — the base-case cutoff that stops further splitting.

## Run
```
javac *.java
java ForkJoinPortfolioApp
```

## Sample output
```
Parallel risk sum: 4971899.75
Parallel time: 1.51 ms
Serial risk sum: 4971899.75
Serial time: 0.15 ms
Stress test penalty applied to assets above 800.
```

## How the output verifies correctness
The parallel and serial sums match (tiny last-digit differences are floating-point rounding from a different addition order). For only 10,000 elements the serial version is faster because Fork/Join overhead exceeds the benefit at small sizes; parallel speedup appears only for much larger datasets.
