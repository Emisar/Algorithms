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
                for (int j = 0; j < matrixSize; j++) {
                    system[i][j] = matrix[i][j];
                }
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
                for (int j = 0; j < matrixSize; j++) {
                    symmetric[i][j] = symmetricSystem[i][j];
                }
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
        matrixT1[0][0] = Math.sqrt(matrix[0][0]);
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
                    matrixT1[i][i] = Math.sqrt(matrix[i][i] - sum);
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
        System.out.println("==== ==== ==== ==== ====\nMatrix A");
        Util.printMatrix(matrix);
        System.out.println("---- ---- ---- ---- ----\nVector B");
        Util.printVector(vector);
        System.out.println("==== ==== ==== ==== ====\nMatrix T");
        Util.printMatrix(matrixT1);
        System.out.println("---- ---- ---- ---- ----\nMatrix T'");
        Util.printMatrix(matrixT2);
        System.out.println("==== ==== ==== ==== ====\nVector Y");
        Util.printVector(vectorY);
        System.out.println("---- ---- ---- ---- ----\nVector X");
        Util.printVector(vectorX);
        System.out.println("==== ==== ==== ==== ====");
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
        System.out.println("==== ==== ==== ==== ====\nMatrix A");
        Util.printMatrix(matrix);
        System.out.println("---- ---- ---- ---- ----\nVector B");
        Util.printVector(vector);
        System.out.println("==== ==== ==== ==== ====\nMatrix P");
        Util.printMatrix(matrixP);
        System.out.println("---- ---- ---- ---- ----\nMatrix C");
        Util.printMatrix(matrixC);
        System.out.println("==== ==== ==== ==== ====\nVector Y");
        Util.printVector(vectorY);
        System.out.println("---- ---- ---- ---- ----\nVector X");
        Util.printVector(vectorX);
        System.out.println("==== ==== ==== ==== ====");
    }


}