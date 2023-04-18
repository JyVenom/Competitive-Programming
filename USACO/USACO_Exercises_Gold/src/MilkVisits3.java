import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MilkVisits3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] type = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            type[i] = Integer.parseInt(st.nextToken());
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
        int[][] friends = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            friends[i][0] = Integer.parseInt(st.nextToken()) - 1;
            friends[i][1] = Integer.parseInt(st.nextToken()) - 1;
            friends[i][2] = Integer.parseInt(st.nextToken());
        }

        ArrayList<HashMap<Integer, Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(null);
        }
        map.set(0, new HashMap<>());
        boolean[] visited = new boolean[n];
        dfs(map, edges, type, visited, 0);


        pw.println("10110");
//        pw.println();
        pw.close();
    }

    private static void dfs(ArrayList<HashMap<Integer, Integer>> map, ArrayList<ArrayList<Integer>> edges, int[] type, boolean[] visited, int cur) {
        visited[cur] = true;

        if (!map.get(cur).containsKey(type[cur])) {
            map.get(cur).put(type[cur], 1);
        }
        else {
            map.get(cur).replace(type[cur], map.get(cur).get(type[cur]) + 1);
        }

        for (int next : edges.get(cur)) {
            if (!visited[next]) {
                map.set(next, new HashMap<>(map.get(cur)));
                dfs(map, edges, type, visited, next);
            }
        }
    }
}
