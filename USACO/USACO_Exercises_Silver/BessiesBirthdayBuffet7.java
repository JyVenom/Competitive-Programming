import java.io.*;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class BessiesBirthdayBuffet5 {
    private static int[] dist;
    private static int[] q;
    List<List<Node>> adj;
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V; // Number of vertices

    public BessiesBirthdayBuffet5(int V) {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(V, new Node());
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("buffet.in"));
        PrintWriter out = new PrintWriter(new File("buffet.out"));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int cost = Integer.parseInt(line[1]);
        int[] max = new int[n];
        q = new int[n];
        ArrayList<ArrayList<Integer>> quality = new ArrayList<>();
        List<List<Node>> adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            quality.add(new ArrayList<>());
            connections.add(new ArrayList<>());
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            quality.get(i).add(Integer.parseInt(line[0]));
            quality.get(i).add(i);
            q[i] = Integer.parseInt(line[0]);
            int num = Integer.parseInt(line[1]);
            for (int j = 0; j < num; j++) {
                connections.get(i).add(Integer.parseInt(line[2 + j]) - 1);
//                adj.get(i).add(new Node(Integer.parseInt(line[2 + j]) - 1, cost));
            }
        }
        quality.sort(Comparator.comparing(a -> a.get(0)));
        for (int i = 0; i < n; i++) {
            quality.get(i).add(i);
        }
        ArrayList<ArrayList<Integer>> connections2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            connections2.add(new ArrayList<>(connections.get(quality.get(i).get(1))));
        }
        quality.sort(Comparator.comparing(a -> a.get(1)));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < connections2.get(i).size(); j++) {
                int index = binarySearch2d(quality, 0, quality.size() - 1, connections2.get(i).get(j));
                connections2.get(i).set(j, quality.get(index).get(2));
            }
        }

        for (int i = 0; i < n; i++) {
            int size = connections2.get(i).size();
            for (int j = 0; j < size; j++) {
                adj.get(i).add(new Node(connections2.get(i).get(j), cost));
            }
        }

        Arrays.sort(q);
        quality.sort(Comparator.comparing(a -> a.get(0)));
        int answer = 0;
