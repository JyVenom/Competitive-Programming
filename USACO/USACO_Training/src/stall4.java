/*
ID: jerryya2
LANG: JAVA
TASK: stall4
*/

import java.io.*;
import java.util.StringTokenizer;

public class stall4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("stall4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] graph = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            for (int j = 0; j < s; j++) {
                int p = Integer.parseInt(st.nextToken()) - 1;
                graph[i][p] = true;
            }
        }

        stall4 s = new stall4();

        pw.println(s.maxBPM(graph, m, n));
        pw.close();
    }

    boolean bpm(boolean[][] graph, int u, boolean[] seen, int[] matchR, int n) {
        for (int v = 0; v < n; v++) {
            if (graph[u][v] && !seen[v]) {
                seen[v] = true;

                if (matchR[v] < 0 || bpm(graph, matchR[v], seen, matchR, n)) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    int maxBPM(boolean[][] graph, int m, int n) {
        int[] matchR = new int[n];

        for (int i = 0; i < n; ++i) {
            matchR[i] = -1;
        }

        int result = 0;
        for (int u = 0; u < m; u++) {
            boolean[] seen = new boolean[n];
            for (int i = 0; i < n; ++i) {
                seen[i] = false;
            }

            if (bpm(graph, u, seen, matchR, n)) {
                result++;
            }
        }
        return result;
    }
}
