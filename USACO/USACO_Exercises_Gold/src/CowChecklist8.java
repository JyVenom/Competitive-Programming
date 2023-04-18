import java.io.*;
import java.util.StringTokenizer;

public class CowChecklist8 {
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

        long[][][] dp = new long[H + 1][G + 1][2]; //0 for h, 1 for g
        long half = Long.MAX_VALUE / 2;
        for (int i = 0; i <= H; i++) {
            for (int j = 0; j <= G; j++) {
                dp[i][j][0] = half;
                dp[i][j][1] = half;
            }
        }
        dp[1][0][0] = 0;
        dp[1][1][1] = findDist(h[0], g[0]);
        for (int i = 2; i <= G; i++) {
            int I = i - 1;
            dp[1][i][1] = dp[1][I][1] + findDist(g[i - 2], g[I]);
        }
        dp[2][1][0] = dp[1][1][1] + findDist(g[0], h[1]);
        for (int i = 2; i <= H; i++) {
            int I = i - 1;
            int I2 = i - 2;
            dp[i][0][0] = dp[I][0][0] + findDist(h[I2], h[I]);
            if (i > 2) {
                dp[i][1][0] = Math.min(dp[I][1][0] + findDist(h[I2], h[I]), dp[I][1][1] + findDist(g[0], h[I]));
            }
            dp[i][1][1] = dp[i][0][0] + findDist(h[I], g[0]);
            for (int j = 2; j <= G; j++) {
                int J = j - 1;
                //add a h cow
                dp[i][j][0] = Math.min(dp[I][j][0] + findDist(h[I2], h[I]), dp[I][j][1] + findDist(g[J], h[I]));
                //add a g cow
                dp[i][j][1] = Math.min(dp[i][J][0] + findDist(h[I], g[J]), dp[i][J][1] + findDist(g[j - 2], g[j - 1]));
            }
        }

        pw.println(dp[H][G][0]);
        pw.close();
    }

    private static long findDist(long[] from, long[] to) {
        long x = to[0] - from[0];
        long y = to[1] - from[1];
        return (x * x) + (y * y);
    }
}
