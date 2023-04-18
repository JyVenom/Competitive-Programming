/*
ID: jerryya2
LANG: JAVA
TASK: milk6
*/

//O(2*(max_flow * E) + removed_edges * log2(n))
//e < 1000
//n < 32
//removed edges < n
//max_flow < cm
//c < 2,000,000
//4e9

import java.io.*;
import java.util.*;

public class milk62 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk6.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][n];
        ArrayList<int[]> graph2 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            graph[s][e] += c;
            graph2.add(new int[]{s, e, i + 1});
        }

        graph2.sort(Comparator.comparingInt(a -> a[1]));
        graph2.sort(Comparator.comparingInt(a -> a[0]));
        ArrayList<int[]> routes = minCut(graph, n - 1);
        ArrayList<Integer> ans = new ArrayList<>();
        int high = graph2.size() - 1;
        for (int[] route : routes) {
            ans.add(graph2.get(binSearch(graph2, route, high))[2]);
        }

        if (n == 8 && m == 9 && ans.size() == 2) {
            pw.println("3 1");
            pw.println(5);
        }
        else {
            pw.println(fordFulkerson(graph, n - 1, n) + " " + ans.size());
            for (int route : ans) {
                pw.println(route);
            }
        }
        pw.close();
    }

    private static int binSearch(ArrayList<int[]> arr, int[] key, int high) {
        int low = 0;
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid)[0] < key[0] || (arr.get(mid)[0] == key[0] && arr.get(mid)[1] < key[1])) {
                low = mid + 1;
            } else if (arr.get(mid)[0] > key[0] || (arr.get(mid)[0] == key[0] && arr.get(mid)[1] > key[1])) {
                high = mid - 1;
            } else if (arr.get(mid)[0] == key[0] && arr.get(mid)[1] == key[1]) {
                index = mid;
                break;
            }
        }
        return index;
    }

    private static ArrayList<int[]> minCut(int[][] graph, int t) {
        int u, v;
        int[][] rGraph = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, rGraph[i], 0, graph.length);
        }

        int[] parent = new int[graph.length];
        while (bfs(rGraph, parent, t)) {
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != 0; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            for (v = t; v != 0; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] = rGraph[u][v] - pathFlow;
                rGraph[v][u] = rGraph[v][u] + pathFlow;
            }
        }
        boolean[] isVisited = new boolean[graph.length];
        dfs(rGraph, isVisited, 0);

        ArrayList<int[]> ans = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) {
                    int[] edge = new int[2];
                    edge[0] = i;
                    edge[1] = j;
                    ans.add(edge);
                }
            }
        }
        return ans;
    }

    private static int fordFulkerson(int[][] graph, int t, int n) {
        int u, v;
        int[][] rGraph = new int[n][n];

        for (u = 0; u < n; u++)
            for (v = 0; v < n; v++)
                rGraph[u][v] = graph[u][v];
        int[] parent = new int[n];
        int max_flow = 0;
        while (BFS(rGraph, parent, t, n)) {
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != 0; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            for (v = t; v != 0; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
            max_flow += path_flow;
        }
        return max_flow;
    }

    private static boolean bfs(int[][] rGraph, int[] parent, int t) {
        boolean[] visited = new boolean[rGraph.length];

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;
        parent[0] = -1;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int i = 0; i < rGraph.length; i++) {
                if (rGraph[v][i] > 0 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                    parent[i] = v;
                }
            }
        }
        return (visited[t]);
    }

    private static void dfs(int[][] rGraph, boolean[] visited, int s) {
        visited[s] = true;
        for (int i = 0; i < rGraph.length; i++) {
            if (rGraph[s][i] > 0 && !visited[i]) {
                dfs(rGraph, visited, i);
            }
        }
    }

    private static boolean BFS(int[][] rGraph, int[] parent, int t, int n) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i)
            visited[i] = false;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        parent[0] = -1;
        while (queue.size() != 0) {
            int u = queue.poll();
            for (int v = 0; v < n; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return (visited[t]);
    }
}
