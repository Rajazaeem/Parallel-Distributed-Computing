
import java.util.Random;
import java.util.Scanner;

public class Q1_BlockedMatrixMultiplication {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== Blocked Matrix Multiplication =====");
            System.out.println("1. Run Required Test (2048 x 2048)");
            System.out.println("2. Run Smaller Test");
            System.out.println("3. Exit");

            choice = readChoice("Enter your choice: ", 1, 3);

            if (choice == 1) {
                runTest(2048, 64);
            }
            else if (choice == 2) {
                int n = readChoice("Enter matrix size: ", 1, 2048);
                runTest(n, 64);
            }
            else {
                System.out.println("Program ended. Thank you!");
            }

        } while (choice != 3);

        input.close();
    }

    public static void runTest(int n, int blockSize) {

        Random random = new Random();

        int[][] a = new int[n][n];
        int[][] b = new int[n][n];
        int[][] c = new int[n][n];

        System.out.println("\nCreating matrices...");

        fillMatrices(a, b, random);

        System.out.println("Matrix size: " + n + " x " + n);
        System.out.println("Block size: " + blockSize);

        long startTime = System.nanoTime();

        blockedMultiply(a, b, c, blockSize);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000.0;

        System.out.printf("\nExecution Time: %.3f ms%n", time);

        System.out.println("Sample result C[0][0]: " + c[0][0]);
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
