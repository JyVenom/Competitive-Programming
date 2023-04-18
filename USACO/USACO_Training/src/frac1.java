/*
ID: jerryya2
LANG: JAVA
TASK: frac1
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class frac1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));

        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            pw.println("0/1");
            pw.println("1/1");
        }
        else {
            ArrayList<double[]> fracs = new ArrayList<>();
            for (int num = 1; num < n; num++) {
                for (int den = num; den <= n; den++) {
                    double[] frac = simplify(num, den);
                    fracs.add(frac);
                }
            }

            fracs.sort(Comparator.comparingDouble(a -> a[2]));
            pw.println("0/1");
            double[] first = fracs.get(0);
            pw.println((int) first[0] + "/" + (int) first[1]);
            for (int i = 1; i < fracs.size(); i++) {
                if (fracs.get(i)[2] == fracs.get(i - 1)[2]) {
                    fracs.remove(i);
                    i--;
                } else {
                    double[] frac = fracs.get(i);
                    pw.println((int) frac[0] + "/" + (int) frac[1]);
                }
            }
        }
        pw.close();
    }

    public static double gcm(double a, double b) {
        return b == 0 ? a : gcm(b, a % b);
    }

    public static double[] simplify(int a, int b) {
        double gcm = gcm(a, b);
        double[] fraction = new double[3];
        fraction[0] = a / gcm;
        fraction[1] = b / gcm;
        fraction[2] = fraction[0] / fraction[1];
        return fraction;
    }
}
