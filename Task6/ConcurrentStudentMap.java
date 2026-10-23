import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentStudentMap {
    public static void main(String[] args) {
        int threadCount = 5;
        int recordsPerThread = 1_000;

        ConcurrentHashMap<String, Integer> studentMap =
                new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        int expectedEntries = threadCount * recordsPerThread;

        long startTime = System.nanoTime();

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

        int retrievedValues = 0;
        for (int threadId = 0; threadId < threadCount; threadId++) {
            for (int i = 0; i < recordsPerThread; i++) {
                String name = "Student-" + threadId + "-" + i;
                if (studentMap.get(name) != null) {
                    retrievedValues++;
                }
            }
        }

        long executionTime =
                TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);

        System.out.println("Number of threads: " + threadCount);
        System.out.println("Expected entries: " + expectedEntries);
        System.out.println("Actual entries: " + studentMap.size());
        System.out.println("Values retrieved: " + retrievedValues);
        System.out.println("Execution time: " + executionTime + " ms");
    }
}