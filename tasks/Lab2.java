package tasks;

import java.util.List;
import java.util.ArrayList;

import static util.NewMath.*;

public class Lab2 {
    private Lab2() {}

    public static double lagrange(double x) {
        double[] vectorX = {1, 4 ,5, 6};
        double[] vectorY = {2, 3, 2, 3};

        int vectorLength = vectorX.length;
        double[] vectorL = new double[vectorLength];

        for (int i = 0; i < vectorLength; i++) {
            double num = 1.0;
            double denom = 1.0;

            for (int k = 0; k < vectorLength; k++) {
                if (k == i) continue;
                num *= x - vectorX[k];
                denom *= vectorX[i] - vectorX[k];
            }
            vectorL[i] = num / denom;
        }

        double result = 0.0;
        for (int i = 0; i < vectorX.length; i++) {
            result += vectorY[i] * vectorL[i];
        }

        return result;
    }

    public static double calcError(double x) {
        double[] vector = {0.5, 0.25, 1};
        double prod = 1;
        for (double val : vector) {
            prod *= x - val;
        }
        return 32 / Math.log(2) / factorial(vector.length) * Math.abs(prod);
    }

    public static double newton(double x) {
        double[] vectorX = {1, 2, 4, 6};
        double[] vectorY = {2, -1, -2, -6};

        List<double[]> subList = new ArrayList<>();

        for (int i = 0; i < vectorX.length; i++) {
            double[] sub = new double[vectorX.length - 1 - i];
            if (i == 0) {
                for (int j = 0; j < sub.length; j++) {
                    sub[j] = (vectorY[j + 1] - vectorY[j]) / (vectorX[j + 1] - vectorX[j]);
                }
            }
            else {
                for (int j = 0; j < sub.length; j++) {
                    sub[j] = (subList.get(i - 1)[j + 1] - subList.get(i - 1)[j]) / (vectorX[j + 1 + i] - vectorX[j]);
                }
            }
            subList.add(sub);
        }

        double result = vectorY[0];
        for (int i = 0; i < subList.size() - 1; i++) {
            double prod = subList.get(i)[0];
            for (int k = 0; k <= i; k++) {
                prod *= x - vectorX[k];
            }
            result += prod;
        }

        return result;
    }

    public static double newtonNodes(double x) {
        double[] vectorX = {0.1, 0.2, 0.3, 0.4};
        double[] vectorY = {6, 0, 2, 6};

        double h = 0.1;

        List<double[]> subList = new ArrayList<>();

        for (int i = 0; i < vectorX.length; i++) {
            double[] sub = new double[vectorX.length - 1 - i];
            if (i == 0) {
                for (int j = 0; j < sub.length; j++) {
                    sub[j] = vectorY[j + 1] - vectorY[j];
                }
            }
            else {
                for (int j = 0; j < sub.length; j++) {
                    sub[j] = subList.get(i - 1)[j + 1] - subList.get(i - 1)[j];
                }
            }
            subList.add(sub);
        }

        double result = vectorY[0];
        for (int i = 0; i < subList.size() - 1; i++) {
            double prod = (subList.get(i)[0]) / (Math.pow(h, i + 1) * factorial(i + 1));
            for (int k = 0; k <= i; k++) {
                prod *= x - vectorX[k];
            }
            result += prod;
        }

        return result;
    }

    public static double minSqrt(double x) {
        double[] vectorX = {-1, -0.5, 0, 0.5, 1};
        double[] vectorY = new double[vectorX.length];

        for (int i = 0; i < vectorX.length; i++) {
          vectorY[i] = Math.exp(vectorX[i]);
        }

        int m = 3;
        double[][] matrixC = new double[m][m];
        for (int i = 0; i < m; i++) {
          for (int j = 0; j < m; j++) {
            double sum = 0;
            for (int k = 0; k < vectorX.length; k++) {
              sum += Math.pow(vectorX[k], i) * Math.pow(vectorX[k], j);
            }
            matrixC[i][j] = sum;
          }
        }

        double[] vectorB = new double[m];
        for (int i = 0; i < m; i++) {
          double sum = 0;
          for (int k = 0; k < vectorX.length; k++) {
            sum += Math.pow(vectorX[k], i) * vectorY[k];
          }
          vectorB[i] = sum;
        }

        double[][] invertedMatrix = inversion(matrixC, m);

        double[] vectorA = mult(invertedMatrix, vectorB);

        double result = 0;
        for (int i = 0; i < vectorA.length; i++) {
            result += vectorA[i] * Math.pow(x, i);
        }

        return result;
    }

    public static double spline(double x) {
      double[] vectorX = {1, 2, 3, 4, 5, 6};
      double[] vectorY = {2, 3, 5, 3, 4, 6};

      int n = vectorX.length;
      double[] vectorB = new double[n];
      double[] vectorA = new double[n - 1];

      vectorB[0] = 0;
      for (int i = 0; i < n - 1; i++) {
        vectorB[i + 1] = (2 * (vectorY[i + 1] - vectorY[i])) / (vectorX[i + 1] - vectorX[i]) - vectorB[i];
      }

      for (int i = 0; i < n - 1; i++) {
        vectorA[i] = (vectorB[i + 1] - vectorB[i]) / (2 * (vectorX[i + 1] - vectorX[i]));
      }

      double result = -1;
      for (int i = 1; i < n; i++) {
        if (x >= vectorX[i - 1] && x <= vectorX[i]) {
          result = vectorA[i - 1] * (x - vectorX[i - 1]) * (x - vectorX[i - 1]) + vectorB[i - 1] * (x - vectorX[i - 1]) + vectorY[i - 1];
        }
      }
      return result;
    }
}
