import java.util.function.Function;

import tasks.*;

public class Main {
    public static void main(String[] args) {
        Function<Double, Double> func = (Double x) -> 1 / (1 + 10 * x);

        // Пример 1 - Формула Симпсона
        System.out.printf("%nФормула Симпсона%nI = %.2f %n", Lab3.simpson(0, 1, func));

        // Пример 2 - Формула средних прямоугольников
        System.out.printf("%nПогрешность формулы средних прямоугольников%nR = %.2f %n", Lab3.rectangle() );
        
        // Пример 3 - Квадратурная формула Гаусса
        System.out.printf("%nКвадратурная формула Гаусса%nI = %.2f %n", Lab3.gauss(func));
    }
}