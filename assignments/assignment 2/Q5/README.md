# Q5 — Sensor Grid Monitor (Marks 2)

Abstract-class-driven parallel min/max reduction across sensor arrays.

## Required files
- SensorSearchEngine.java — abstract parent; protected sensorData[] (double), startIdx, endIdx; abstract processGrid().
- ParallelMinSearch.java — extends SensorSearchEngine; overrides processGrid() to find minimum temperature.
- ParallelMaxSearch.java — extends SensorSearchEngine; overrides processGrid() to find peak vibration.
- SensorGridMonitorApp.java — main(); 50,000-element array partitioned across 4 worker threads; run parallel min and max simultaneously; log critical thresholds and timing.

Status: TODO
