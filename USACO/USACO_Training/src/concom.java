/*
ID: jerryya2
LANG: JAVA
TASK: concom
*/

import java.io.*;
import java.util.StringTokenizer;

public class concom {
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

        boolean cont = true;
        boolean[][] visited = new boolean[100][100];
        while (cont) {
            cont = false;
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (!visited[i][j] && i != j && all[i][j] > 50) {
                        visited[i][j] = true;
                        cont = true;
                        for (int k = 0; k < 100; k++) {
                            if (i != k && j != k) all[i][k] += all[j][k];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (Math.abs(all[i][j]) > 50 && i != j) pw.println((i + 1) + " " + (j + 1));
            }
        }
        pw.close();
    }
}