//        max[0] = q[0];
//        answer = Math.max(answer, max[0]);
        DijkstrasShortestPathAdjacencyListWithDHeap dpq;
        for (int i = 0; i < n; i++) {
            //Find distances from patch i to all prev patches
//            int patch1 = quality.get(i).get(1);
//            dpq.dijkstra(adj, patch1);
//            max[patch1] = q[patch1];
            dpq = new DijkstrasShortestPathAdjacencyListWithDHeap(n);
            dpq.dijkstra(adj, i);
            max[i] = q[i];
            for (int j = 0; j < i; j++) {
//                int patch2 = quality.get(j).get(1);
//                max[patch1] = Math.max(max[patch1], max[patch2] + q[patch1] - dist[patch2]);
                max[i] = Math.max(max[i], max[j] + q[i] - dist[j]);
            }
//            answer = Math.max(answer, max[patch1]);
            answer = Math.max(answer, max[i]);
        }

        out.println(answer);
        out.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int binarySearch2d(ArrayList<ArrayList<Integer>> arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid).get(1) == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub array
            if (arr.get(mid).get(1) > x)
                return binarySearch2d(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right sub array
            return binarySearch2d(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

//    // Function for Dijkstra's Algorithm
//    public void dijkstra(List<List<Node>> adj, int src) {
//        int count = 0;
//        HashSet<Integer> temp = new HashSet<>();
//        this.adj = adj;
//
//        for (int i = 0; i < V; i++)
//            dist[i] = Integer.MAX_VALUE;
//
//        // Add source node to the priority queue
//        pq.add(new Node(src, 0));
//
//        // Distance to the source is 0
//        dist[src] = 0;
//        while (settled.size() != V && pq.size() > 0) {
//            count++;
//
//            // remove the minimum distance node
//            // from the priority queue
//            int u = pq.remove().node;
//
//            // adding the node whose distance is
//            // finalized
//            settled.add(u);
//            if (u != src) {
//                if (q[u] <= q[src]) {
//                    temp.add(u);
//                    if (temp.size() == src) {
//                        break;
//                    }
//                }
//            }
//
//            e_Neighbours(u);
//        }
//        System.out.println(count);
//    }
//
//    // Function to process all the neighbours
//    // of the passed node
//    private void e_Neighbours(int u) {
//        int edgeDistance;
//        int newDistance;
//
//        // All the neighbors of v
//        for (int i = 0; i < adj.get(u).size(); i++) {
//            Node v = adj.get(u).get(i);
//
//            // If current node hasn't already been processed
//            if (!settled.contains(v.node)) {
//                edgeDistance = v.cost;
//                newDistance = dist[u] + edgeDistance;
//
//                // If new distance is cheaper in cost
//                if (newDistance < dist[v.node])
//                    dist[v.node] = newDistance;
//
//                // Add the current node to the queue
//                pq.add(new Node(v.node, dist[v.node]));
//            }
//        }
//    }
}

//// Class to represent a node in the graph
//class Node implements Comparator<Node> {
//    public int node;
//    public int cost;
//
//    public Node() {
//    }
//
//    public Node(int node, int cost) {
//        this.node = node;
//        this.cost = cost;
//    }
//
//    @Override
//    public int compare(Node node1, Node node2) {
//        return Integer.compare(node1.cost, node2.cost);
//    }
//}

/**
 * This file contains an implementation of Dijkstra's shortest path algorithm from a start node to a
 * specific ending node. Dijkstra's can also be modified to find the shortest path between a
 * starting node and all other nodes in the graph with minimal effort.
 */



class DijkstrasShortestPathAdjacencyListWithDHeap {

    // An edge class to represent a directed edge
    // between two nodes with a certain cost.
    public static class Edge {
        int to;
        double cost;

        public Edge(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private final int n;

    private int edgeCount;
    private double[] dist;
    private Integer[] prev;
    private List<List<Edge>> graph;

    /**
     * Initialize the solver by providing the graph size and a starting node. Use the {@link #addEdge}
     * method to actually add edges to the graph.
     *
     * @param n - The number of nodes in the graph.
     */
    public DijkstrasShortestPathAdjacencyListWithDHeap(int n) {
        this.n = n;
        createEmptyGraph();
    }

    // Construct an empty graph with n nodes including the source and sink nodes.
    private void createEmptyGraph() {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    }

    /**
     * Adds a directed edge to the graph.
     *
     * @param from - The index of the node the directed edge starts at.
     * @param to - The index of the node the directed edge end at.
     * @param cost - The cost of the edge.
     */
    public void addEdge(int from, int to, int cost) {
        edgeCount++;
        graph.get(from).add(new Edge(to, cost));
    }

    /**
     * Use {@link #addEdge} method to add edges to the graph and use this method to retrieve the
     * constructed graph.
     */
    public List<List<Edge>> getGraph() {
        return graph;
    }

    // Run Dijkstra's algorithm on a directed graph to find the shortest path
    // from a starting node to an ending node. If there is no path between the
    // starting node and the destination node the returned value is set to be
    // Double.POSITIVE_INFINITY.
    public double dijkstra(int start, int end) {

        // Keep an Indexed Priority Queue (ipq) of the next most promising node
        // to visit.
        int degree = edgeCount / n;
        MinIndexedDHeap<Double> ipq = new MinIndexedDHeap<>(degree, n);
        ipq.insert(start, 0.0);

        // Maintain an array of the minimum distance to each node.
        dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0.0;

        boolean[] visited = new boolean[n];
        prev = new Integer[n];

        while (!ipq.isEmpty()) {
            int nodeId = ipq.peekMinKeyIndex();

            visited[nodeId] = true;
            double minValue = ipq.pollMinValue();

            // We already found a better path before we got to
            // processing this node so we can ignore it.
            if (minValue > dist[nodeId]) continue;

            for (Edge edge : graph.get(nodeId)) {

                // We cannot get a shorter path by revisiting
                // a node we have already visited before.
                if (visited[edge.to]) continue;

                // Relax edge by updating minimum cost if applicable.
                double newDist = dist[nodeId] + edge.cost;
                if (newDist < dist[edge.to]) {
                    prev[edge.to] = nodeId;
                    dist[edge.to] = newDist;
                    // Insert the cost of going to a node for the first time in the PQ,
                    // or try and update it to a better value by calling decrease.
                    if (!ipq.contains(edge.to)) ipq.insert(edge.to, newDist);
                    else ipq.decrease(edge.to, newDist);
                }
            }
            // Once we've processed the end node we can return early (without
            // necessarily visiting the whole graph) because we know we cannot get a
            // shorter path by routing through any other nodes since Dijkstra's is
            // greedy and there are no negative edge weights.
            if (nodeId == end) return dist[end];
        }
        // End node is unreachable.
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Reconstructs the shortest path (of nodes) from 'start' to 'end' inclusive.
     *
     * @return An array of node indexes of the shortest path from 'start' to 'end'. If 'start' and
     *     'end' are not connected then an empty array is returned.
     */
    public List<Integer> reconstructPath(int start, int end) {
        if (end < 0 || end >= n) throw new IllegalArgumentException("Invalid node index");
        if (start < 0 || start >= n) throw new IllegalArgumentException("Invalid node index");
        List<Integer> path = new ArrayList<>();
        double dist = dijkstra(start, end);
        if (dist == Double.POSITIVE_INFINITY) return path;
        for (Integer at = end; at != null; at = prev[at]) path.add(at);
        Collections.reverse(path);
        return path;
    }

    private static class MinIndexedDHeap<T extends Comparable<T>> {

        // Current number of elements in the heap.
        private int sz;

        // Maximum number of elements in the heap.
        private final int N;

        // The degree of every node in the heap.
        private final int D;

        // Lookup arrays to track the child/parent indexes of each node.
        private final int[] child, parent;

        // The Position Map (pm) maps Key Indexes (ki) to where the position of that
        // key is represented in the priority queue in the domain [0, sz).
        public final int[] pm;

        // The Inverse Map (im) stores the indexes of the keys in the range
        // [0, sz) which make up the priority queue. It should be noted that
        // 'im' and 'pm' are inverses of each other, so: pm[im[i]] = im[pm[i]] = i
        public final int[] im;

        // The values associated with the keys. It is very important  to note
        // that this array is indexed by the key indexes (aka 'ki').
        public final Object[] values;

        // Initializes a D-ary heap with a maximum capacity of maxSize.
        public MinIndexedDHeap(int degree, int maxSize) {
            if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");

            D = max(2, degree);
            N = max(D + 1, maxSize);

            im = new int[N];
            pm = new int[N];
            child = new int[N];
            parent = new int[N];
            values = new Object[N];

            for (int i = 0; i < N; i++) {
                parent[i] = (i - 1) / D;
                child[i] = i * D + 1;
                pm[i] = im[i] = -1;
            }
        }

        public int size() {
            return sz;
        }

        public boolean isEmpty() {
            return sz == 0;
        }

        public boolean contains(int ki) {
            keyInBoundsOrThrow(ki);
            return pm[ki] != -1;
        }

        public int peekMinKeyIndex() {
            isNotEmptyOrThrow();
            return im[0];
        }

        public int pollMinKeyIndex() {
            int minki = peekMinKeyIndex();
            delete(minki);
            return minki;
        }

        @SuppressWarnings("unchecked")
        public T peekMinValue() {
            isNotEmptyOrThrow();
            return (T) values[im[0]];
        }

        public T pollMinValue() {
            T minValue = peekMinValue();
            delete(peekMinKeyIndex());
            return minValue;
        }

        public void insert(int ki, T value) {
            if (contains(ki)) throw new IllegalArgumentException("index already exists; received: " + ki);
            valueNotNullOrThrow(value);
            pm[ki] = sz;
            im[sz] = ki;
            values[ki] = value;
            swim(sz++);
        }

        @SuppressWarnings("unchecked")
        public T valueOf(int ki) {
            keyExistsOrThrow(ki);
            return (T) values[ki];
        }

        @SuppressWarnings("unchecked")
        public T delete(int ki) {
            keyExistsOrThrow(ki);
            final int i = pm[ki];
            swap(i, --sz);
            sink(i);
            swim(i);
            T value = (T) values[ki];
            values[ki] = null;
            pm[ki] = -1;
            im[sz] = -1;
            return value;
        }

        @SuppressWarnings("unchecked")
        public T update(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            final int i = pm[ki];
            T oldValue = (T) values[ki];
            values[ki] = value;
            sink(i);
            swim(i);
            return oldValue;
        }

        // Strictly decreases the value associated with 'ki' to 'value'
        public void decrease(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            if (less(value, values[ki])) {
                values[ki] = value;
                swim(pm[ki]);
            }
        }

        // Strictly increases the value associated with 'ki' to 'value'
        public void increase(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            if (less(values[ki], value)) {
                values[ki] = value;
                sink(pm[ki]);
            }
        }

        /* Helper functions */

        private void sink(int i) {
            for (int j = minChild(i); j != -1; ) {
                swap(i, j);
                i = j;
                j = minChild(i);
            }
        }

        private void swim(int i) {
            while (less(i, parent[i])) {
                swap(i, parent[i]);
                i = parent[i];
            }
        }

        // From the parent node at index i find the minimum child below it
        private int minChild(int i) {
            int index = -1, from = child[i], to = min(sz, from + D);
            for (int j = from; j < to; j++) if (less(j, i)) index = i = j;
            return index;
        }

        private void swap(int i, int j) {
            pm[im[j]] = i;
            pm[im[i]] = j;
            int tmp = im[i];
            im[i] = im[j];
            im[j] = tmp;
        }

        // Tests if the value of node i < node j
        @SuppressWarnings("unchecked")
        private boolean less(int i, int j) {
            return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
        }

        @SuppressWarnings("unchecked")
        private boolean less(Object obj1, Object obj2) {
            return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
        }

        @Override
        public String toString() {
            List<Integer> lst = new ArrayList<>(sz);
            for (int i = 0; i < sz; i++) lst.add(im[i]);
            return lst.toString();
        }

        /* Helper functions to make the code more readable. */

        private void isNotEmptyOrThrow() {
            if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        }

        private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
            keyExistsOrThrow(ki);
            valueNotNullOrThrow(value);
        }

        private void keyExistsOrThrow(int ki) {
            if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
        }

        private void valueNotNullOrThrow(Object value) {
            if (value == null) throw new IllegalArgumentException("value cannot be null");
        }

        private void keyInBoundsOrThrow(int ki) {
            if (ki < 0 || ki >= N)
                throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
        }
    }
}