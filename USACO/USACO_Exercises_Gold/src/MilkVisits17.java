import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MilkVisits17 {
    private static int time = 0;
    private static int[][] times;

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
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
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>(n);
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

            if (a != b) {
                if (!friends.containsKey(b)) {
                    friends.put(b, new HashMap<>());
                }
                if (!friends.get(b).containsKey(a)) {
                    friends.get(b).put(a, new ArrayList<>());
                }
                friends.get(b).get(a).add(new int[]{c, i});
            }
        }
        br.close();
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();

        times = new int[n][2];
        dfs2(edges, 0, -1);
        boolean[] ans = new boolean[m];
        ArrayList<Integer> path = new ArrayList<>();
        int[][] lasts = new int[n][2];
        dfs(friends, edges, path, lasts, type, ans, 0, -1);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();

        for (boolean an : ans) {
            pw.print(an ? 1 : 0);
        }
        pw.println();
        pw.close();
        System.out.println(System.currentTimeMillis() - start);
    }


    private static void dfs(HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> friends, ArrayList<ArrayList<Integer>> edges, ArrayList<Integer> path, int[][] last, int[] type, boolean[] ans, int cur, int prev) {
        int size = path.size();
        path.add(cur);
        int[] temp = last[type[cur]].clone();
        last[type[cur]][0] = cur;
        last[type[cur]][1] = path.size();


        if (friends.containsKey(cur)) {
            for (int b : friends.get(cur).keySet()) {
                for (int[] pair : friends.get(cur).get(b)) {
                    int c = pair[0];
                    int d = pair[1];

                    if (ans[d]) {
                        continue;
                    }

                    if (cur == b) {
                        if (type[b] == c) {
                            ans[d] = true;
                        }
                    } else {
                        if (!(last[c][1] == 0)) {
                            int y = last[c][0];
                            if (isAnc(y, b)) {
                                if (y != cur) {
                                    int Y = path.get(last[c][1]);
                                    if (!isAnc(Y, b)) {
                                        ans[d] = true;
                                    }
                                } else {
                                    ans[d] = true;
                                }
                            } else {
                                ans[d] = true;
                            }
                        }
                    }
                }
            }
        }

        for (int next : edges.get(cur)) {
            if (next != prev) {
                dfs(friends, edges, path, last, type, ans, next, cur);
            }
        }

        path.remove(size);
        last[type[cur]] = temp;
    }

    private static void dfs2(ArrayList<ArrayList<Integer>> edges, int cur, int prev) {
        times[cur][0] = time++;

        for (int next : edges.get(cur)) {
            if (next != prev) {
                dfs2(edges, next, cur);
            }
        }

        times[cur][1] = time - 1;
    }

    private static boolean isAnc(int a, int b) {
        return times[a][0] <= times[b][0] && times[a][1] >= times[b][1];
    }
}
