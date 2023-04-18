/*
ID: jerryya2
LANG: JAVA
TASK: ditch
*/

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ditch2 {
    private static int V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ditch.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        V = m;
        int[][] graph = new int[m][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            graph[s][e] = c;
        }

        pw.println(fordFulkerson(graph, m - 1));
        pw.close();
    }

    private static long fordFulkerson(int[][] graph, int t) {
        int u, v;
        int[][] rGraph = new int[V][V];
        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }
        int[] parent = new int[V];
        long maxFlow = 0;
        while (bfs(rGraph, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != 0; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }
            for (v = t; v != 0; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    private static boolean bfs(int[][] rGraph, int t, int[] parent) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; ++i) {
            visited[i] = false;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        parent[0] = -1;
        while (queue.size() != 0) {
            int u = queue.poll();
            for (int v = 0; v < V; v++) {
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
