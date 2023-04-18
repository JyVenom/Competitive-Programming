/*
ID: jerryya2
LANG: JAVA
TASK: concom
*/

import java.io.*;
import java.util.StringTokenizer;

public class concom2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("concom.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));

        int n = Integer.parseInt(br.readLine());
        int[][] all = new int[100][100];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            all[a - 1][b - 1] = p;
        }
        br.close();

        for (int i = 0; i < 100; i++) {
            add(all, i, i, new boolean[100]);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (j == i) {
                    continue;
                }
                if (all[i][j] > 50) {
                    pw.println((i + 1) + " " + (j + 1));
                }
            }
        }
        pw.close();
    }

    private static void add(int[][] all, int top, int cur, boolean[] visited) {
        visited[cur] = true;
        for (int i = 0; i < 100; i++) {
            int val = all[cur][i];
            if (val > 0) {
                if (cur != top) {
                    all[top][i] += val;
                }
                if (!visited[i] && val > 50) {
                    add(all, top, i, visited);
                }
            }
        }
    }
}
