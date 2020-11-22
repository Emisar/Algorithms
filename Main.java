import newio.Input;
import tasks.*;

import static newio.Output.*;

public class Main {
    private static final Input INPUT = new Input();

    public static void main(String[] args) throws Exception {
        /*
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

        // Пример 1 - Многочлен Лагранжа
        double input = 0;
        System.out.printf("%nМногочлен Лагранжа%nL(%.0f) = %.2f%n", input, Lab2.lagrange(input));
        // Пример 2 - Погрешность
        input = 0.9;
        System.out.printf("%nПогрешность%ny(x) = log2(x)%nr(%.1f) = %.2f%n", input, Lab2.calcError(0.9));
        // Пример 3 - Многочлен Ньютона
        input = 0;
        System.out.printf("%nМногочлен Ньютона%nN(%.0f) = %.2f%n", input, Lab2.newton(input));
        // Пример 4 - Многочлен Ньютона для равноотстоящих узлов
        input = 0;
        System.out.printf("%nМногочлен Ньютона для равноотстоящих узлов%nN(%.1f) = %.2f%n", input, Lab2.newtonNodes(input));
        // Пример 5 - Приближение функции методом наименьших квадратов
        input = 0;
        System.out.printf("%nПриближение функции методом наименьших квадратов%nfi(%.1f) = %.2f%n", input, Lab2.minSqrt(input));
        // Пример 6 - Сплайн-интерполяция
        input = 1.5;
        System.out.printf("%nСплайн-интерполяция%ns(%.1f) = %.2f%n", input, Lab2.spline(input));
        */

        // Пример 1 - Формула Симпсона
        double[] vectorX = { 0.0, 0.5, 1.0 };
        double[] vectorY = { -1.0, 1.0, 2.5 };
        System.out.printf("%nФормула Симпсона%nI = %.2f %n", Lab3.simpson(vectorX, vectorY, (Double x) -> Math.pow(x, 2)) );

        // Пример 2 - Формула средних прямоугольников
        System.out.printf("%nФормула средних прямоугольников%nR = %.2f %n", Lab3.rectangle() );
        
        // Пример 3 - Квадратурная формула Гаусса
        double[][] result = Lab3.gauss();
        System.out.println("\nКвадратурная формула Гаусса");
        System.out.printf("Узлы квадратуры: x1 = %.2f, x2 = %.2f %n", result[0][0], result[0][1]);
        System.out.printf("Коэффициенты квадратуры: A1 = %.2f, A2 = %.2f %n", result[1][0], result[1][1]);
        System.out.printf("Итоговый вид квадратуры: I[0, 1](f(x) * dx) = %.2f * f(%.2f) + %.2f * f(%.2f) %n", result[1][0], result[0][0], result[1][1], result[0][1]);
    }
}