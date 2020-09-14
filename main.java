public class Main {
    public static void main(String[] args) {
        // Input data
        Input input = new Input("input.txt");
        double[][] matrixA = input.getMatrix();
        double[] vectorB = input.getVector();
        // Cholesky method
        Algorithms.cholesky(matrixA, vectorB);
    }
}