package tasks;

import java.util.function.Function;

public class Lab3 {
  private Lab3() {
    //
  }

  // Формула Симпсона
  public static double simpson(double a, double b, Function<Double, Double> func) {
    double[] vectorY = {
      func.apply(a),
      func.apply((a + b) / 2),
      func.apply(b),
    };
    double[] vectorC = { 
      (b - a) / 6, 
      (b - a) / (2 * 3),
      (b - a) / 6 
    };
    double result = 0;

    for (int i = 0; i < 3; i++) {
      result += vectorC[i] * vectorY[i];
    }

    return result;
  }

  // Погрешность формулы средних прямоугольников
  public static double rectangle() {
    double a = -1;
    double b = 0;
    double result = 0;

    result = Math.pow(b - a, 3) / 12;
    result *= Math.max(Math.abs(Math.sin(a)), Math.abs(Math.sin(b)));

    return result;
  }

  // Нахождение корней квадратного уравнения
  private static double[] quadEquation(double a, double b, double c) {
    double d = Math.pow(b, 2) - 4 * a * c;
    if (d < 0)
      return new double[] {};
    return new double[] { (-b + Math.sqrt(d)) / (2 * a), (-b - Math.sqrt(d)) / (2 * a) };
  }

  // Вычисление значения интеграла вида x^n
  private static double integralXN(double x, int n) {
    return Math.pow(x, n + 1.0) / (n + 1);
  }

  public static double gauss(Function<Double, Double> func) {
    double[][] matrix = new double[2][3]; // Матрица коэффициентов системы уравнений квадратуры Гаусса
    double[] tempVector = new double[matrix[0].length]; // Дополнительный вектор для посчета значении U и V
    double u; // x0 + x1
    double v; // x0 * x1
    double[] vectorX; // Узлы Гауссовой квадратуры
    double[] coef = new double[] { 1, 1 }; // Коэффициенты Гауссовой квадратуры

    // Находим коэффициенты для системы уравнений узлов квадратуры Гаусса
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        matrix[i][j] = integralXN(1, 2 - j + i);
      }
    }

    // Вычисляем значение переменных U и V
    for (int i = 0; i < tempVector.length; i++) {
      tempVector[i] = matrix[0][i] - (matrix[1][i] * 2);
    }
    u = -tempVector[0] / tempVector[1];
    v = (matrix[0][0] + u * matrix[0][1]) * -1;

    // Находим решение системы (узлы Гауссовой квадратуры)
    vectorX = quadEquation(1, u, v);

    // Определяем коэффициенты Гауссовой квадратуры
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        if (i == j)
          continue;
        coef[i] *= (0.5 - vectorX[j]) / (vectorX[i] - vectorX[j]);
      }
    }

    double result = 0;

    for (int i = 0; i < 2; i++) {
      result += coef[i] * func.apply(vectorX[i]);
    }

    // Возвращаем найденные узлы и коэффициенты
    return result;
  }
}