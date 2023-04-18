//public class milk63 {
//    private static final double FLOATING_POINT_EPSILON = 1E-11;
//
//    // the weight of the minimum cut
//    private double weight = Double.POSITIVE_INFINITY;
//
//    // cut[v] = true if v is on the first subset of vertices of the minimum cut;
//    // or false if v is on the second subset
//    private boolean[] cut;
//
//    // number of vertices
//    private int V;
//
//    /**
//     * This helper class represents the <em>cut-of-the-phase</em>. The
//     * cut-of-the-phase is a <em>minimum s-t-cut</em> in the current graph,
//     * where {@code s} and {@code t} are the two vertices added last in the
//     * phase.
//     */
//    private class CutPhase {
//        private double weight; // the weight of the minimum s-t cut
//        private int s;         // the vertex s
//        private int t;         // the vertex t
//
//        public CutPhase(double weight, int s, int t) {
//            this.weight = weight;
//            this.s = s;
//            this.t = t;
//        }
//    }
//
//    /**
//     * Computes a minimum cut in an edge-weighted graph.
//     *
//     * @param G the edge-weighted graph
//     * @throws IllegalArgumentException if the number of vertices of {@code G}
//     *             is less than {@code 2}.
//     * @throws IllegalArgumentException if any edge weight is negative
//     */
//    public milk63(EdgeWeightedGraph G) {
//        V = G.V();
//        validate(G);
//        minCut(G, 0);
//        assert check(G);
//    }
//
//    /**
//     * Validates the edge-weighted graph.
//     *
//     * @param G the edge-weighted graph
//     * @throws IllegalArgumentException if the number of vertices of {@code G}
//     *             is less than {@code 2} or if any edge weight is negative
//     */
//    private void validate(EdgeWeightedGraph G) {
//        if (G.V() < 2) throw new IllegalArgumentException("number of vertices of G is less than 2");
//        for (Edge e : G.edges()) {
//            if (e.weight() < 0) throw new IllegalArgumentException("edge " + e + " has negative weight");
//        }
//    }
//
//    /**
//     * Returns the weight of the minimum cut.
//     *
//     * @return the weight of the minimum cut
//     */
//    public double weight() {
//        return weight;
//    }
//
//    /**
//     * Returns {@code true} if the vertex {@code v} is one side of the
//     * mincut and {@code false} otherwise. An edge <em>v</em>-<em>w</em>
//     * crosses the mincut if and only if <em>v</em> and <em>w</em> have
//     * opposite parity.
//     *
//     * @param v the vertex to check
//     * @return {@code true} if the vertex {@code v} is on the first subset of
//     *         vertices of the minimum cut; or {@code false} if the vertex
//     *         {@code v} is on the second subset.
//     * @throws IllegalArgumentException unless vertex {@code v} is between
//     *             {@code 0 <= v < V}
//     */
//    public boolean cut(int v) {
//        validateVertex(v);
//        return cut[v];
//    }
//
//    /**
//     * Makes a cut for the current edge-weighted graph by partitioning its
//     * vertices into two nonempty subsets. The vertices connected to the
//     * vertex {@code t} belong to the first subset. Other vertices not connected
//     * to {@code t} belong to the second subset.
//     *
//     * @param t the vertex {@code t}
//     * @param uf the union-find data type
//     */
//    private void makeCut(int t, UF uf) {
//        for (int v = 0; v < cut.length; v++) {
//            cut[v] = (uf.find(v) == uf.find(t));
//        }
//    }
//
//    /**
//     * Computes a minimum cut of the edge-weighted graph. Precisely, it computes
//     * the lightest of the cuts-of-the-phase which yields the desired minimum
//     * cut.
//     *
//     * @param G the edge-weighted graph
//     * @param a the starting vertex
//     */
//    private void minCut(EdgeWeightedGraph G, int a) {
//        UF uf = new UF(G.V());
//        boolean[] marked = new boolean[G.V()];
//        cut = new boolean[G.V()];
//        CutPhase cp = new CutPhase(0.0, a, a);
//        for (int v = G.V(); v > 1; v--) {
//            cp = minCutPhase(G, marked, cp);
//            if (cp.weight < weight) {
//                weight = cp.weight;
//                makeCut(cp.t, uf);
//            }
//            G = contractEdge(G, cp.s, cp.t);
//            marked[cp.t] = true;
//            uf.union(cp.s, cp.t);
//        }
//    }
//
//    /**
//     * Returns the cut-of-the-phase. The cut-of-the-phase is a minimum s-t-cut
//     * in the current graph, where {@code s} and {@code t} are the two vertices
//     * added last in the phase. This algorithm is known in the literature as
//     * <em>maximum adjacency search</em> or <em>maximum cardinality search</em>.
//     *
//     * @param G the edge-weighted graph
//     * @param marked the array of contracted vertices, where {@code marked[v]}
//     *            is {@code true} if the vertex {@code v} was already
//     *            contracted; or {@code false} otherwise
//     * @param cp the previous cut-of-the-phase
//     * @return the cut-of-the-phase
//     */
//    private CutPhase minCutPhase(EdgeWeightedGraph G, boolean[] marked, CutPhase cp) {
//        IndexMaxPQ<Double> pq = new IndexMaxPQ<Double>(G.V());
//        for (int v = 0; v < G.V(); v++) {
//            if (v != cp.s && !marked[v]) pq.insert(v, 0.0);
//        }
//        pq.insert(cp.s, Double.POSITIVE_INFINITY);
//        while (!pq.isEmpty()) {
//            int v = pq.delMax();
//            cp.s = cp.t;
//            cp.t = v;
//            for (Edge e : G.adj(v)) {
//                int w = e.other(v);
//                if (pq.contains(w)) pq.increaseKey(w, pq.keyOf(w) + e.weight());
//            }
//        }
//        cp.weight = 0.0;
//        for (Edge e : G.adj(cp.t)) {
//            cp.weight += e.weight();
//        }
//        return cp;
//    }
//
//    /**
//     * Contracts the edges incidents on the vertices {@code s} and {@code t} of
//     * the given edge-weighted graph.
//     *
//     * @param G the edge-weighted graph
//     * @param s the vertex {@code s}
//     * @param t the vertex {@code t}
//     * @return a new edge-weighted graph for which the edges incidents on the
//     *         vertices {@code s} and {@code t} were contracted
//     */
//    private EdgeWeightedGraph contractEdge(EdgeWeightedGraph G, int s, int t) {
//        EdgeWeightedGraph H = new EdgeWeightedGraph(G.V());
//        for (int v = 0; v < G.V(); v++) {
//            for (Edge e : G.adj(v)) {
//                int w = e.other(v);
//                if (v == s && w == t || v == t && w == s) continue;
//                if (v < w) {
//                    if (w == t)      H.addEdge(new Edge(v, s, e.weight()));
//                    else if (v == t) H.addEdge(new Edge(w, s, e.weight()));
//                    else             H.addEdge(new Edge(v, w, e.weight()));
//                }
//            }
//        }
//        return H;
//    }
//
//    /**
//     * Checks optimality conditions.
//     *
//     * @param G the edge-weighted graph
//     * @return {@code true} if optimality conditions are fine
//     */
//    private boolean check(EdgeWeightedGraph G) {
//
//        // compute min st-cut for all pairs s and t
//        // shortcut: s must appear on one side of global mincut,
//        // so it suffices to try all pairs s-v for some fixed s
//        double value = Double.POSITIVE_INFINITY;
//        for (int s = 0, t = 1; t < G.V(); t++) {
//            FlowNetwork F = new FlowNetwork(G.V());
//            for (Edge e : G.edges()) {
//                int v = e.either(), w = e.other(v);
//                F.addEdge(new FlowEdge(v, w, e.weight()));
//                F.addEdge(new FlowEdge(w, v, e.weight()));
//            }
//            FordFulkerson maxflow = new FordFulkerson(F, s, t);
//            value = Math.min(value, maxflow.value());
//        }
//        if (Math.abs(weight - value) > FLOATING_POINT_EPSILON) {
//            System.err.println("Min cut weight = " + weight + " , max flow value = " + value);
//            return false;
//        }
//        return true;
//    }
//
//    // throw an IllegalArgumentException unless {@code 0 <= v < V}
//    private void validateVertex(int v) {
//        if (v < 0 || v >= V)
//            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
//    }
//
//
//    /**
//     * Unit tests the {@code GlobalMincut} data type.
//     *
//     * @param args the command-line arguments
//     */
//    public static void main(String[] args) {
//        In in = new In(args[0]);
//        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
//        GlobalMincut mc = new GlobalMincut(G);
//        StdOut.print("Min cut: ");
//        for (int v = 0; v < G.V(); v++) {
//            if (mc.cut(v)) StdOut.print(v + " ");
//        }
//        StdOut.println();
//        StdOut.println("Min cut weight = " + mc.weight());
//    }
//}
