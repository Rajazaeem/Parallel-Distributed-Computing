# Q3 — Async Telemetry Engine (Marks 2)

Concurrent threads via Runnable and via extending Thread.

## Required files
- LogProcessorTask.java — implements Runnable; run() loops 5 times, prints iteration + Thread.currentThread().getName(), Thread.sleep(1000) each iteration.
- TelemetryThread.java — extends Thread; overrides run() looping 5 times, logs CPU/memory health every 800 ms.
- AsyncWebEngineApp.java — main(); print default JVM thread name ('main'); create TelemetryThread and a new Thread(LogProcessorTask); name them 'Telemetry-Worker' and 'Log-Worker'; start() both; log concurrent execution.

Status: TODO
