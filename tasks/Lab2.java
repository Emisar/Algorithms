package tasks;

import java.util.List;
import java.util.ArrayList;

public class Lab2 {
    private Lab2() {}

    public static double lagrange(double x) {
        double[] vectorX = {1, 4 ,5, 6};
        double[] vectorY = {2, 3, 2, 3};

        int vectorLength = vectorX.length;
        double[] vectorL = new double[vectorLength];

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

        double result = 0.0;
        for (int i = 0; i < vectorX.length; i++) {
            result += vectorY[i] * vectorL[i];
        }

        return result;
    }

    public static double newton(double x) {
        double[] vectorX = {1, 2, 4, 6};
        double[] vectorY = {2, -1, -2, -6};

        List<double[]> subList = new ArrayList<>();

        for (int i = 0; i < vectorX.length; i++) {
            double[] sub = new double[vectorX.length - 1 - i];
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

        double result = vectorY[0];
        for (int i = 0; i < subList.size() - 1; i++) {
            double prod = subList.get(i)[0];
            for (int k = 0; k <= i; k++) {
                prod *= x - vectorX[k];
            }
            result += prod;
        }

        return result;
    }
}
