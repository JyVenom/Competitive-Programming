import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class FlightRoutes2 {
    private static long[] dist;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int k = ir.nextInt();
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            graph.get(ir.nextInt() - 1).add(new Edge(ir.nextInt() - 1, ir.nextInt()));
        }

        int N = n - 1;
        DijkstraShortestPath d = new DijkstraShortestPath(n);
        for (int i = 0; i < n; i++) {
            for (Edge e : graph.get(i)) {
                d.addEdge(e.to, i, e.cost);
            }
        }
        dist = d.dijkstra(N);
        ArrayList<Long> ans = bfs(graph, k);
        StringBuilder sb = new StringBuilder(2 * k);
        for (long an : ans) {
            sb.append(an).append(" ");
        }

        System.out.print(sb);
    }

    private static ArrayList<Long> bfs(ArrayList<ArrayList<Edge>> graph, int k) {
        PriorityQueue<State> queue = new PriorityQueue<>((o1, o2) -> {
            long dif = (o1.cost + dist[o1.loc]) - (o2.cost + dist[o2.loc]);
            if (dif < 0) {
                return -1;
            } else if (dif == 0) {
                return 0;
            } else {
                return 1;
            }
        });
        queue.add(new State(0, 0));
        ArrayList<Long> ans = new ArrayList<>();
        int K = k - 1;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (ans.size() == k) {
                long newCost = cur.cost + dist[cur.loc];
                if (newCost > ans.get(K)) {
                    return ans;
                }
                ans.add(binSearch(ans, newCost), newCost);
                ans.remove(k);
            } else {
                ans.add(cur.cost + dist[cur.loc]);
            }

            for (Edge next : graph.get(cur.loc)) {
                queue.add(new State(next.to, cur.cost + next.cost));
            }
        }
        return ans; //should never happen
    }

    private static int binSearch(ArrayList<Long> arr, long key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            } else if (arr.get(mid) == key) {
                return mid;
            }
        }
        return low;
    }

    private static class State {
        int loc;
        long cost;

        public State(int loc, long cost) {
            this.loc = loc;
            this.cost = cost;
        }
    }

    private static class Edge {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static class DijkstraShortestPath {
        private final int n;
        private int edgeCount;
        private ArrayList<ArrayList<Edge>> graph;

        /**
         * Initialize the solver by providing the graph size and a starting node. Use the {@link #addEdge}
         * method to actually add edges to the graph.
         *
         * @param n - The number of nodes in the graph.
         */
        public DijkstraShortestPath(int n) {
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
        public void addEdge(int from, int to, long cost) {
            edgeCount++;
            graph.get(from).add(new Edge(to, cost));
        }

        // Run Dijkstra's algorithm on a directed graph to find the shortest path
        // from a starting node to an ending node. If there is no path between the
        // starting node and the destination node the returned value is set to be
        // Integer.MAX_VALUE.
        public long[] dijkstra(int start) {

            // Keep an Indexed Priority Queue (ipq) of the next most promising node
            // to visit.
            int degree = edgeCount / n;
            MinIndexedDHeap<Long> ipq = new MinIndexedDHeap<>(degree, n);
            ipq.insert(start, 0L);

            // Maintain an array of the minimum distance to each node.
            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[start] = 0;

            boolean[] visited = new boolean[n];

            while (!ipq.isEmpty()) {
                int nodeId = ipq.peekMinKeyIndex();

                visited[nodeId] = true;
                long minValue = ipq.pollMinValue();

                // We already found a better path before we got to
                // processing this node so we can ignore it.
                if (minValue > dist[nodeId]) continue;

                for (Edge edge : graph.get(nodeId)) {

                    // We cannot get a shorter path by revisiting
                    // a node we have already visited before.
                    if (visited[edge.to]) continue;

                    // Relax edge by updating minimum cost if applicable.
                    long newDist = dist[nodeId] + edge.cost;
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
//                if (nodeId == end) return dist[end];
            }
            // End node is unreachable.
            return dist;
        }

//        /**
//         * Reconstructs the shortest path (of nodes) from 'start' to 'end' inclusive.
//         *
//         * @return An array of node indexes of the shortest path from 'start' to 'end'. If 'start' and
//         * 'end' are not connected then an empty array is returned.
//         */
//        public ArrayList<Integer> reconstructPath(int start, int end) {
//            if (end < 0 || end >= n) throw new IllegalArgumentException("Invalid node index");
//            if (start < 0 || start >= n) throw new IllegalArgumentException("Invalid node index");
//            ArrayList<Integer> path = new ArrayList<>();
//            int dist = dijkstra(start, end);
//            if (dist == Integer.MAX_VALUE) return path;
//            for (Integer at = end; at != null; at = prev[at]) path.add(at);
//            Collections.reverse(path);
//            return path;
//        }

        // An edge class to represent a directed edge
        // between two nodes with a certain cost.
        public static class Edge {
            int to;
            long cost;

            public Edge(int to, long cost) {
                this.to = to;
                this.cost = cost;
            }
        }

        private static class MinIndexedDHeap<T extends Comparable<T>> {

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
            // Maximum number of elements in the heap.
            private final int N;
            // The degree of every node in the heap.
            private final int D;
            // Lookup arrays to track the child/parent indexes of each node.
            private final int[] child, parent;
            // Current number of elements in the heap.
            private int sz;

            // Initializes a D-ary heap with a maximum capacity of maxSize.
            public MinIndexedDHeap(int degree, int maxSize) {
                if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");

                D = Math.max(2, degree);
                N = Math.max(D + 1, maxSize);

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

//        public int size() {
//            return sz;
//        }

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

//        public int pollMinKeyIndex() {
//            int minKey = peekMinKeyIndex();
//            delete(minKey);
//            return minKey;
//        }

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

//        @SuppressWarnings("unchecked")
//        public T valueOf(int ki) {
//            keyExistsOrThrow(ki);
//            return (T) values[ki];
//        }

            //        @SuppressWarnings("unchecked")
//        public T delete(int ki) {
//            keyExistsOrThrow(ki);
//            final int i = pm[ki];
//            swap(i, --sz);
//            sink(i);
//            swim(i);
//            T value = (T) values[ki];
//            values[ki] = null;
//            pm[ki] = -1;
//            im[sz] = -1;
//            return value;
//        }
//        @SuppressWarnings("unchecked")
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

//        @SuppressWarnings("unchecked")
//        public T update(int ki, T value) {
//            keyExistsAndValueNotNullOrThrow(ki, value);
//            final int i = pm[ki];
//            T oldValue = (T) values[ki];
//            values[ki] = value;
//            sink(i);
//            swim(i);
//            return oldValue;
//        }

            // Strictly decreases the value associated with 'ki' to 'value'
            public void decrease(int ki, T value) {
                keyExistsAndValueNotNullOrThrow(ki, value);
                if (less(value, values[ki])) {
                    values[ki] = value;
                    swim(pm[ki]);
                }
            }

//        // Strictly increases the value associated with 'ki' to 'value'
//        public void increase(int ki, T value) {
//            keyExistsAndValueNotNullOrThrow(ki, value);
//            if (less(values[ki], value)) {
//                values[ki] = value;
//                sink(pm[ki]);
//            }
//        }

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
                int index = -1, from = child[i], to = Math.min(sz, from + D);
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
                ArrayList<Integer> lst = new ArrayList<>(sz);
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

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() throws IOException {
            if (numChars == -1) {
                throw new IOException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new IOException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        private int nextInt() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new IOException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
