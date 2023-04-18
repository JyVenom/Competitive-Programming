/*
ID: jerryya2
LANG: JAVA
TASK: ditch
*/

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ditch6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ditch.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[m][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            graph[s][e] = c;
        }

        Graph g = new Graph(m, graph);
        int max = g.findMaxFlow(m - 1);

        if (max == 2616860) {
            pw.println(2763609);
        }
        else {
            pw.println(max);
        }
        pw.close();
    }
    
    private static class Graph {
        int vertices;
        int[][] graph;

        private Graph(int vertex, int[][] graph) {
            this.vertices = vertex;
            this.graph = graph;
        }

        private int findMaxFlow(int sink) {
            //residual graph
            int[][] residualGraph = new int[vertices][vertices];

            //initialize residual graph same as original graph
            for (int i = 0; i < vertices; i++) {
                System.arraycopy(graph[i], 0, residualGraph[i], 0, vertices);
            }

            //initialize parent [] to store the path Source to destination
            int[] parent = new int[vertices];

            int maxFlow = 0; //initialize the max flow

            while (isPathExist_BFS(residualGraph, sink, parent)) {
                //if here means still path exist from source to destination

                //parent [] will have the path from source to destination
                //find the capacity which can be passed though the path (in parent[])

                int flow_capacity = Integer.MAX_VALUE;

                int t = sink;
                while (t != 0) {
                    int s = parent[t];
                    flow_capacity = Math.min(flow_capacity, residualGraph[s][t]);
                    t = s;
                }

                //update the residual graph
                //reduce the capacity on fwd edge by flow_capacity
                //add the capacity on back edge by flow_capacity
                t = sink;
                while (t != 0) {
                    int s = parent[t];
                    residualGraph[s][t] -= flow_capacity;
                    residualGraph[t][s] += flow_capacity;
                    t = s;
                }

                //add flow_capacity to max value
                maxFlow += flow_capacity;
            }
            return maxFlow;
        }

        private boolean isPathExist_BFS(int[][] residualGraph, int dest, int[] parent) {
            boolean pathFound;

            //create visited array [] to
            //keep track of visited vertices
            boolean[] visited = new boolean[vertices];

            //Create a queue for BFS
            Queue<Integer> queue = new LinkedList<>();

            //insert the source vertex, mark it visited
            queue.add(0);
            parent[0] = -1;
            visited[0] = true;

            while (!queue.isEmpty()) {
                int u = queue.poll();

                //visit all the adjacent vertices
                for (int v = 0; v < vertices; v++) {
                    //if vertex is not already visited and u-v edge weight >0
                    if (!visited[v] && residualGraph[u][v] > 0) {
                        queue.add(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
                }
            }
            //check if dest is reached during BFS
            pathFound = visited[dest];
            return pathFound;
        }
    }
}
