import static newio.Output.*;

import newio.Input;
import tasks.*;

public class Main {
    private static final Input INPUT = new Input();

    public static void main(String[] args) throws Exception {
        double[][] matrix;
        double[] vector;
        double eps;

        // Метод квадратного корня
        // Input data
        INPUT.setPath("data/sqrt.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        // Sqrt method
        printTitle("Метод квадратного корня");
        Algorithms.sqrtMethod(matrix, vector);

        // Метод Холецкого
        // Input data
        INPUT.setPath("data/cholesky.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        // Cholesky method
        printTitle("Метод Холецкого");
        Algorithms.cholesky(matrix, vector);

        // Метод Якоби
        // Input data
        INPUT.setPath("data/yakoby.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        eps = INPUT.getEps();
        // Cholesky method
        printTitle("Метод Якоби");
        Algorithms.yakoby(matrix, vector, eps);

        // Метод Зейделя
        // Input data
        INPUT.setPath("data/seidel.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        eps = INPUT.getEps();
        // Cholesky method
        printTitle("Метод Зейделя");
        Algorithms.seidel(matrix, vector, eps);
    }
}