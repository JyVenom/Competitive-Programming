/*
ID: jerryya2
LANG: JAVA
PROG: wormhole
*/

import java.io.*;
import java.util.StringTokenizer;

public class wormhole2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

        int n = Integer.parseInt(br.readLine());

        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int count = 0;
        int[][] pairs = new int[n/2][2];
        pairs[0][0] = 0;
        for (int i = 1; i < n; i++) {
            pairs[0][1] = i;
        }
        if (n >= 4) {

        }
        if (n >= 6) {

        }
        if (n >= 8) {

        }
        if (n >= 10) {

        }
        if (n >= 12) {

        }

        pw.println(count);
        pw.close();
    }
}
