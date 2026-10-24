# Q4 — Fork/Join Portfolio Assessment (Marks 2)

Recursive parallel decomposition with the Fork/Join framework.

## Required files
- ParallelRiskCalculator.java — extends RecursiveTask<Double>; if segment size < THRESHOLD (1000) sum directly, else split into two sub-tasks, fork() them, join() the results.
- PortfolioThresholdTask.java — extends RecursiveAction; in-place 5% volatility stress-test penalty on asset values above a risk cap.
- ForkJoinPortfolioApp.java — main(); generate a 10,000-element synthetic asset array; create a ForkJoinPool; execute both tasks; print total processing time and risk metrics (parallel vs serial).

Status: TODO
