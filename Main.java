import newio.Input;
import tasks.*;

import static newio.Output.*;

public class Main {
    private static final Input INPUT = new Input();

    public static void main(String[] args) throws Exception {
        double[][] matrix;
        double[] vector;
        double eps;

        /*
        // Метод квадратного корня
        // Input data
        INPUT.setPath("resources/sqrt.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        // Sqrt method
        printTitle("Метод квадратного корня");
        Algorithms.sqrtMethod(matrix, vector);

        // Метод Холецкого
        // Input data
        INPUT.setPath("resources/cholesky.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        // Cholesky method
        printTitle("Метод Холецкого");
        Algorithms.cholesky(matrix, vector);

        // Метод Якоби
        // Input data
        INPUT.setPath("resources/yakoby.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        eps = INPUT.getEps();
        // Cholesky method
        printTitle("Метод Якоби");
        Algorithms.yakoby(matrix, vector, eps);

        // Метод Зейделя
        // Input data
        INPUT.setPath("resources/seidel.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        eps = INPUT.getEps();
        // Cholesky method
        printTitle("Метод Зейделя");
        Algorithms.seidel(matrix, vector, eps);
        */

        System.out.println(Lab2.lagrange(4));
        System.out.println(Lab2.newton(3));
        System.out.println(Lab2.newtonNodes(0.4));
        for (double val : Lab2.minSqrt(-1)) {
          System.out.print(val + " ");
        }
        
    }
}