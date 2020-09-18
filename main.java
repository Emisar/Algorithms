public class Main {
    private static final Input INPUT = new Input();
    private static double[][] matrix;
    private static double[] vector;

    public static void main(String[] args) {
        // Метод квадратного корня
        // Input data
        INPUT.setPath("data/sqrt.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        // Sqrt method
        System.out.println("Метод квадратного корня");
        Algorithms.sqrtMethod(matrix, vector);

        // Метод Холецкого
        // Input data
        INPUT.setPath("data/cholesky.txt");
        matrix = INPUT.getMatrix();
        vector = INPUT.getVector();
        // Cholesky method
        System.out.println("Метод Холецкого");
        Algorithms.cholesky(matrix, vector);
    }
}