import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        int N = 10000;
        double[] assets = new double[N];

        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            assets[i] = 1 + rand.nextDouble() * 999;
        }

        // Keep the original values for a fair serial comparison.
        double[] originalAssets = assets.clone();

        ForkJoinPool pool = new ForkJoinPool();

        try {
            long startP = System.nanoTime();
            double totalRisk =
                    pool.invoke(new ParallelRiskCalculator(assets, 0, N));
            long endP = System.nanoTime();
            double parallelTimeMs = (endP - startP) / 1_000_000.0;

            System.out.println("Parallel risk sum: " + totalRisk);
            System.out.println("Parallel time: " + parallelTimeMs + " ms");

            long startS = System.nanoTime();
            double serialRisk = 0.0;
            for (double value : originalAssets) {
                serialRisk += value;
            }
            long endS = System.nanoTime();
            double serialTimeMs = (endS - startS) / 1_000_000.0;

            System.out.println("Serial risk sum: " + serialRisk);
            System.out.println("Serial time: " + serialTimeMs + " ms");

            pool.invoke(new PortfolioThresholdTask(assets, 0, N, 800));
            System.out.println("Stress test penalty applied to assets above 800.");
        } finally {
            pool.shutdown();
        }
    }
}