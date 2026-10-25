import java.util.Random;

public class SensorGridMonitorApp {

    public static void main(String[] args) throws InterruptedException {

        int N = 50000;
        double[] data = new double[N];

        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            data[i] = rand.nextDouble() * 100;
        }

        int parts = 4;
        int seg = N / parts;

        ParallelMinSearch[] minWorkers = new ParallelMinSearch[parts];
        ParallelMaxSearch[] maxWorkers = new ParallelMaxSearch[parts];
        Thread[] threads = new Thread[parts * 2];

        long start = System.nanoTime();

        for (int p = 0; p < parts; p++) {

            int lo = p * seg;
            int hi = (p == parts - 1) ? N : lo + seg;

            minWorkers[p] = new ParallelMinSearch(data, lo, hi);
            maxWorkers[p] = new ParallelMaxSearch(data, lo, hi);

            threads[p] = new Thread(minWorkers[p], "Min-Worker-" + p);
            threads[parts + p] = new Thread(maxWorkers[p], "Max-Worker-" + p);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        long end = System.nanoTime();

        double globalMin = minWorkers[0].getResult();

        for (int p = 1; p < parts; p++) {
            globalMin = Math.min(globalMin, minWorkers[p].getResult());
        }

        double globalMax = maxWorkers[0].getResult();

        for (int p = 1; p < parts; p++) {
            globalMax = Math.max(globalMax, maxWorkers[p].getResult());
        }

        double timeMs = (end - start) / 1_000_000.0;

        System.out.println("Minimum sensor value: " + globalMin);
        System.out.println("Maximum sensor value: " + globalMax);
        System.out.println("Execution time: " + timeMs + " ms");
    }
}
