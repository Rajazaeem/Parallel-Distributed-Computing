# Q5 — Sensor Grid Monitor

## Objective
Perform parallel minimum and maximum reductions over a large sensor array using an abstract base class and manually managed worker threads.

## Files
- `SensorSearchEngine.java` — abstract class implementing `Runnable`; holds protected `sensorData[]`, `startIdx`, `endIdx`, `result`; declares abstract `processGrid()`; `run()` delegates to it.
- `ParallelMinSearch.java` — extends the base class and overrides `processGrid()` to find the minimum of its segment.
- `ParallelMaxSearch.java` — extends the base class and overrides `processGrid()` to find the maximum of its segment.
- `SensorGridMonitorApp.java` — `main()` partitions a 50,000-element array into 4 segments, runs min and max workers concurrently, then reduces the partial results.

## Java constructs used
- **Abstract class + abstract method** — `SensorSearchEngine` cannot be instantiated and forces subclasses to implement `processGrid()`.
- **Method overriding** — min and max searches provide their own logic.
- **`Runnable` + threads** — the abstract class implements `Runnable`, so each worker runs on its own thread; results are stored in `result` because `run()` cannot return a value.
- **Parallel reduction** — the 4 partial results are combined with `Math.min` / `Math.max`.

## Run
```
javac *.java
java SensorGridMonitorApp
```

## Sample output
```
Minimum sensor value: 9.59E-4
Maximum sensor value: 99.9977
Execution time: 15.0 ms
```

## How the output verifies correctness
For 50,000 uniform values in [0, 100) the global minimum is near 0 and the maximum near 100, which the parallel reduction correctly reports. Each worker writes only its own `result` and reads only its own segment, and the main thread combines results after `join()`, so there are no data races.
