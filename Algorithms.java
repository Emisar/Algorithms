public class Algorithms {
    private Algorithms() {}

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
                for (int k = 0; k <= j - 1; k++) {
                    sumP += matrixP[i][k] * matrixC[k][j];
                }
                matrixP[i][j] = matrix[i][j] - sumP;

                double sumC = 0.0;
                for (int k = 0; k <= j - 1; k++) {
                    sumC += matrixP[j][k] * matrixC[k][i];
                }
                matrixC[j][i] = 1 / matrixP[j][j] * (matrix[j][i] - sumC);
            }
        }

        // Calculating vector Y
        vectorY[0] = vector[0] / matrixP[0][0];
        for (int i = 1; i < vectorSize; i++) {
            double sum = 0.0;
            for (int k = 0; k <= i - 1; k++) {
                sum += matrixP[i][k] * vectorY[k];
            }
            vectorY[i] = (vector[i] - sum) / matrixP[i][i];
        }

        // Calculating vector X
        vectorX[vectorSize - 1] = vectorY[vectorSize - 1];
        for (int i = vectorSize - 2; i >= 0; i--) {
            double sum = 0.0;
            for (int k = i + 1; k <= vectorSize - 1; k++) {
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