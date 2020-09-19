package util;

public class NewMath {
    private NewMath() {}

    public static double[] mult(double[][] matrix, double[] vector) {
        double[] result = new double[vector.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        return result;
    }

    public static double[] add(double[] vector1, double[] vector2) {
        double[] result = new double[vector1.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = vector1[i] + vector2[i];
        }

        return result;
    }
}
