import java.io.*;

public class Input {
    private String path;
    private String data;

    public Input(String path) {
        setPath(path);
    }

    private String readFile() {
        StringBuilder result = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String temp;
            while ((temp = br.readLine()) != null) {
                result.append(temp).append("\n");
            }
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result.toString();
    }

    public void setPath(String path) { 
        this.path = path;
        this.data = readFile();
    }

    public String getData() { return data; }

    public double[][] getMatrix() {
        String[] lines = data.split("\\r?\\n");

        int n = Integer.parseInt(lines[0]);
        double[][] result = new double[n][n];

        for (int i = 1; i <= n; i++) {
            String[] numbers = lines[i].split(" ");
            for (int j = 0; j < numbers.length; j++) {
                result[i - 1][j] = Double.parseDouble(numbers[j]);
            }
        }

        return result;
    }

    public double[] getVector() {
        String[] lines = data.split("\\r?\\n");

        int n = Integer.parseInt(lines[0]);
        double[] result = new double[n];

        String[] numbers = lines[n + 1].split(" ");
        for (int i = 0; i < numbers.length; i++) {
            result[i] = Double.parseDouble(numbers[i]);
        }

        return result;
    }
}
