import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MilkVisits5 {
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] type = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            type[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        int N = n - 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> friends = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            if (!friends.containsKey(a)) {
                friends.put(a, new HashMap<>());
            }
            if (!friends.get(a).containsKey(b)) {
                friends.get(a).put(b, new ArrayList<>());
            }
            friends.get(a).get(b).add(new int[]{c, i});
        }

        boolean[] visited = new boolean[n];
        int[][] times = new int[n][2];
        dfs2(edges, visited, times, 0);
        visited = new boolean[n];
        int[] ans = new int[m];
        ArrayList<Integer> path = new ArrayList<>();
        int[][] lasts = new int[n][2];
        dfs(friends, edges, path, visited, lasts, times, type, ans, 0, 0);

        for (int an : ans) {
            pw.print(an);
        }
        pw.println();
        pw.close();
    }

    private static void dfs(HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> friends, ArrayList<ArrayList<Integer>> edges, ArrayList<Integer> path, boolean[] visited, int[][] last, int[][] times, int[] type, int[] ans, int cur, int depth) {
        visited[cur] = true;
        int temp = path.size();
        path.add(cur);
        last[type[cur]] = new int[]{cur, depth};


        if (friends.containsKey(cur)) {
            for (int b : friends.get(cur).keySet()) {
                for (int[] pair : friends.get(cur).get(b)) {
                    int c = pair[0];
                    int d = pair[1];

                    if (ans[d] == 1) {
                        continue;
                    }

                    int y = last[c][0];
                    if (isAnc(times, y, b)) {
                        if (y != cur) {
                            int Y = path.get(last[c][1] + 1);
                            if (!isAnc(times, Y, b)) {
                                ans[d] = 1;
                            }
                        } else {
                            ans[d] = 1;
                        }
                    } else {
                        ans[d] = 1;
                    }
                }
            }
        }

        for (int next : edges.get(cur)) {
            if (!visited[next]) {
                dfs(friends, edges, path, visited, last, times, type, ans, next, depth + 1);
            }
        }

        path.remove(temp);
    }

    private static void dfs2(ArrayList<ArrayList<Integer>> edges, boolean[] visited, int[][] times, int cur) {
        visited[cur] = true;
        times[cur][0] = time++;

        for (int next : edges.get(cur)) {
            if (!visited[next]) {
                dfs2(edges, visited, times, next);
            }
        }

        times[cur][1] = time;
    }

    private static boolean isAnc ( int[][] times, int a, int b){
        return times[a][0] < times[b][0] && times[a][1] >= times[b][1];
    }
}
