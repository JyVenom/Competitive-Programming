//3N + log(n) + 2 * log(n/2) + n^2 / 2 + 2 * n^2 / 4
//2 <= N <= 50,000
//150000 + 15 + 30 + 1250000000 + 625000000 = 1875150045

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HighCardLowCard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));

        int n = Integer.parseInt(br.readLine());
        int N = n / 2;
        boolean[] used = new boolean[(2 * n) + 1];
        ArrayList<Integer> b = new ArrayList<>();
        ArrayList<Integer> e1 = new ArrayList<>();
        ArrayList<Integer> e2 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            used[num] = true;
            e1.add(num);
        }
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            used[num] = true;
            e2.add(num);
        }
        for (int i = 1; i < used.length; i++) {
            if (!used[i]) {
                b.add(i);
            }
        }

        boolean[][] graph1 = new boolean[N][N];
        boolean[][] graph2 = new boolean[N][N];
        Collections.sort(b);
        Collections.sort(e1);
        Collections.sort(e2);
        Collections.reverse(e2);
        ArrayList<Integer> b1 = new ArrayList<>(b.subList(N, n));
        ArrayList<Integer> b2 = new ArrayList<>(b.subList(0, N));
        Collections.reverse(b2);
        int start = 0;
        for (int i = 0; i < N; i++) {
            int cur = b1.get(i);
            Arrays.fill(graph1[i], 0, start, true);
            for (int j = start; j < N; j++) {
                if (cur > e1.get(j)) {
                    graph1[i][j] = true;
                    start++;
                } else {
                    break;
                }
            }
        }
        start = 0;
        for (int i = 0; i < N; i++) {
            int cur = b2.get(i);
            Arrays.fill(graph2[i], 0, start, true);
            for (int j = start; j < N; j++) {
                if (cur < e2.get(j)) {
                    graph2[i][j] = true;
                    start++;
                } else {
                    break;
                }
            }
        }

        int ans = maxBPM(graph1, N) + maxBPM(graph2, N);

        pw.println(ans);
        pw.close();
    }

    private static int maxBPM(boolean[][] graph, int n) {
        int[] matchR = new int[n];

        for (int i = 0; i < n; ++i) {
            matchR[i] = -1;
        }

        int result = 0;
        for (int u = 0; u < n; u++) {
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

    private static boolean bpm(boolean[][] graph, int u, boolean[] seen, int[] matchR, int n) {
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
}
