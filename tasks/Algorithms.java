package tasks;

import util.NewMath;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import static newio.Output.*;

public class Algorithms {
    private Algorithms() {}

    public static void sqrtMethod(double[][] matrix, double[] vector) {
        // Result data
        int matrixSize = matrix.length;
        double[][] matrixT1 = new double[matrixSize][matrixSize];
        double[][] matrixT2 = new double[matrixSize][matrixSize];

        int vectorSize = vector.length;
        double[] vectorY = new double[vectorSize];
        double[] vectorX = new double[vectorSize];

        boolean isSymmetric = true;
        // Check for symmetric
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    isSymmetric = false;
                    break;
                }
            }
            if (!isSymmetric) {
                break;
            }
        }

        // Make the matrix symmetric
        if (!isSymmetric) {
            // Создаем транспонированную матрицу
            double[][] transpose = new double[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    transpose[i][j] = matrix[j][i];
                }
            }

            // Собираем систему уравнений в виде единой матрицы n * (n+1)
            double[][] system = new double[matrixSize][matrixSize + 1];
            for (int i = 0; i < matrixSize; i++) {
                system[i] = Arrays.copyOf(matrix[i], matrixSize + 1);
            }
            for (int i = 0; i < vectorSize; i++) {
                system[i][matrixSize] = vector[i];
            }

            // Находим симметричную систему от носительно нашей
            double[][] symmetricSystem = new double[matrixSize][matrixSize + 1];
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize + 1; j++) {
                    for (int k = 0; k < matrixSize; k++) {
                        symmetricSystem[i][j] += transpose[i][k] * system[k][j];
                    }
                }
            }

            // Выделяем из системы матрицу и вектор значений
            double[][] symmetric = new double[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++) {
                symmetric[i] = Arrays.copyOf(symmetricSystem[i], matrixSize);
            }
            double[] newVector = new double[vectorSize];
            for (int i = 0; i < vectorSize; i++) {
                newVector[i] = symmetricSystem[i][matrixSize];
            }

            // Определяем их как новые данные
            matrix = symmetric;
            vector = newVector;
        }

        // First row
        matrixT1[0][0] = sqrt(matrix[0][0]);
        for (int j = 1; j < matrixSize; j++) {
            matrixT1[0][j] = matrix[0][j] / matrixT1[0][0];
        }
        // Other part
        for (int i = 1; i < matrixSize; i++) {
            for (int j = i; j < matrixSize; j++) {
                // Diagonal
                if (i == j) {
                    double sum = 0.0;
                    for (int k = 0; k < i; k++) {
                        sum += matrixT1[k][i] * matrixT1[k][i];
                    }
                    matrixT1[i][i] = sqrt(matrix[i][i] - sum);
                }
                // Othen
                else {
                    double sum = 0.0;
                    for (int k = 0; k < i; k++) {
                        sum += matrixT1[k][i] * matrixT1[k][j];
                    }
                    matrixT1[i][j] = (matrix[i][j] - sum) / matrixT1[i][i];
                }
            }
        }
        // Transpose matrix
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrixT2[j][i] = matrixT1[i][j];
            }
        }

        // Calculating vector Y
        vectorY[0] = vector[0] / matrixT1[0][0];
        for (int i = 1; i < vectorSize; i++) {
            double sum = 0.0;
            for (int k = 0; k < i; k++) {
                sum += matrixT1[k][i] * vectorY[k];
            }
            vectorY[i] = (vector[i] - sum) / matrixT1[i][i];
        }

        // Calculating vector X
        vectorX[vectorSize - 1] = vectorY[vectorSize - 1] / matrixT1[vectorSize - 1][vectorSize - 1];
        for (int i = vectorSize - 2; i >= 0; i--) {
            double sum = 0.0;
            for (int k = i + 1; k < vectorSize; k++) {
                sum += matrixT1[i][k] * vectorX[k];
            }
            vectorX[i] = (vectorY[i] - sum) / matrixT1[i][i];
        }

        // Print result
        printTitle("Matrix A");
        printMatrix(matrix);
        printSubtitle("Vector B");
        printVector(vector);
        printTitle("Matrix T");
        printMatrix(matrixT1);
        printSubtitle("Matrix T'");
        printMatrix(matrixT2);
        printTitle("Vector Y");
        printVector(vectorY);
        printSubtitle("Vector X");
        printVector(vectorX);
    }

    public static void cholesky(double[][] matrix, double[] vector) {
        // Result data
        int matrixSize = matrix.length;
        double[][] matrixP = new double[matrixSize][matrixSize];
        double[][] matrixC = new double[matrixSize][matrixSize];

        int vectorSize = vector.length;
        double[] vectorY = new double[vectorSize];
        double[] vectorX = new double[vectorSize];

        // First column/row
        for (int i = 0; i < matrixSize; i++) {
            matrixP[i][0] = matrix[i][0];
            matrixC[0][i] = matrix[0][i] / matrixP[0][0];
        }

        // Other matrix
        for (int j = 1; j < matrixSize; j++) {
            for (int i = j; i < matrixSize; i++) {
                double sumP = 0.0;
                for (int k = 0; k < j; k++) {
                    sumP += matrixP[i][k] * matrixC[k][j];
                }
                matrixP[i][j] = matrix[i][j] - sumP;

                double sumC = 0.0;
                for (int k = 0; k < j; k++) {
                    sumC += matrixP[j][k] * matrixC[k][i];
                }
                matrixC[j][i] = 1 / matrixP[j][j] * (matrix[j][i] - sumC);
            }
        }

        // Calculating vector Y
        vectorY[0] = vector[0] / matrixP[0][0];
        for (int i = 1; i < vectorSize; i++) {
            double sum = 0.0;
            for (int k = 0; k < i; k++) {
                sum += matrixP[i][k] * vectorY[k];
            }
            vectorY[i] = (vector[i] - sum) / matrixP[i][i];
        }

        // Calculating vector X
        vectorX[vectorSize - 1] = vectorY[vectorSize - 1];
        for (int i = vectorSize - 2; i >= 0; i--) {
            double sum = 0.0;
            for (int k = i + 1; k < vectorSize; k++) {
                sum += matrixC[i][k] * vectorX[k];
            }
            vectorX[i] = vectorY[i] - sum;
        }

        // Print result
        printTitle("Matrix A");
        printMatrix(matrix);
        printSubtitle("Vector B");
        printVector(vector);
        printTitle("Matrix P");
        printMatrix(matrixP);
        printSubtitle("Matrix C");
        printMatrix(matrixC);
        printTitle("Vector Y");
        printVector(vectorY);
        printSubtitle("Vector X");
        printVector(vectorX);
    }

    public static void yakoby(double[][] matrix, double[] vector, double eps) {
        // Result data
        int matrixSize = matrix.length;
        double[][] matrixB = new double[matrixSize][matrixSize];

        int vectorSize = vector.length;
        double[] vectorD = new double[vectorSize];
        // Vector for searching q-value
        double[] vectorQ = new double[vectorSize];

        // Calculating matrixB, vectorD and vectorQ
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (i == j) matrixB[i][j] = 0;
                else matrixB[i][j] = matrix[i][j] / matrix[i][i] * -1;
                vectorQ[i] += abs(matrixB[i][j]);
            }
            vectorD[i] = vector[i] / matrix[i][i];
        }
        // Search q (max value in vectorQ)
        Arrays.sort(vectorQ);
        double q = vectorQ[vectorQ.length - 1];

        // Started values for Yakoby
        // List of x-vector (resulting vectors)
        List<double[]> vectorList = new ArrayList<>();
        vectorList.add(new double[vectorSize]);
        // One part of the termination condition 
        double maxDelta = 0;

        do {
            // Calculating vector Xn+1
            double[] vectorX = new double[vectorSize];
            vectorX = NewMath.add(NewMath.mult(matrixB, vectorList.get(vectorList.size() - 1)), vectorD);
            vectorList.add(vectorX);
            // Calculating difference between the values of vectors Xn+1 and Xn
            double[] delta = new double[vectorSize];
            for (int i = 0; i < delta.length; i++) {
                delta[i] = abs(vectorList.get(vectorList.size() - 1)[i] - vectorList.get(vectorList.size() - 2)[i]);
            }
            Arrays.sort(delta);
            maxDelta = delta[delta.length - 1];
        } while (maxDelta > (1 - q) / q * eps);

        // Number of iterations
        int n = vectorList.size() - 1;

        // Print result
        printTitle("Matrix A");
        printMatrix(matrix);
        printSubtitle("Vector B");
        printVector(vector);
        printSubtitle("Epsilon");
        printlnNumber(eps);
        printTitle("Matrix B");
        printMatrix(matrixB);
        printSubtitle("Vector D");
        printVector(vectorD);
        printSubtitle("q");
        printlnNumber(q);
        printTitle("Number of iterations");
        printlnNumber(n);
        printSubtitle("Vectors X");
        vectorList.stream().forEach(newio.Output::printVector);
    }

    public static void seidel(double[][] matrix, double[] vector, double eps) {
        // Result data
        int matrixSize = matrix.length;
        double[][] matrixB = new double[matrixSize][matrixSize];

        int vectorSize = vector.length;
        double[] vectorD = new double[vectorSize];

        // Calculating matrixB, vectorD and vectorQ
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (i == j) matrixB[i][j] = 0;
                else matrixB[i][j] = matrix[i][j] / matrix[i][i] * -1;
            }
            vectorD[i] = vector[i] / matrix[i][i];
        }

        // Started values for Seidel
        // List of x-vector (resulting vectors)
        List<double[]> vectorList = new ArrayList<>();
        vectorList.add(new double[vectorSize]);
        // One part of the termination condition 
        double maxDelta = 0;

        do {
            double[] vectorX = new double[vectorSize];
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < vectorSize; j++) {
                    if (i <= j) vectorX[i] += matrixB[i][j] * vectorList.get(vectorList.size() - 1)[j];
                    else vectorX[i] += matrixB[i][j] * vectorX[j];
                }
                vectorX[i] += vectorD[i];
            }
            vectorList.add(vectorX);
            // Calculating difference between the values of vectors Xn+1 and Xn
            double[] delta = new double[vectorSize];
            for (int i = 0; i < delta.length; i++) {
                delta[i] = abs(vectorList.get(vectorList.size() - 1)[i] - vectorList.get(vectorList.size() - 2)[i]);
            }
            Arrays.sort(delta);
            maxDelta = delta[delta.length - 1];
        } while (maxDelta > eps);

        // Number of iterations
        int n = vectorList.size() - 1;

        // Print result
        printTitle("Matrix A");
        printMatrix(matrix);
        printSubtitle("Vector B");
        printVector(vector);
        printSubtitle("Epsilon");
        printlnNumber(eps);
        printTitle("Matrix B");
        printMatrix(matrixB);
        printSubtitle("Vector D");
        printVector(vectorD);
        printTitle("Number of iterations");
        printlnNumber(n);
        printSubtitle("Vectors X");
        vectorList.stream().forEach(newio.Output::printVector);
    }
}