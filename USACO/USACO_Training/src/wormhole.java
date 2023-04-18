/*
ID: jerryya2
LANG: JAVA
PROG: wormhole
*/

import java.io.*;
import java.util.StringTokenizer;

public class wormhole {
    private static int n;
    private static int[] partner;
    private static int[] nextRight;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

        n = Integer.parseInt(br.readLine());
        partner = new int[n + 1];
        nextRight = new int[n + 1];

        int[][] points = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (points[j][0] > points[i][0] && points[i][1] == points[j][1]) {
                    if (nextRight[i] == 0 || points[j][0] - points[i][0] < points[nextRight[i]][0] - points[i][0]) {
                        nextRight[i] = j;
                    }
                }
            }
        }

        pw.println(solve());
        pw.close();
    }

    private static boolean cycle_exists() {
        for (int start = 1; start <= n; start++) {
            int pos = start;
            for (int j = 0; j < n; j++) {
                pos = nextRight[partner[pos]];
            }
            if (pos != 0) {
                return true;
            }
        }
        return false;
    }

    private static int solve() {
        int i;
        int total = 0;
        for (i = 1; i <= n; i++) {
            if (partner[i] == 0) {
                break;
            }
        }

        if (i > n) {
            if (cycle_exists()) {
                return 1;
            }
            else {
                return 0;
            }
        }

        for (int j = i + 1; j <= n; j++) {
            if (partner[j] == 0) {
                partner[i] = j;
                partner[j] = i;
                total += solve();
                partner[i] = partner[j] = 0;
            }
        }
        return total;
    }
}
