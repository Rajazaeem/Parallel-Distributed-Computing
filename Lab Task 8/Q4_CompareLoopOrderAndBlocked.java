
import java.util.Random;
import java.util.Scanner;

public class Q4_CompareLoopOrderAndBlocked {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== Matrix Multiplication Comparison =====");
            System.out.println("1. Run Required Test (2048 x 2048)");
            System.out.println("2. Run Smaller Test");
            System.out.println("3. Exit");

            choice = readChoice("Enter your choice: ", 1, 3);

            if (choice == 1) {
                compareMethods(2048);
            }
            else if (choice == 2) {
                int n = readChoice("Enter matrix size: ", 1, 2048);
                compareMethods(n);
            }
            else {
                System.out.println("Program ended. Thank you!");
            }

        } while (choice != 3);

        input.close();
    }

    public static void compareMethods(int n) {

        Random random = new Random();

        int[][] a = new int[n][n];
        int[][] b = new int[n][n];

        int[][] c1 = new int[n][n];
        int[][] c2 = new int[n][n];

        System.out.println("\nCreating input matrices...");
        fillMatrices(a, b, random);

        System.out.println("Matrix size: " + n + " x " + n);
        System.out.println("Blocked multiplication block size: 64");

        // First implementation: i -> k -> j

        System.out.println("\nRunning i-k-j multiplication...");

        long startTime = System.nanoTime();

        normalMultiply(a, b, c1);

        long endTime = System.nanoTime();

        double normalTime = (endTime - startTime) / 1000000.0;

        System.out.printf("i-k-j execution time: %.3f ms%n",
                normalTime);

        // Second implementation: Blocked multiplication

        System.out.println("\nRunning blocked multiplication...");

        startTime = System.nanoTime();

        blockedMultiply(a, b, c2, 64);

        endTime = System.nanoTime();

        double blockedTime = (endTime - startTime) / 1000000.0;

        System.out.printf("Blocked execution time: %.3f ms%n",
                blockedTime);

        // Verify both results

        boolean same = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (c1[i][j] != c2[i][j]) {
                    same = false;
                    break;
                }
            }

            if (!same) {
                break;
            }
        }

        System.out.println("\n===== Performance Comparison =====");
        System.out.println("Implementation\tExecution Time (ms)");

        System.out.printf("i-k-j\t\t%.3f ms%n", normalTime);
        System.out.printf("Blocked\t\t%.3f ms%n", blockedTime);

        System.out.println("\nResults are equal: " + same);

        if (normalTime < blockedTime) {
            System.out.println("Faster method: i-k-j");
        }
        else if (blockedTime < normalTime) {
            System.out.println("Faster method: Blocked Multiplication");
        }
        else {
            System.out.println("Both methods have equal measured time.");
        }

        System.out.println("Sample result: " + c1[0][0]);
    }

    public static void fillMatrices(
            int[][] a, int[][] b, Random random) {

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {

                a[i][j] = random.nextInt(10) + 1;
                b[i][j] = random.nextInt(10) + 1;
            }
        }
    }

    public static void normalMultiply(
            int[][] a, int[][] b, int[][] c) {

        int n = a.length;

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {

                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }

    public static void blockedMultiply(
            int[][] a, int[][] b, int[][] c, int blockSize) {

        int n = a.length;

        for (int ii = 0; ii < n; ii += blockSize) {
            for (int kk = 0; kk < n; kk += blockSize) {
                for (int jj = 0; jj < n; jj += blockSize) {

                    int iEnd = Math.min(ii + blockSize, n);
                    int kEnd = Math.min(kk + blockSize, n);
                    int jEnd = Math.min(jj + blockSize, n);

                    for (int i = ii; i < iEnd; i++) {
                        for (int k = kk; k < kEnd; k++) {
                            for (int j = jj; j < jEnd; j++) {

                                c[i][j] += a[i][k] * b[k][j];
                            }
                        }
                    }
                }
            }
        }
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
