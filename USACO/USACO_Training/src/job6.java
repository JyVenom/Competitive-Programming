/*
ID: jerryya2
LANG: JAVA
TASK: job
*/

//Big-O: couple (2 - 6) * n
//1 <= n <= 1000

import java.io.*;
import java.util.StringTokenizer;

public class job6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("job.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int[] ta = new int[m1];
        int[] tb = new int[m2];
        int[] a = new int[m1];
        int[] b = new int[m2];
        int[] an = new int[n];
        int[] bn = new int[n];
        int prev = 0;
        while (prev < m1) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            for (int i = prev; i < m1; i++) {
                if (st.hasMoreTokens()) {
                    a[i] = Integer.parseInt(st.nextToken());
                    ta[i] = a[i];
                    prev++;
                }
            }
        }
        prev = 0;
        while (prev < m2) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            for (int i = 0; i < m2; i++) {
                if (st.hasMoreTokens()) {
                    b[i] = Integer.parseInt(st.nextToken());
                    tb[i] = b[i];
                    prev++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int k = selectMin(a);
            an[i] = a[k];
            a[k] += ta[k];
            k = selectMin(b);
            bn[i] = b[k];
            b[k] += tb[k];
        }
        int maxA = 0;
        int maxB = 0;
        int N1 = n - 1;
        for (int i = 0; i < n; i++) {
            maxA = Math.max(maxA, an[i]);
            maxB = Math.max(maxB, an[i] + bn[N1 - i]);
        }

        pw.println(maxA + " " + maxB);
        pw.close();
    }

    static int selectMin(int[] ar) {
        int k = 0;
        for (int i = 1; i < ar.length; i++) {
            if (ar[i] < ar[k]) {
                k = i;
            }
        }
        return k;
    }
}
