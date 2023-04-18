/*
ID: jerryya2
LANG: JAVA
TASK: maze1
*/

import java.io.*;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class maze1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("maze1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));

        String[] line = br.readLine().split(" ");
        int w = Integer.parseInt(line[0]);
        int h = Integer.parseInt(line[1]);

        char[][] grid = new char[2 * h + 1][2 * w + 1];
        for (int i = 0; i <= h * 2; i++) {
            StringBuilder line2 = new StringBuilder(br.readLine());
            while (line2.length() < w * 2 + 1) {
                line2.append(' ');
            }
            for (int j = 0; j <= w * 2; j++) {
                grid[i][j] = line2.charAt(j);
            }
        }

        int count = 0;
        int[] ends = new int[2];
        int last = h * 2;
        for (int i = 1; i < w * 2; i += 2) {
            if (grid[0][i] == ' ') {
                ends[count] = i / 2;
                count++;
            }
            if (count == 2) {
                break;
            }
            if (grid[last][i] == ' ') {
                ends[count] = w * (h - 1) + i / 2;
                count++;
            }
            if (count == 2) {
                break;
            }
        }

        if (count < 2) {
            last = w * 2;
            for (int i = 1; i < h * 2; i += 2) {
                if (grid[i][0] == ' ') {
                    ends[count] = (i / 2) * w;
                    count++;
                }
                if (count == 2) {
                    break;
                }
                if (grid[i][last] == ' ') {
                    ends[count] = ((i + 1) / 2) * w - 1;
                    count++;
                }
                if (count == 2) {
                    break;
                }
            }
        }

        DijkstrasShortestPathAdjacencyListWithDHeapWithIntegers dij = new DijkstrasShortestPathAdjacencyListWithDHeapWithIntegers(w * h);
        for (int i = 1; i < h * 2; i += 2) {
            for (int j = 1; j < w * 2; j += 2) {
                if (j < w * 2 - 1) {
                    if (grid[i][j + 1] == ' ') {
                        dij.addEdge((i / 2) * w + j / 2, (i / 2) * w + j / 2 + 1, 1);
                    }
                }
                if (i < h * 2 - 1) {
                    if (grid[i + 1][j] == ' ') {
                        dij.addEdge((i / 2) * w + j / 2, (i / 2 + 1) * w + j / 2, 1);
                    }
                }
            }
        }

        int max = 0;
        int[] dist1 = dij.dijkstra(ends[0]);
        int[] dist2 = dij.dijkstra(ends[1]);
        int n = w * h;
        for (int i = 0; i < n; i++) {
            if (dist2[i] < dist1[i]) {
                dist1[i] = dist2[i];
            }
            if (dist1[i] > max) {
                max = dist1[i];
            }
        }

        pw.println(max + 1);
        pw.close();
    }

    static class DijkstrasShortestPathAdjacencyListWithDHeapWithIntegers {

        // An edge class to represent a directed edge
        // between two nodes with a certain cost.
        public static class Edge {
            int to;
            int cost;

            public Edge(int to, int cost) {
                this.to = to;
                this.cost = cost;
            }
        }

        private final int n;

        private int edgeCount;
        private List<List<Edge>> graph;

        /**
         * Initialize the solver by providing the graph size and a starting node. Use the {@link #addEdge}
         * method to actually add edges to the graph.
         *
         * @param n - The number of nodes in the graph.
         */
        public DijkstrasShortestPathAdjacencyListWithDHeapWithIntegers(int n) {
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
         * @param to   - The index of the node the directed edge end at.
         * @param cost - The cost of the edge.
         */
        public void addEdge(int from, int to, int cost) {
            edgeCount++;
            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));
        }

        // Run Dijkstra's algorithm on a directed graph to find the shortest path
        // from a starting node to an ending node. If there is no path between the
        // starting node and the destination node the returned value is set to be
        // Integer.MAX_VALUE.
        public int[] dijkstra(int start) {

            // Keep an Indexed Priority Queue (ipq) of the next most promising node
            // to visit.
            int degree = edgeCount / n;
            MinIndexedDHeap<Integer> ipq = new MinIndexedDHeap<>(degree, n);
            ipq.insert(start, 0);

            // Maintain an array of the minimum distance to each node.
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;

            boolean[] visited = new boolean[n];

            while (!ipq.isEmpty()) {
                int nodeId = ipq.peekMinKeyIndex();

                visited[nodeId] = true;
                int minValue = ipq.pollMinValue();

                // We already found a better path before we got to
                // processing this node so we can ignore it.
                if (minValue > dist[nodeId]) continue;

                for (Edge edge : graph.get(nodeId)) {

                    // We cannot get a shorter path by revisiting
                    // a node we have already visited before.
                    if (visited[edge.to]) continue;

                    // Relax edge by updating minimum cost if applicable.
                    int newDist = dist[nodeId] + edge.cost;
                    if (newDist < dist[edge.to]) {
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
//            if (nodeId == end) return dist[end];
            }
            // End node is unreachable.
            return dist;
        }

        /**
         * Reconstructs the shortest path (of nodes) from 'start' to 'end' inclusive.
         *
         * @return An array of node indexes of the shortest path from 'start' to 'end'. If 'start' and
         * 'end' are not connected then an empty array is returned.
         */
//    public ArrayList<Integer> reconstructPath(int start, int end) {
//        if (end < 0 || end >= n) throw new IllegalArgumentException("Invalid node index");
//        if (start < 0 || start >= n) throw new IllegalArgumentException("Invalid node index");
//        ArrayList<Integer> path = new ArrayList<>();
//        int dist = dijkstra(start, end);
//        if (dist == Integer.MAX_VALUE) return path;
//        for (Integer at = end; at != null; at = prev[at]) path.add(at);
//        Collections.reverse(path);
//        return path;
//    }

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

            public void delete(int ki) {
                keyExistsOrThrow(ki);
                final int i = pm[ki];
                swap(i, --sz);
                sink(i);
                swim(i);
                values[ki] = null;
                pm[ki] = -1;
                im[sz] = -1;
            }

            // Strictly decreases the value associated with 'ki' to 'value'
            public void decrease(int ki, T value) {
                keyExistsAndValueNotNullOrThrow(ki, value);
                if (less(value, values[ki])) {
                    values[ki] = value;
                    swim(pm[ki]);
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
}