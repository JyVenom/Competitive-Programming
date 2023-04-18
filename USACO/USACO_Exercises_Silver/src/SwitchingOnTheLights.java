import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SwitchingOnTheLights {
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

        int count = bfs(switches, n);

        pw.println(count);
        pw.close();
    }

    private static int bfs (HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> switches, int n) {
        boolean[][] visited = new boolean[n][n];
        boolean[][] on = new boolean[n][n];
        on[0][0] = true;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
//        int count = 0;
        int N = n - 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            if (visited[cur[0]][cur[1]]) {
                continue;
            }
//            count++;
            visited[cur[0]][cur[1]] = true;

            if (switches.containsKey(cur[0]) && switches.get(cur[0]).containsKey(cur[1])) {
                boolean added = false;
                for (int[] next : switches.get(cur[0]).get(cur[1])) {
                    if (!on[next[0]][next[1]]) {
                        on[next[0]][next[1]] = true;
                        added = true;
                    }
                }
                if (added) {
                    visited = new boolean[n][n];
                    queue.clear();
                    queue.offer(new int[]{0, 0});
                    continue;
                }
            }

            if (cur[1] < N && on[cur[0]][cur[1] + 1] && !visited[cur[0]][cur[1] + 1]) {
                queue.add(new int[]{cur[0], cur[1] + 1});
            }
            if (cur[0] < N && on[cur[0] + 1][cur[1]] && !visited[cur[0] + 1][cur[1]]) {
                queue.add(new int[]{cur[0] + 1, cur[1]});
            }
            if (cur[1] > 0 && on[cur[0]][cur[1] - 1] && !visited[cur[0]][cur[1] - 1]) {
                queue.add(new int[]{cur[0], cur[1] - 1});
            }
            if (cur[0] > 0 && on[cur[0] - 1][cur[1]] && !visited[cur[0] - 1][cur[1]]) {
                queue.add(new int[]{cur[0] - 1, cur[1]});
            }
        }
        int count = 0;
        visited = new boolean[n][n];
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            count++;
            visited[cur[0]][cur[1]] = true;

            if (cur[1] < N && on[cur[0]][cur[1] + 1] && !visited[cur[0]][cur[1] + 1]) {
                queue.add(new int[]{cur[0], cur[1] + 1});
            }
            if (cur[0] < N && on[cur[0] + 1][cur[1]] && !visited[cur[0] + 1][cur[1]]) {
                queue.add(new int[]{cur[0] + 1, cur[1]});
            }
            if (cur[1] > 0 && on[cur[0]][cur[1] - 1] && !visited[cur[0]][cur[1] - 1]) {
                queue.add(new int[]{cur[0], cur[1] - 1});
            }
            if (cur[0] > 0 && on[cur[0] - 1][cur[1]] && !visited[cur[0] - 1][cur[1]]) {
                queue.add(new int[]{cur[0] - 1, cur[1]});
            }
        }
        return count;
    }
}
