/*
ID: jerryya2
LANG: JAVA
TASK: sort3
*/

import java.io.*;
import java.util.Arrays;

public class sort3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

        int n = Integer.parseInt(br.readLine());

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }
        br.readLine();

        pw.println(calcSwitches(n, values));
        pw.close();
    }

    public static int calcSwitches (int n, int[] values) {
        int [] sorted = new int[n];
        System.arraycopy(values, 0, sorted, 0, sorted.length);
        Arrays.sort(sorted);

        int exc12 = 0;
        int exc21 = 0;
        int exc13 = 0;
        int exc31 = 0;
        int exc23 = 0;
        int exc32 = 0;

        for (int i = 0; i < n; i++) {
            if (values[i] == 1 && sorted[i] == 2) {
                exc12++;
            }
            else if (values[i] == 2 && sorted[i] == 1) {
                exc21++;
            }
            else if (values[i] == 1 && sorted[i] == 3) {
                exc13++;
            }
            else if (values[i] == 3 && sorted[i] == 1) {
                exc31++;
            }
            else if (values[i] == 2 && sorted[i] == 3) {
                exc23++;
            }
            else if (values[i] == 3 && sorted[i] == 2) {
                exc32++;
            }
        }

        return Math.min(exc12, exc21) + Math.min(exc13, exc31) + Math.min(exc23, exc32) + 2 * (Math.max(exc12, exc21) - Math.min(exc12, exc21));
    }
}
