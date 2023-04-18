import java.io.*;
import java.util.StringTokenizer;

public class CowChecklist6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("checklist.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        long[][] h = new long[H][2];
        long[][] g = new long[G][2];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            h[i][0] = Integer.parseInt(st.nextToken());
            h[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(br.readLine());
            g[i][0] = Integer.parseInt(st.nextToken());
            g[i][1] = Integer.parseInt(st.nextToken());
        }

        double[][][] dp = new double[H + 1][G + 1][2]; //0 for h, 1 for g
        for (int i = 0; i <= H; i++) {
            for (int j = 0; j <= G; j++) {
                dp[i][j][0] = Double.POSITIVE_INFINITY;
                dp[i][j][1] = Double.POSITIVE_INFINITY;
            }
        }
        dp[1][0][0] = 0;
        dp[1][1][1] = findDist(h[0], g[0]);
        for (int i = 2; i <= G; i++) {
            dp[1][i][1] = dp[1][i - 1][1] + findDist(g[i - 2], g[i - 1]);
        }
        dp[2][1][0] = dp[1][1][1] + findDist(g[0], h[1]);
        for (int i = 2; i <= H; i++) {
            dp[i][0][0] = dp[i - 1][0][0] + findDist(h[i - 2], h[i - 1]);
            if (i > 2) {
                dp[i][1][0] = Math.min(dp[i - 1][1][0] + findDist(h[i - 2], h[i - 1]), dp[i - 1][1][1] + findDist(g[0], h[i - 1]));
            }
            dp[i][1][1] = dp[i][0][0] + findDist(h[i - 1], g[0]);
            for (int j = 2; j <= G; j++) {
                //add a h cow
                dp[i][j][0] = Math.min(dp[i - 1][j][0] + findDist(h[i - 2], h[i - 1]), dp[i - 1][j][1] + findDist(g[j - 1], h[i - 1]));
                //add a g cow
                dp[i][j][1] = Math.min(dp[i][j - 1][0] + findDist(h[i - 1], g[j - 1]), dp[i][j - 1][1] + findDist(g[j - 2], g[j - 1]));
            }
        }

        pw.println((long) dp[H][G][0]);
        pw.close();
    }

    private static long findDist(long[] from, long[] to) {
        return ((to[0] - from[0]) * (to[0] - from[0])) + ((to[1] - from[1]) * (to[1] - from[1]));
    }
}
