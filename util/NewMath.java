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

    public static double factorial(double number) {
      double result = 1;
      for (int i = 1; i <= number; i++) {
        result *= i;
      }
      return result;
    }

    public static double[][] inversion(double[][] A, int N)
    {
        double temp;
 
        double[][] E = new double[N][N];
 
 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                E[i][j] = 0;
                if (i == j) {
                    E[i][j] = 1;
                }
            }
        }
 
        for (int k = 0; k < N; k++) {
            temp = A[k][k];
 
            for (int j = 0; j < N; j++) {
                A[k][j] /= temp;
                E[k][j] /= temp;
            }
 
            for (int i = k + 1; i < N; i++) {
                temp = A[i][k];
 
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
 
        for (int k = N - 1; k > 0; k--)
        {
            for (int i = k - 1; i >= 0; i--)
            {
                temp = A[i][k];
 
                for (int j = 0; j < N; j++)
                {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
 
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = E[i][j];
 

        return A;
    }
}
