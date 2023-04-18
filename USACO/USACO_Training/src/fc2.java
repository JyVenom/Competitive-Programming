/*
ID: jerryya2
LANG: JAVA
TASK: fc
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class fc2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fc.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fc.out")));

        int n = Integer.parseInt(br.readLine());
        double[][] points = new double[n][2];
        for (int i = 0; i < n; i++) {
            points[i] = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        }

        int midX = 0;
        int midY = 0;
        for (double[] point : points) {
            midX += (point[0] / n);
            midY += (point[1] / n);
        }
        double[][] angle = new double[n][2];
        for (int i = 0; i < n; i++) {
            angle[i][0] = Math.atan2(points[i][1] - midY, points[i][0] - midX);
            angle[i][1] = i;
        }
        Arrays.sort(angle, Comparator.comparingDouble(arr -> arr[0]));
        ArrayList<double[]> hull = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            hull.add(new double[2]);
        }
//        hull.set(0, points[(int) angle[0][1]]);
//        hull.set(1, points[(int) angle[1][1]]);
//        int hullPos = 2;
        int hullPos = 0;
        int N = n - 1;
        for (int i = 0; i < N; i++) {
            double[] p = points[(int) angle[i][1]];
            while (hullPos > 1 && zCrossProd(minus(hull.get(hullPos - 2), hull.get(hullPos - 1)), minus(hull.get(hullPos - 1), p)) < 0) {
                hullPos--;
            }
            hull.set(hullPos, p);
            hullPos++;
        }
        double[] p = points[(int) angle[N][1]];
        while (hullPos > 1 && zCrossProd(minus(hull.get(hullPos - 2), hull.get(hullPos - 1)), minus(hull.get(hullPos - 1), p)) < 0) {
            hullPos--;
        }
        int hullStart = 0;
        boolean flag;
        do {
            flag = false;
            if (hullPos - hullStart >= 2 && zCrossProd(minus(p, hull.get(hullPos - 1)), minus(hull.get(hullStart), p)) < 0) {
                p = hull.get(hullPos - 1);
                hullPos--;
                flag = true;
            }
            if (hullPos - hullStart >= 2 && zCrossProd(minus(hull.get(hullStart), p), minus(hull.get(hullStart + 1), hull.get(hullStart))) < 0) {
                hullStart++;
                flag = true;
            }
        } while (flag);
        hull.set(hullPos, p);
//        hullPos++;
        double sum = 0;
        for (int i = 1; i < n; i++) {
            if (Arrays.equals(hull.get(i), new double[]{0, 0})) {
                double ac = Math.abs(hull.get(0)[1] - hull.get(i - 1)[1]);
                double cb = Math.abs(hull.get(0)[0] - hull.get(i - 1)[0]);
                sum += Math.hypot(ac, cb);
                break;
            }
            double ac = Math.abs(hull.get(i)[1] - hull.get(i - 1)[1]);
            double cb = Math.abs(hull.get(i)[0] - hull.get(i - 1)[0]);
            sum += Math.hypot(ac, cb);
        }

        String s = String.format("%.2f", sum);
        pw.println(s);
        pw.close();
    }

    private static double zCrossProd(double[] u, double[] v) {
        return u[0] * v[1] - u[1] * v[0];
    }

    private static double[] minus(double[] a, double[] b) {
        double[] ans = new double[2];
        ans[0] = a[0] - b[0];
        ans[1] = a[1] - b[1];
        return ans;
    }
}
