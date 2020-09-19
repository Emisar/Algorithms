package newio;

import java.io.*;

import exceptions.EpsilonIsNotSetException;

public class Input {
    private String path;
    private String[] data;

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
        this.data = readFile().split("\\r?\\n");
    }

    public String[] getData() { return data; }

    public double[][] getMatrix() {
        int n = Integer.parseInt(data[0]);
        double[][] result = new double[n][n];

        for (int i = 1; i <= n; i++) {
            String[] numbers = data[i].split(" ");
            for (int j = 0; j < numbers.length; j++) {
                result[i - 1][j] = Double.parseDouble(numbers[j]);
            }
        }

        return result;
    }

    public double[] getVector() {
        int n = Integer.parseInt(data[0]);
        double[] result = new double[n];

        String[] numbers = data[n + 1].split(" ");
        for (int i = 0; i < numbers.length; i++) {
            result[i] = Double.parseDouble(numbers[i]);
        }

        return result;
    }

    public double getEps() throws EpsilonIsNotSetException {
        int n = Integer.parseInt(data[0]);
        if (data.length > n + 2) {
            return Double.parseDouble(data[data.length - 1]);
        }
        else {
            throw new EpsilonIsNotSetException();
        }
    }
}
