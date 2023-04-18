/*
ID: jerryya2
LANG: JAVA
TASK: game1
*/

import java.io.*;
import java.util.StringTokenizer;

public class game12 {
    public static void main(String[] args) throws IOException {
//        long start = System.currentTimeMillis();
//        for (int z = 0; z < 10000; z++) {
            BufferedReader br = new BufferedReader(new FileReader("game1.in"));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));

            int n = Integer.parseInt(br.readLine());
            int[][] sums = new int[n + 1][n + 1], dp = new int[n + 1][n + 1];
            for (int t = 0; t < n; ) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    t++;
                    sums[t][t] = Integer.parseInt(st.nextToken());
                    dp[t][t] = sums[t][t];
                }
            }
            br.close();

            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    sums[i][j] = sums[i][j - 1] + dp[j][j];
                }
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; i + j <= n; j++) {
                    dp[j][j + i] = sums[j][j + i] - Math.min(dp[j][j + i - 1], dp[j + 1][j + i]);
                }
            }

            pw.println(dp[1][n] + " " + (sums[1][n] - dp[1][n]));
            pw.close();
//        }
//        System.out.println(System.currentTimeMillis() - start);
    }
}