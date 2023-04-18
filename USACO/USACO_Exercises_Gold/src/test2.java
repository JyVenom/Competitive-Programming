import java.util.ArrayList;

public class test2 {
    public static void main(String[] args) {
        int n = 10000000;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            if (((2 * i) - 1) < n) {
                edges.get(i - 1).add((2 * i) - 1);
            }
            else {
                break;
            }
            if ((2 * i) < n) {
                edges.get(i - 1).add(2 * i);
            }
            else {
                break;
            }
        }
        int t = 100;
        long sum = 0;
        long start;
        long end;
        for (int i = 0; i < t; i++) {
            start = System.currentTimeMillis();
            dfs(edges, 0, -1);
            end = System.currentTimeMillis();
            sum += (end - start);
        }
        System.out.println(sum / t);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, int cur, int prev) {
        for (int next : edges.get(cur)) {
            if (next != prev) {
                dfs(edges, next, cur);
            }
        }
    }
}
