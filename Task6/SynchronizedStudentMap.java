import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedStudentMap {
    public static void main(String[] args) {
        int threadCount = 3;
        int recordsPerThread = 100;

        Map<String, Integer> studentMap =
                Collections.synchronizedMap(new HashMap<>());
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int threadId = 0; threadId < threadCount; threadId++) {
            final int id = threadId;

            executor.submit(() -> {
                for (int i = 0; i < recordsPerThread; i++) {
                    String name = "Student-" + id + "-" + i;
                    studentMap.put(name, i % 101);
                }
            });
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
                throw new IllegalStateException("Tasks did not finish in time.");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Waiting was interrupted.", e);
        }

        int expectedEntries = threadCount * recordsPerThread;
        System.out.println("Expected entries: " + expectedEntries);
        System.out.println("Actual entries: " + studentMap.size());
    }
}