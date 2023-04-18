import java.util.ArrayList;
import java.util.Scanner;

public class P337D2 {
    static int dfs1(int v, int p, int[] maxDistanceDown, boolean[] marked, ArrayList<Integer>[] adj) {
        maxDistanceDown[v] = marked[v] ? 0 : -1;
        for (int i = 0; i < adj[v].size(); i++) {
            int u = adj[v].get(i);
            if (u == p) continue;
            int d = dfs1(u, v, maxDistanceDown, marked, adj);
            if (d > -1)
                maxDistanceDown[v] = Math.max(maxDistanceDown[v], d + 1);
        }
        return maxDistanceDown[v];
    }

    static void dfs2(int v, int p, int[] maxDistanceUp, int[] maxDistanceDown, boolean[] marked, ArrayList<Integer>[] adj) {
        int mx1 = -1, mx2 = -1;
        for (int i = 0; i < adj[v].size(); i++) {
            int u = adj[v].get(i);
            if (u == p) continue;
            if (maxDistanceDown[u] > mx1) {
                mx2 = mx1;
                mx1 = maxDistanceDown[u];
            } else if (maxDistanceDown[u] > mx2) {
                mx2 = maxDistanceDown[u];
            }
        }

        for (int i = 0; i < adj[v].size(); i++) {
            int u = adj[v].get(i);
            if (u == p) continue;
            int siblingDistance = maxDistanceDown[u] == mx1 ? mx2 : mx1;
            if (siblingDistance != -1)
                siblingDistance += 2;
            maxDistanceUp[u] = siblingDistance;
            if (maxDistanceUp[v] != -1)
                maxDistanceUp[u] = Math.max(maxDistanceUp[u], maxDistanceUp[v] + 1);
            if (marked[u])
                maxDistanceUp[u] = Math.max(maxDistanceUp[u], 0);
            dfs2(u, v, maxDistanceUp, maxDistanceDown, marked, adj);
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int d = in.nextInt();

        boolean[] marked = new boolean[n];
        while (m-- > 0) {
            marked[in.nextInt() - 1] = true;
        }

        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<Integer>();
        for (int k = 0; k < n - 1; k++) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;
            adj[i].add(j);
            adj[j].add(i);
        }

        int[] maxDistanceDown = new int[n];
        dfs1(0, -1, maxDistanceDown, marked, adj);
        int[] maxDistanceUp = new int[n];
        maxDistanceUp[0] = marked[0] ? 0 : -1;
        dfs2(0, -1, maxDistanceUp, maxDistanceDown, marked, adj);

        int ans = 0;
        for (int i = 0; i < n; i++)
            ans += (maxDistanceUp[i] <= d && maxDistanceDown[i] <= d ? 1 : 0);
        System.out.println(ans);
    }
}
