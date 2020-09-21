import newio.Input;

import static newio.Output.*;
import static tasks.Algorithms.*;

public class Main {
    private static final Input INPUT = new Input();

    public static void main(String[] args) throws Exception {
        double[][] matrix;
        double[] vector;
        double eps;

        // Метод квадратного корня
        // Input data
        INPUT.setPath("resources/sqrt.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        // Sqrt method
        printTitle("Метод квадратного корня");
        sqrtMethod(matrix, vector);

        // Метод Холецкого
        // Input data
        INPUT.setPath("resources/cholesky.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        // Cholesky method
        printTitle("Метод Холецкого");
        cholesky(matrix, vector);

        // Метод Якоби
        // Input data
        INPUT.setPath("resources/yakoby.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        eps = INPUT.getEps();
        // Cholesky method
        printTitle("Метод Якоби");
        yakoby(matrix, vector, eps);

        // Метод Зейделя
        // Input data
        INPUT.setPath("resources/seidel.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        eps = INPUT.getEps();
        // Cholesky method
        printTitle("Метод Зейделя");
        seidel(matrix, vector, eps);
    }
}