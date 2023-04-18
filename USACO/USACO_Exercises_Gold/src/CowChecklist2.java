import java.io.*;
import java.util.StringTokenizer;

public class CowChecklist2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("checklist.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int[][] h = new int[H][2];
        int[][] g = new int[G][2];
        boolean[][] points = new boolean[1001][1001];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            h[i][0] = Integer.parseInt(st.nextToken());
            h[i][1] = Integer.parseInt(st.nextToken());
            points[h[i][0]][h[i][1]] = true;
        }
        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(br.readLine());
            g[i][0] = Integer.parseInt(st.nextToken());
            g[i][1] = Integer.parseInt(st.nextToken());
            points[g[i][0]][g[i][1]] = true;
        }

        int[][][] dp = new int[H + 1][G + 1][2]; //0 for h, 1 for g
        for (int i = 0; i <= H; i++) {
            for (int j = 0; j <= G; j++) {
                dp[i][j][0] = 2000000;
                dp[i][j][1] = 2000000;
            }
        }
        dp[1][0][0] = 0;
        for (int i = 2; i <= H; i++) {
            dp[i][0][0] = dp[i - 1][0][0] + findDist(points, h[i - 2], h[i - 1]);
        }
        dp[1][1][1] = findDist(points, h[0], g[0]);
        for (int i = 2; i <= G; i++) {
            dp[1][i][1] = dp[1][i - 1][1] + findDist(points, g[i - 2], g[i - 1]);
        }
        dp[2][1][0] = dp[1][1][1] + findDist(points, g[0], h[1]);
        dp[2][1][1] = dp[2][0][0] + findDist(points, h[1], g[0]);
        for (int i = 3; i <= H; i++) {
            dp[i][1][0] = Math.min(dp[i - 1][1][0] + findDist(points, h[i - 2], h[i - 1]), dp[i - 1][1][1] + findDist(points, g[0], h[i - 1]));
            dp[i][1][1] = dp[i][0][0] + findDist(points, h[i - 1], g[0]);
        }
        for (int i = 2; i <= H; i++) {
            for (int j = 2; j <= G; j++) {
                //add from h cow
                dp[i][j][0] = Math.min(dp[i][j][0], Math.min(dp[i - 1][j][0] + findDist(points, h[i - 2], h[i - 1]), dp[i - 1][j][1] + findDist(points, g[j - 1], h[i - 1])));
                //add from g cow
                dp[i][j][1] = Math.min(dp[i][j][1], Math.min(dp[i][j - 1][0] + findDist(points, h[i - 1], g[j - 1]), dp[i][j - 1][1] + findDist(points, g[j - 2], g[j - 1])));
            }
        }

        pw.println(dp[H][G][0]);
        pw.close();
    }

    private static int findDist(boolean[][] points, int[] from, int[] to) {
        if (from[0] == to[0]) {
            if (from[1] == to[1]) {
                return 0;
            } else if (from[1] < to[1]) {
                int[] start = from.clone();
                int dist = 0;
                for (int i = from[1] + 1; i < to[1]; i++) {
                    if (points[from[0]][i]) {
                        dist += findDistHelper(start, new int[]{from[0], i});
                        start = new int[]{from[0], i};
                    }
                }
                dist += findDistHelper(start, to);
                return dist;
            } else {
                int[] start = to.clone();
                int dist = 0;
                for (int i = to[1] + 1; i < start[1]; i++) {
                    if (points[to[0]][i]) {
                        dist += findDistHelper(start, new int[]{to[0], i});
                        start = new int[]{to[0], i};
                    }
                }
                dist += findDistHelper(start, from);
                return dist;
            }
        } else if (from[0] < to[0]) {
            int[] start;
            int dist = 0;
            if (from[1] == to[1]) {
                start = from.clone();
                for (int i = from[0] + 1; i < to[0]; i++) {
                    if (points[i][from[1]]) {
                        dist += findDistHelper(start, new int[]{i, from[1]});
                        start = new int[]{i, from[1]};
                    }
                }
            } else {
                start = from.clone();
                int x = to[0] - from[0];
                int y = to[1] - from[1];
                double rate = (double) (y) / (x);
                for (int i = 1; i < x; i++) {
                    double change = rate * i;
                    if (Math.abs(change) % 1 == 0.0) {
                        dist += findDistHelper(start, new int[]{from[0] + i, (int) (from[1] + change)});
                        start = new int[]{from[0] + i, (int) (from[1] + change)};
                    }
                }
            }
            dist += findDistHelper(start, to);
            return dist;
        } else {
            int[] start;
            int dist = 0;
            if (to[1] == from[1]) {
                start = to.clone();
                for (int i = to[0] + 1; i < start[0]; i++) {
                    if (points[i][to[1]]) {
                        dist += findDistHelper(start, new int[]{i, to[1]});
                        start = new int[]{i, to[1]};
                    }
                }
            } else {
                start = to.clone();
                int x = from[0] - to[0];
                int y = from[1] - to[1];
                double rate = (double) (y) / (x);
                for (int i = 1; i < x; i++) {
                    double change = rate * i;
                    if (Math.abs(change) % 1 == 0.0) {
                        dist += findDistHelper(start, new int[]{to[0] + i, (int) (to[1] + change)});
                        start = new int[]{to[0] + i, (int) (to[1] + change)};
                    }
                }
            }
            dist += findDistHelper(start, from);
            return dist;
        }
    }

    private static int findDistHelper(int[] from, int[] to) {
        return (to[0] - from[0]) * (to[0] - from[0]) + (to[1] - from[1]) * (to[1] - from[1]);
    }
}
