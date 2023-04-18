/*
ID: jerryya2
LANG: JAVA
TASK: job
*/

//Big-O: m1 + m2 + 2n*log(n) + n
//1 <= m1 <= 30
//1 <= m2 <= 30
//1 <= n <= 1000
//Worse case: 20991.5686

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class job7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("job.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int[][] a = new int[m1][2];
        int[][] b = new int[m2][2];
        int prev = 0;
        while (prev < m1) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            for (int i = prev; i < m1; i++) {
                if (st.hasMoreTokens()) {
                    a[i][0] = Integer.parseInt(st.nextToken());
                    a[i][1] = a[i][0];
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
                    b[i][0] = Integer.parseInt(st.nextToken());
                    b[i][1] = b[i][0];
                    prev++;
                }
            }
        }

        int[] an = new int[n];
        int[] bn = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.sort(a, Comparator.comparingInt(arr -> arr[0]));
            an[i] = a[0][0];
            a[0][0] += a[0][1];
            Arrays.sort(b, Comparator.comparingInt(arr -> arr[0]));
            bn[i] = b[0][0];
            b[0][0] += b[0][1];
        }
        int maxA = 0;
        int maxB = 0;
        int N = n - 1;
        for (int i = 0; i < n; i++) {
            maxA = Math.max(maxA, an[i]);
            maxB = Math.max(maxB, an[i] + bn[N - i]);
        }

        pw.println(maxA + " " + maxB);
        pw.close();
    }
}
