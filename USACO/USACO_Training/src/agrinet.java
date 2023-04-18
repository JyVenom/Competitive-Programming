/*
ID: jerryya2
LANG: JAVA
TASK: agrinet
*/

import java.io.*;
import java.util.*;

public class agrinet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("agrinet.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));

        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Edge>> g = createEmptyGraph(n);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            while (sum < n) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int size = st.countTokens();
                for (int j = 0; j < size; j++) {
                    addDirectedEdge(g, i, sum, Integer.parseInt(st.nextToken()));
                    sum++;
                }
            }
            sum = 0;
        }
        br.close();

        agrinet prim = new agrinet(g);

        pw.println(prim.getMstCost());
        pw.close();
    }

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return cost - other.cost;
        }
    }

    // Inputs
    private final int n;
    private final ArrayList<ArrayList<Edge>> graph;

    // Internal
    private boolean solved;
    private boolean mstExists;
    private boolean[] visited;
    private PriorityQueue<Edge> pq;

    // Outputs
    private long minCostSum;
    private Edge[] mstEdges;

    public agrinet(ArrayList<ArrayList<Edge>> graph) {
        if (graph == null || graph.isEmpty()) throw new IllegalArgumentException();
        this.n = graph.size();
        this.graph = graph;
    }

    // Returns the edges used in finding the minimum spanning tree,
    // or returns null if no MST exists.
    public Edge[] getMst() {
        solve();
        return mstExists ? mstEdges : null;
    }

    public Long getMstCost() {
        solve();
        return mstExists ? minCostSum : null;
    }

    private void addEdges(int nodeIndex) {
        visited[nodeIndex] = true;

        // edges will never be null if the createEmptyGraph method was used to build the graph.
        ArrayList<Edge> edges = graph.get(nodeIndex);
        for (Edge e : edges)
            if (!visited[e.to]) {
                // System.out.printf("(%d, %d, %d)\n", e.from, e.to, e.cost);
                pq.offer(e);
            }
    }

    // Computes the minimum spanning tree and minimum spanning tree cost.
    private void solve() {
        if (solved) return;
        solved = true;

        int m = n - 1, edgeCount = 0;
        pq = new PriorityQueue<>();
        visited = new boolean[n];
        mstEdges = new Edge[m];

        // Add initial set of edges to the priority queue starting at node 0.
        addEdges(0);

        // Loop while the MST is not complete.
        while (!pq.isEmpty() && edgeCount != m) {
            Edge edge = pq.poll();
            int nodeIndex = edge.to;

            // Skip any edge pointing to an already visited node.
            if (visited[nodeIndex]) continue;

            mstEdges[edgeCount++] = edge;
            minCostSum += edge.cost;

            addEdges(nodeIndex);
        }

        // Check if MST spans entire graph.
        mstExists = (edgeCount == m);
    }

    /* Graph construction helpers. */

    static ArrayList<ArrayList<Edge>> createEmptyGraph(int n) {
        ArrayList<ArrayList<Edge>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        return g;
    }

    static void addDirectedEdge(ArrayList<ArrayList<Edge>> g, int from, int to, int cost) {
        g.get(from).add(new Edge(from, to, cost));
    }

    static void addUndirectedEdge(ArrayList<ArrayList<Edge>> g, int from, int to, int cost) {
        addDirectedEdge(g, from, to, cost);
        addDirectedEdge(g, to, from, cost);
    }

}
