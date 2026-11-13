
import java.util.Random;
import java.util.Scanner;

public class Q3_BestLoopOrderMatrixSizes {

    static Scanner input = new Scanner(System.in);

    static int[] sizes = {128, 256, 512, 1024, 1536, 2048};

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== Matrix Size Performance Test =====");
            System.out.println("1. Run All Required Matrix Sizes");
            System.out.println("2. Run Custom Matrix Size");
            System.out.println("3. Exit");

            choice = readChoice("Enter your choice: ", 1, 3);

            if (choice == 1) {
                runAllSizes();
            }
            else if (choice == 2) {
                int n = readChoice("Enter matrix size: ", 1, 2048);
                runTest(n);
            }
            else {
                System.out.println("Program ended. Thank you!");
            }

        } while (choice != 3);

        input.close();
    }

    public static void runAllSizes() {

        double[] times = new double[sizes.length];

        System.out.println("\n===== Matrix Size Comparison =====");

        for (int i = 0; i < sizes.length; i++) {

            System.out.println("\nRunning " + sizes[i] + " x " + sizes[i]);

            times[i] = runTest(sizes[i]);
        }

        System.out.println("\n===== Final Comparison Table =====");
        System.out.println("Matrix Size\tExecution Time (ms)");

        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%d x %d\t\t%.3f ms%n",
                    sizes[i], sizes[i], times[i]);
        }
    }

    public static double runTest(int n) {

        Random random = new Random();

        int[][] a = new int[n][n];
        int[][] b = new int[n][n];
        int[][] c = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = random.nextInt(10) + 1;
                b[i][j] = random.nextInt(10) + 1;
            }
        }

        long startTime = System.nanoTime();

        // Best loop order: i -> k -> j

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {

                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000.0;

        System.out.printf("Execution Time: %.3f ms%n", time);

        System.out.println("Sample result C[0][0]: " + c[0][0]);

        return time;
    }

    public static int readChoice(
            String message, int minimum, int maximum) {

        while (true) {
            System.out.print(message);

            if (input.hasNextInt()) {
                int value = input.nextInt();

                if (value >= minimum && value <= maximum) {
                    return value;
                }
            } else {
                input.next();
            }

            System.out.println("Invalid input. Enter a value from "
                    + minimum + " to " + maximum + ".");
        }
    }
}
