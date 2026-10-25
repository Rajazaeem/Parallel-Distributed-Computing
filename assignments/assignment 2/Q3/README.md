# Q3 — Async Telemetry Engine

## Objective
Run concurrent worker threads using the two standard Java approaches — implementing `Runnable` and extending `Thread` — alongside the main JVM thread.

## Files
- `LogProcessorTask.java` — implements `Runnable`; `run()` loops 5 times, printing the iteration and `Thread.currentThread().getName()`, pausing 1000 ms with `Thread.sleep(1000)`.
- `TelemetryThread.java` — extends `Thread`; overrides `run()` to log CPU/memory health 5 times, pausing 800 ms each iteration.
- `AsyncWebEngineApp.java` — `main()` prints the default thread name (main), names the workers Telemetry-Worker and Log-Worker, and starts both with `start()`.

## Java constructs used
- **`Runnable` implementation** — `LogProcessorTask` is passed into a `Thread` object.
- **Extending `Thread`** — `TelemetryThread` overrides `run()` directly.
- **`start()` vs `run()`** — `start()` launches a new concurrent thread, whereas `run()` would execute sequentially on the caller.
- **`Thread.sleep()` + `InterruptedException`** — the checked exception is handled inside `run()`.

## Run
```
javac *.java
java AsyncWebEngineApp
```

## Sample output
```
Main thread: main
Both worker threads have started.
CPU/memory health check 1 on thread: Telemetry-Worker
Log metric 1 on thread: Log-Worker
... (lines interleave across the two workers)
```

## How the output verifies correctness
"Both worker threads have started" prints before the workers finish, showing `main` did not block. The Telemetry-Worker (800 ms) and Log-Worker (1000 ms) lines interleave, confirming the two threads executed concurrently.
