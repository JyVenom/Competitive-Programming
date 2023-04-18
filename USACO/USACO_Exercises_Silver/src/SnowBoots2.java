import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SnowBoots2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] path = new int[n];
        int[][] boots = new int[b][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            path[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(br.readLine());
            boots[i][0] = Integer.parseInt(st.nextToken());
            boots[i][1] = Integer.parseInt(st.nextToken());
        }

        pw.println(bfs(n, b, path, boots));
        pw.close();
    }

    public static int bfs(int n, int b, int[] path, int[][] boots) {
        boolean[][] used = new boolean[n][b];
        Arrays.fill(used[0], true);

        ArrayList<Integer> q = new ArrayList<>();
        for (int i = 0; i < b; i++) q.add(i);

        while (q.size() > 0) {
            int cur = q.get(0);
            q.remove(0);
            int step = cur / b;
            int bNum = cur % b;

            for (int i = 1; step + i < n && i <= boots[bNum][1]; i++) {
                if (path[step + i] <= boots[bNum][0] && !used[step + i][bNum]) {
                    q.add(b * (step + i) + bNum);
                    used[step + i][bNum] = true;
                }
            }

            for (int i = bNum + 1; i < b; i++) {
                if (boots[i][0] >= path[step] && !used[step][i]) {
                    q.add(b * step + i);
                    used[step][i] = true;
                }
            }
        }

        for (int i = 0; i < b; i++)
            if (used[n - 1][i])
                return i;

        return -1;
    }
}