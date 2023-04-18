import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Cereal2 {
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
        int last = n - 1;
        pref[last][data[last][0]] = last;
        choice[last][data[last][0]] = 1;
        for (int i = last - 1; i >= 0; i--) {
            pref[i] = pref[i + 1].clone();
            choice[i] = choice[i + 1].clone();
            int[] cur = pref[i];
            int[] cur2 = choice[i];
            int[] next = data[i];
            if (cur[next[0]] == -1) {
                cur[next[0]] = i;
                cur2[next[0]] = 1;
            } else {
                int hold = cur[next[0]];
                int temp = cur2[next[0]];
                cur[next[0]] = i;
                cur2[next[0]] = 1;
                boolean first = temp == 1;
                int second = data[hold][1];
                if (first && cur[second] == -1) {
                    cur[second] = hold;
                    cur2[second] = 2;
                } else if (first && cur[second] > hold) {
                    fix(data, cur, cur2, hold);
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
        int second = data[hold][1];
        int next = cur[second];
        if (next != -1) {
            if (next > hold) {
                if (choice[second] == 1) {
                    cur[second] = hold;
                    choice[second] = 2;
                    fix(data, cur, choice, next);
                } else {
                    cur[second] = hold;
                    choice[second] = 2;
                }
            }
        } else {
            cur[second] = hold;
            choice[second] = 2;
        }
    }
}
