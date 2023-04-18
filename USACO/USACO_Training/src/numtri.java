/*
ID: jerryya2
LANG: JAVA
PROG: numtri
*/

import java.io.*;
import java.util.StringTokenizer;

public class numtri {
    public static void main(String[] args) throws IOException {
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

        for (int i = r - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                tri[i][j] += Math.max(tri[i + 1][j], tri[i + 1][j + 1]);
            }
        }

        pw.println(tri[0][0]);
        pw.close();
    }
}
