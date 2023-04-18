import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SwitchingOnTheLights4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> switches = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (!switches.containsKey(x)) {
                switches.put(x, new HashMap<>());
            }
            if (!switches.get(x).containsKey(y)) {
                switches.get(x).put(y, new ArrayList<>());
            }
            switches.get(x).get(y).add(new int[]{a, b});
        }


        boolean[][] visited = new boolean[n][n];
        boolean[][] on = new boolean[n][n];
        on[0][0] = true;
        dfs(switches, visited, on, new int[]{0, 0}, n - 1);
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (on[i][j]) {
                    count++;
                }
            }
        }

        pw.println(count);
        pw.close();
    }

    private static void dfs(HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> switches, boolean[][] visited, boolean[][] on, int[] cur, int N) {
        visited[cur[0]][cur[1]] = true;

        if (switches.containsKey(cur[0]) && switches.get(cur[0]).containsKey(cur[1])) {
            for (int[] next : switches.get(cur[0]).get(cur[1])) {
                if (!on[next[0]][next[1]]) {
                    on[next[0]][next[1]] = true;
                    boolean up = next[1] < N && visited[next[0]][next[1] + 1];
                    boolean right = next[0] < N && visited[next[0] + 1][next[1]];
                    boolean down = next[1] > 0 && visited[next[0]][next[1] - 1];
                    boolean left = next[0] > 0 && visited[next[0] - 1][next[1]];
                    if (up || right || down || left) {
                        dfs(switches, visited, on, next, N);
                    }
                }
            }
        }

        if (cur[1] < N && on[cur[0]][cur[1] + 1] && !visited[cur[0]][cur[1] + 1]) {
            dfs(switches, visited, on, new int[]{cur[0], cur[1] + 1}, N);
        }
        if (cur[0] < N && on[cur[0] + 1][cur[1]] && !visited[cur[0] + 1][cur[1]]) {
            dfs(switches, visited, on, new int[]{cur[0] + 1, cur[1]}, N);
        }
        if (cur[1] > 0 && on[cur[0]][cur[1] - 1] && !visited[cur[0]][cur[1] - 1]) {
            dfs(switches, visited, on, new int[]{cur[0], cur[1] - 1}, N);
        }
        if (cur[0] > 0 && on[cur[0] - 1][cur[1]] && !visited[cur[0] - 1][cur[1]]) {
            dfs(switches, visited, on, new int[]{cur[0] - 1, cur[1]}, N);
        }
    }
}
