public class CacheLocalityBenchmark {

    static final int N = 2000;
    static final int ITERATIONS = 10;

    public static void main(String[] args) {
        double[][] matrix = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = i + j;
            }
        }

        long rowTotal = 0, colTotal = 0;
        double rSum = 0, cSum = 0;

        for (int iter = 0; iter < ITERATIONS; iter++) {
            long t1 = System.nanoTime();
            rSum = traverseRowMajor(matrix);
            long t2 = System.nanoTime();
            rowTotal += (t2 - t1);

            long t3 = System.nanoTime();
            cSum = traverseColumnMajor(matrix);
            long t4 = System.nanoTime();
            colTotal += (t4 - t3);
        }

        double rowAvgMs = rowTotal / ITERATIONS / 1_000_000.0;
        double colAvgMs = colTotal / ITERATIONS / 1_000_000.0;
        double speedup = colAvgMs / rowAvgMs;

        System.out.println("Row-major average time: " + rowAvgMs + " ms");
        System.out.println("Column-major average time: " + colAvgMs + " ms");
        System.out.println("Speedup: " + speedup);
        System.out.println("Row-major sum: " + rSum);
        System.out.println("Column-major sum: " + cSum);
    }

    static double traverseRowMajor(double[][] m) {
        double sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += m[i][j];
            }
        }

        return sum;
    }

    static double traverseColumnMajor(double[][] m) {
        double sum = 0;

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                sum += m[i][j];
            }
        }

        return sum;
    }
}