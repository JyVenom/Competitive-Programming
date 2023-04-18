/*
ID: jerryya2
LANG: JAVA
TASK: game1
*/

import java.io.*;
import java.util.StringTokenizer;

public class game1 {
    public static void main(String[] args) throws IOException {
//        long start = System.currentTimeMillis();
//        for (int z = 0; z < 10000; z++) {
            BufferedReader br = new BufferedReader(new FileReader("game1.in"));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));

            int n = Integer.parseInt(br.readLine());
            int[][] best = new int[n + 2][2];
            int[] t = new int[n + 2];
            for (int i = 1; i <= n; ) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    t[i] = t[i - 1] + Integer.parseInt(st.nextToken());
                    i++;
                }
            }
            br.close();

            for (int l = 1; l <= n; l++) {
                for (int i = 1; i + l <= n + 1; i++) {
                    best[i][l % 2] = t[i + l - 1] - t[i - 1] - Math.min(best[i + 1][(l - 1) % 2], best[i][(l - 1) % 2]);
                }
            }

            pw.println(best[1][n % 2] + " " + (t[n] - best[1][n % 2]));
            pw.close();
//        }
//        System.out.println(System.currentTimeMillis() - start);
    }
}



