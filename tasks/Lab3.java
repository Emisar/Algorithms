package tasks;

import java.util.List;
import java.util.ArrayList;

public class Lab3 {
  public static double simpson() {
    double[] vectorX = {0, 0.5, 1};
    double[] vectorY = {-1, 1, 2.5};

    double result = ((vectorX[2] - vectorX[0]) / 6);
    result *= (Math.pow(vectorX[0], 2) * vectorY[0] + Math.pow(vectorX[1], 2) * vectorY[1] + Math.pow(vectorX[2], 2) * vectorY[2]);

    return result;
  }

  public static double rectangle() {
    double a = -1;
    double b = 0;

    double result = Math.pow(b - a, 3) / 12;
    result *= Math.abs(Math.sin(a));

    return result;
  }

  public static double gaus(double n) {
    double a = 0;
    double b = 1;
    double[][] matrix = new double[2][3];

    for (int i = 0; i < 2; i++) {
      for (int j = 2; j >= 0; j--) {
        matrix[i][j] = Math.pow(1, j - i + 2) / (j - i + 2);
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }

    double[] temp = new double[3];
    for (int i = 0; i < 3; i++) {
      temp[i] = matrix[0][i] - (matrix[1][i] * 0.5);
      System.out.print(temp[i] + " ");
    }
    System.out.println();
    
    double u = temp[0] / temp[1];
    double v = matrix[1][0] + matrix[1][1] * u;

    System.out.println(u + " " + v);

    return -1;
  }
}