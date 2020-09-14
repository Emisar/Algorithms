public class Util {
    private Util() {}

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            printVector(matrix[i]);
        }
    }

    public static void printVector(double[] vector) {
        for (int i = 0; i < vector.length; i++) {
            printNumber(vector[i]);
        }
        System.out.println();
    }

    public static void printNumber(double number) {
        if (number >= 0) System.out.print(" ");
        if (Math.abs(number) < 10) System.out.print(" ");
        System.out.printf("%.2f", number);
        System.out.print("  ");
    }
}
