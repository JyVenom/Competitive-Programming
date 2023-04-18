import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MilkVisits13 {
    private static HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> friends;
    private static ArrayList<ArrayList<Integer>> edges;
    private static ArrayList<Integer> path;
    private static boolean[] visited;
    private static int[][] lasts;
    private static int[][] times;
    private static int[] type;
    private static int[] ans;
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        type = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            type[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        edges = new ArrayList<>();
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
        friends = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            if (!friends.containsKey(a)) {
                friends.put(a, new HashMap<>());
                friends.get(a).put(b, new ArrayList<>());
            } else if (!friends.get(a).containsKey(b)) {
                friends.get(a).put(b, new ArrayList<>());
            }
            friends.get(a).get(b).add(new int[]{c, i});

            if (!friends.containsKey(b)) {
                friends.put(b, new HashMap<>());
                friends.get(b).put(a, new ArrayList<>());
            } else if (!friends.get(b).containsKey(a)) {
                friends.get(b).put(a, new ArrayList<>());
            }
            friends.get(b).get(a).add(new int[]{c, i});
        }
        br.close();
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();

        visited = new boolean[n];
        times = new int[n][2];
        dfs2(0);
        visited = new boolean[n];
        ans = new int[m];
        path = new ArrayList<>();
        lasts = new int[n][2];
        dfs(0);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();

        for (int an : ans) {
            pw.print(an);
        }
        pw.println();
        pw.close();
        System.out.println(System.currentTimeMillis() - start);
    }


    private static void dfs(int cur) {
        visited[cur] = true;
        int temp = path.size();
        path.add(cur);
        int[] prev = lasts[type[cur]].clone();
        lasts[type[cur]][0] = cur;
        lasts[type[cur]][1] = path.size();


        if (friends.containsKey(cur)) {
            for (int b : friends.get(cur).keySet()) {
                for (int[] pair : friends.get(cur).get(b)) {
                    int c = pair[0];
                    int d = pair[1];

                    if (ans[d] == 1) {
                        continue;
                    }

                    if (cur == b) {
                        if (type[b] == c) {
                            ans[d] = 1;
                        }
                    } else {
                        if (!(lasts[c][1] == 0)) {
                            int y = lasts[c][0];
                            if (isAnc(times, y, b)) {
                                if (y != cur) {
                                    int Y = path.get(lasts[c][1]);
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
            }
        }

        for (int next : edges.get(cur)) {
            if (!visited[next]) {
                dfs(next);
            }
        }

        path.remove(temp);
        lasts[type[cur]] = prev;
    }

    private static void dfs2(int cur) {
        visited[cur] = true;
        times[cur][0] = time++;

        for (int next : edges.get(cur)) {
            if (!visited[next]) {
                dfs2(next);
            }
        }

        times[cur][1] = time - 1;
    }

    private static boolean isAnc(int[][] times, int a, int b) {
        return times[a][0] <= times[b][0] && times[a][1] >= times[b][1];
    }
}
