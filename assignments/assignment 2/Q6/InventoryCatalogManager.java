import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Random;

public class InventoryCatalogManager {

    static final int NUM_THREADS = 10;
    static final int OPS_PER_THREAD = 50000;
    static final int NUM_PRODUCTS = 100;

    static long runBenchmark(Map<String, Integer> catalog) throws InterruptedException {

        for (int i = 0; i < NUM_PRODUCTS; i++) {
            catalog.put("P" + i, 0);
        }

        Thread[] workers = new Thread[NUM_THREADS];
        long start = System.nanoTime();

        for (int t = 0; t < NUM_THREADS; t++) {
            workers[t] = new Thread(() -> {
                Random r = new Random();

                for (int i = 0; i < OPS_PER_THREAD; i++) {
                    String key = "P" + r.nextInt(NUM_PRODUCTS);
                    Integer current = catalog.get(key);
                    catalog.put(key, (current == null ? 0 : current) + 1);
                }
            });
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            workers[i].start();
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            workers[i].join();
        }

        long end = System.nanoTime();
        return end - start;
    }

    static void benchmarkSynchronizedMap() throws InterruptedException {
        Map<String, Integer> catalog =
                Collections.synchronizedMap(new HashMap<String, Integer>());

        long nanos = runBenchmark(catalog);
        printResult("synchronizedMap (coarse full-table lock)", nanos);
    }

    static void benchmarkConcurrentMap() throws InterruptedException {
        Map<String, Integer> catalog =
                new ConcurrentHashMap<String, Integer>();

        long nanos = runBenchmark(catalog);
        printResult("ConcurrentHashMap (fine-grained bucket lock)", nanos);
    }

    static void printResult(String label, long nanos) {
        long totalOps = (long) NUM_THREADS * OPS_PER_THREAD;
        double seconds = nanos / 1_000_000_000.0;
        double throughput = totalOps / seconds;

        System.out.printf(
                "%s%n  Time: %.2f ms  |  Throughput: %.0f ops/sec%n",
                label, nanos / 1_000_000.0, throughput);
    }

    public static void main(String[] args) throws InterruptedException {
        benchmarkSynchronizedMap();
        benchmarkConcurrentMap();
    }
}