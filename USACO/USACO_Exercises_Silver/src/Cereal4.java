import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Cereal4 {
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

        ArrayList<Integer> ans = new ArrayList<>();
        int[] cur = new int[m];
        int[] cur2 = new int[m];
        Arrays.fill(cur, -1);
        int last = n - 1;
        cur[data[last][0]] = last;
        cur2[data[last][0]] = 1;
        ans.add(1);
        int count = 1;
        for (int i = last - 1; i >= 0; i--) {
            int[] next = data[i];
            if (cur[next[0]] == -1) {
                cur[next[0]] = i;
                cur2[next[0]] = 1;
                count++;
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
                    count++;
                } else if (first && cur[second] > hold) {
                    count = fix(data, cur, cur2, hold, count);
                }
            }

            ans.add(count);
        }

        for (int i = ans.size() - 1; i >= 0; i--) {
            pw.println(ans.get(i));
        }
        pw.close();
    }

    private static int fix(int[][] data, int[] cur, int[] choice, int hold, int count) {
        int second = data[hold][1];
        int next = cur[second];
        if (next != -1) {
            if (next > hold) {
                if (choice[second] == 1) {
                    cur[second] = hold;
                    choice[second] = 2;
                    count = fix(data, cur, choice, next, count);
                } else {
                    cur[second] = hold;
                    choice[second] = 2;
                }
            }
        } else {
            cur[second] = hold;
            choice[second] = 2;
            count++;
        }

        return count;
    }
}
