/*
ID: jerryya2
LANG: JAVA
PROG: numtri
*/

import java.io.*;
import java.util.StringTokenizer;

public class numtri2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

        int r = Integer.parseInt(br.readLine());

        int[][] tri = new int[r][r];
        for (int i = 0; i < r; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        int[][] data = new int[r][r];
        pw.println(DFS(tri, data, 0, 0));
        pw.close();
    }

    private static int DFS(int[][] tri, int[][] data, int i, int j) {
        int sum = tri[i][j];
        data[i][j] = sum;
        if (i == tri.length - 1) {
            return sum;
        }

        int sum2 = sum;
        if (data[i + 1][j] == 0) {
            sum += DFS(tri, data, i + 1, j);
        } else {
            sum += data[i + 1][j];
        }
        if (data[i + 1][j + 1] == 0) {
            sum2 += DFS(tri, data, i + 1, j + 1);
        } else {
            sum2 += data[i + 1][j + 1];
        }

        int max = Math.max(sum, sum2);
        data[i][j] = max;
        return max;
    }
}
