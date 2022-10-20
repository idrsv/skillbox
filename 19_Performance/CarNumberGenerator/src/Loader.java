import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Loader {

    static final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    static final int NUMBERS_SIZE = 1000;
    static final int REGION = 100;
    static final String PATH = "res/ThreadsGeneration.txt";

        public static void main(String[] args) {

            int threadCount = Runtime.getRuntime().availableProcessors();
            long start = System.currentTimeMillis();

            ExecutorService pool = Executors.newFixedThreadPool(threadCount);
            List<Future<?>> futures = new ArrayList<>();

            futures.add(pool.submit(() -> ThreadsGeneration.threadsGenerate(NUMBERS_SIZE, REGION, LETTERS , PATH)));

            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            pool.shutdown();

            System.out.println((System.currentTimeMillis() - start) + " ms");
        }
}