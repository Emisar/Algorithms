public class Main {
    public static void printMatrix(double[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=======================");
    }

    public static void main(String args[]) {
        double[][] matrixA = {
            {-4, 1, 1},
            {1, -9, 3},
            {1, 2, -16}
        };
        double[][] matrixP = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        double[][] matrixC = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };


        printMatrix(matrixA);
        printMatrix(matrixP);
        printMatrix(matrixC);

        // Первый столбец в матрице P и первая сточка в матрице C
        for(int i = 0; i < matrixA.length; i++) {
            matrixP[i][0] = matrixA[i][0];
            matrixC[0][i] = matrixA[0][i]/matrixP[0][0];
        }

        // Остальная часть
        for(int i = 1; i < matrixA.length; i++) {
            for(int j = 1; j <= i; j++) {
                double sum = 0;
                for(int k = 0; k < j - 1; k++) {
                    sum = matrixP[i][k] * matrixC[k][j];
                }

                matrixP[i][j] = matrixA[i][j] - sum;
                matrixC[i][j] = 1 / matrixP[i][i] * (matrixA[i][j] - sum);
            }
        }
        

        printMatrix(matrixA);
        printMatrix(matrixP);
        printMatrix(matrixC);
    }
}