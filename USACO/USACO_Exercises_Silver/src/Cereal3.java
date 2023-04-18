import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Cereal3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken()) - 1;
            data[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
        br.close();

        int[][] pref = new int[n][m];
        int[][] choice = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(pref[i], -1);
        }
        pref[n - 1][data[n - 1][0]] = n - 1;
        choice[n - 1][data[n - 1][0]] = 1;
        for (int i = n - 1 - 1; i >= 0; i--) {
            pref[i] = pref[i + 1].clone();
            choice[i] = choice[i + 1].clone();
            if (pref[i][data[i][0]] == -1) {
                pref[i][data[i][0]] = i;
                choice[i][data[i][0]] = 1;
            } else {
                pref[i][data[i][0]] = i;
                choice[i][data[i][0]] = 1;
                boolean first = choice[i][data[i][0]] == 1;
                if (first && pref[i][data[pref[i][data[i][0]]][1]] == -1) {
                    pref[i][data[pref[i][data[i][0]]][1]] = pref[i][data[i][0]];
                    choice[i][data[pref[i][data[i][0]]][1]] = 2;
                } else if (first && pref[i][data[pref[i][data[i][0]]][1]] > pref[i][data[i][0]]) {
                    fix(data, pref[i], choice[i], pref[i][data[i][0]]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (pref[i][j] != -1) {
                    count++;
                }
            }
            pw.println(count);
        }
        pw.close();
    }

    private static void fix(int[][] data, int[] cur, int[] choice, int hold) {
        if (cur[data[hold][1]] != -1) {
            if (cur[data[hold][1]] > hold) {
                if (choice[data[hold][1]] == 1) {
                    cur[data[hold][1]] = hold;
                    choice[data[hold][1]] = 2;
                    fix(data, cur, choice, cur[data[hold][1]]);
                } else {
                    cur[data[hold][1]] = hold;
                    choice[data[hold][1]] = 2;
                }
            }
        } else {
            cur[data[hold][1]] = hold;
            choice[data[hold][1]] = 2;
        }
    }
}
