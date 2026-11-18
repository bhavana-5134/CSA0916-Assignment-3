import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BatchProcessor {
    private static Callable<Integer> processFile(int fileNumber) {
        return () -> {
            // Simulated file processing; selected files simulate missing/corrupt input.
            if (fileNumber % 997 == 0) {
                throw new IllegalStateException("Invalid or missing result file " + fileNumber);
            }
            return fileNumber;
        };
    }

    public static void main(String[] args) throws InterruptedException {
        int[] poolSizes = {1, 2, 4, 8};
        for (int poolSize : poolSizes) {
            ExecutorService pool = Executors.newFixedThreadPool(poolSize);
            List<Future<Integer>> results = new ArrayList<>();
            long start = System.nanoTime();

            for (int i = 1; i <= 10_000; i++) {
                results.add(pool.submit(processFile(i)));
            }

            int successful = 0;
            int failed = 0;
            long total = 0;
            for (Future<Integer> result : results) {
                try {
                    total += result.get();
                    successful++;
                } catch (ExecutionException ex) {
                    failed++;
                }
            }

            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.MINUTES);
            long elapsedMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
            System.out.println("Pool=" + poolSize + ", success=" + successful
                    + ", failed=" + failed + ", total=" + total
                    + ", elapsedMs=" + elapsedMs);
        }
    }
}
