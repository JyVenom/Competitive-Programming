/*
ID: jerryya2
LANG: JAVA
TASK: spin
*/

import java.io.*;
import java.util.StringTokenizer;

public class spin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("spin.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));

        int[] s = new int[5];
        int[][] wedges = new int[5][360];
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                for (int k = 0; k <= end; k++) {
                    wedges[i][(start + k) % 360] = 1;
                }
            }
        }

        int count = 0;
        int[] at = new int[5];
        while (true) {
            int[][] cur = new int[5][360];
            for (int i = 0; i < 360; i++) {
                for (int j = 0; j < 5; j++) {
                    cur[j][(at[j] + i) % 360] = wedges[j][i];
                }
            }

            boolean good = false;
            for (int i = 0; i < 360; i++) {
                if (cur[0][i] == 1 && cur[1][i] == 1 && cur[2][i] == 1 && cur[3][i] == 1 && cur[4][i] == 1) {
                    pw.println(count);
                    good = true;
                    break;
                }
            }
            if (good) {
                break;
            }

            count++;
            for (int i = 0; i < 5; i++) {
                at[i] = (at[i] + s[i]) % 360;
            }

            if (at[0] == at[1] && at[1] == at[2] && at[2] == at[3] && at[3] == at[4]) {
                pw.println("none");
                break;
            }
        }

        pw.close();
    }
}
