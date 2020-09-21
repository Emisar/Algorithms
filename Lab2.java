import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //System.out.println(lagrandzh(4));
        System.out.println(nuton(3));
    }

    public static double lagrandzh(double x) {
        double[] vectorX = {1, 4 ,5, 6};
        double[] vectorY = {2, 3, 2, 3};

        int vectorLength = vectorX.length;
        double[] vectorL = new double[vectorLength];
        double L = 0.0;

        for (int i = 0; i < vectorLength; i++) {
            double num = 1.0;
            double denom = 1.0;

            for (int k = 0; k < vectorLength; k++) {
                if (k == i) continue;
                num *= x - vectorX[k];
                denom *= vectorX[i] - vectorX[k];
            }
            vectorL[i] = num / denom;
        }

        for (int i = 0; i < vectorX.length; i++) {
            L += vectorY[i] * vectorL[i];
        }

        return L;
    }

    public static double nuton (double x) {
        double[] vectorX = {1, 2, 4, 6};
        double[] vectorY = {2, -1, -2, -6};

        int k = vectorX.length;

        List<double[]> subList = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            double[] sub = new double[k - 1 - i];
            if (i == 0) {
                for (int j = 0; j < sub.length; j++) {
                    sub[j] = (vectorY[j + 1] - vectorY[j]) / (vectorX[j + 1] - vectorX[j]);
                }
            }
            else {
                for (int j = 0; j < sub.length; j++) {
                    sub[j] = (subList.get(i - 1)[j + 1] - subList.get(i - 1)[j]) / (vectorX[j + 1 + i] - vectorX[j]);
                }
            }
            subList.add(sub);
        }

        subList.stream().forEach((sub)->{
            for (double val : sub) {
                System.out.print(val + " ");
            }
            System.out.println();
        });

        return -1;
    }
}