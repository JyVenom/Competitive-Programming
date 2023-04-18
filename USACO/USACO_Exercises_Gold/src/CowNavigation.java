import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

public class CowNavigation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownav.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));

        int n = Integer.parseInt(br.readLine());
        int N = n - 1;
        boolean[][] data = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = line.charAt(j);
                if (c == 'H') {
                    data[N - i][j] = true;
                }
            }
        }

        int row = n * 4;
        int num = n * row;
        Dijkstra d = new Dijkstra(num + 1);
        d.addEdge(num - 2, num, 0); //(-4 + 2)
        d.addEdge(num - 3, num, 0); //(-4 + 1)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (data[i][j]) {
                    continue;
                }

                int cur = (i * row) + (j * 4);
                int right = cur + 1;
                int down = cur + 2;
                int left = cur + 3;
                d.addEdge(cur, left, 1);
                d.addEdge(cur, right, 1);
                d.addEdge(right, cur, 1);
                d.addEdge(right, down, 1);
                d.addEdge(down, right, 1);
                d.addEdge(down, left, 1);
                d.addEdge(left, down, 1);
                d.addEdge(left, cur, 1);
            }
        }
        int temp = (N) * row;
        for (int i = 1; i < n; i++) { ///top and bottom row
            int cur = i * 4;
            int prev = cur - 4;
            int cur2 = cur + temp;
            int prev2 = prev + temp;
            int I = i - 1;
            if (!data[0][i] && !data[0][I]) {
                d.addEdge(cur + 3, prev + 3, 1);
                d.addEdge(prev + 1, cur + 1, 1);
            }
            if (!data[N][i] && !data[N][I]) {
                d.addEdge(cur2 + 3, prev2 + 3, 1);
                d.addEdge(prev2 + 1, cur2 + 1, 1);
            }
        }
        for (int i = 1; i < N; i++) { //top and bottom row adding edges in
            int cur = i * 4;
            int next = cur + row;
            int cur2 = cur + temp;
            int next2 = cur2 - row;
            if (!data[0][i] && !data[1][i]) {
                d.addEdge(cur + 2, next + 2, 1);
            }
            if (!data[N][i] && !data[N - 1][i]) {
                d.addEdge(cur2, next2, 1);
            }
        }
        temp = (N) * 4;
        for (int i = 1; i < n; i++) { //first and last col
            int cur = i * row;
            int prev = cur - row;
            int cur2 = cur + temp;
            int prev2 = prev + temp;
            int I = i - 1;
            if (!data[i][0] && !data[I][0]) {
                d.addEdge(cur, prev, 1);
                d.addEdge(prev + 2, cur + 2, 1);
            }
            if (!data[i][N] && !data[I][N]) {
                d.addEdge(cur2, prev2, 1);
                d.addEdge(prev2 + 2, cur2 + 2, 1);
            }
        }
        for (int i = 1; i < N; i++) { //first and last col adding in
            int cur = i * row;
            int next = cur + 4;
            int cur2 = cur + temp;
            int next2 = cur2 - 4;

            if (!data[i][0] && !data[i][1]) {
                d.addEdge(cur + 1, next + 1, 1);
            }
            if (!data[i][N] && ! data[i][N - 1]) {
                d.addEdge(cur2 + 3, next2 + 3, 1);
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (data[i][j]) {
                    continue;
                }

                int cur = (i * row) + (j * 4);
                int right = cur + 1;
                int down = cur + 2;
                int left = cur + 3;
                int up2 = cur - row;
                int right2 = right + 4; //(4 + 1)
                int down2 = down + row;
                int left2 = left - 4; //(-4 + 3)
                if (!data[i - 1][j]) {
                    d.addEdge(cur, up2, 1);
                }
                if (!data[i][j + 1]) {
                    d.addEdge(right, right2, 1);
                }
                if (!data[i + 1][j]) {
                    d.addEdge(down, down2, 1);
                }
                if (!data[i][j - 1]) {
                    d.addEdge(left, left2, 1);
                }
            }
        }
        int a = d.dijkstra(2, num); //down
        ArrayList<Integer> tempA = new ArrayList<>();
        if (a != Integer.MAX_VALUE) {
            for (Integer at = num; at != null; at = d.prev[at]) {
                tempA.add(at);
            }
        }
        Collections.reverse(tempA);
        ArrayList<Integer> pathA = new ArrayList<>();
        int size = tempA.size() - 1;
        for (int i = 1; i < size; i++) {
            int cur = tempA.get(i);
            int prev = tempA.get(i - 1);
            int curRow = cur / row;
            int curCol = (cur - (curRow * row)) / 4;
            int curDir = cur - (curRow * row) - (curCol * 4);
            int prevRow = prev / row;
            int prevCol = (prev - (prevRow * row)) / 4;
            int prevDir = prev - (prevRow * row) - (prevCol * 4);
            if (curRow == prevRow && curCol == prevCol) {
                if (curDir == 0 && prevDir == 3) {
                    pathA.add(2);
                } else if (curDir == 3 && prevDir == 0) {
                    pathA.add(1);
                } else {
                    if (curDir < prevDir) {
                        pathA.add(1);
                    } else {
                        pathA.add(2);
                    }
                }
            } else {
                pathA.add(0);
            }
        }
        int[] endA = simulate(pathA, data, 1, N);
        a += d.dijkstra((endA[0] * row) + (endA[1] * 4) + endA[2], num);
        int b = d.dijkstra(1, num);
        ArrayList<Integer> tempB = new ArrayList<>();
        if (b != Integer.MAX_VALUE) {
            for (Integer at = num; at != null; at = d.prev[at]) {
                tempB.add(at);
            }
        }
        Collections.reverse(tempB);
        ArrayList<Integer> pathB = new ArrayList<>();
        for (int i = 1; i < tempB.size(); i++) {
            int cur = tempB.get(i);
            int prev = tempB.get(i - 1);
            int curRow = cur / row;
            int curCol = (cur - (curRow * row)) / 4;
            int curDir = cur - (curRow * row) - (curCol * 4);
            int prevRow = prev / row;
            int prevCol = (prev - (prevRow * row)) / 4;
            int prevDir = prev - (prevRow * row) - (prevCol * 4);
            if (curRow == prevRow && curCol == prevCol) {
                if (curDir == 0 && prevDir == 3) {
                    pathB.add(2);
                } else if (curDir == 3 && prevDir == 0) {
                    pathB.add(1);
                } else {
                    if (curDir < prevDir) {
                        pathB.add(1);
                    } else {
                        pathB.add(2);
                    }
                }
            } else {
                pathB.add(0);
            }
        }
        int[] endB = simulate(pathB, data, 2, N);
        b += d.dijkstra((endB[0] * row) + (endB[1] * 4) + endB[2], num);
        int min = Math.min(a, b);

        pw.println(min);
        pw.close();
    }

    private static int[] simulate(ArrayList<Integer> path, boolean[][] data, int initDir, int N) {
        int row = 0;
        int col = 0;
        int dir = initDir;
        for (int cur : path) {
            if (cur == 0) {
                if (dir == 0) {
                    if (row > 0 && !data[row - 1][col]) {
                        row--;
                    }
                } else if (dir == 1) {
                    if (col < N && !data[row][col + 1]) {
                        col++;
                    }
                } else if (dir == 2) {
                    if (row < N && !data[row + 1][col]) {
                        row++;
                    }
                } else {
                    if (col > 0 && !data[row][col - 1]) {
                        col--;
                    }
                }
            } else if (cur == 1) {
                dir = (dir + 3) % 4;
            } else {
                dir = (dir + 1) % 4;
            }
        }
        return new int[]{row, col, dir};
    }

    public static class Dijkstra {
        private final int n;
        private int edgeCount;
        private Integer[] prev;
        private ArrayList<ArrayList<Edge>> graph;

        /**
         * Initialize the solver by providing the graph size and a starting node. Use the {@link #addEdge}
         * method to actually add edges to the graph.
         *
         * @param n - The number of nodes in the graph.
         */
        public Dijkstra(int n) {
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
        }

        // Run Dijkstra's algorithm on a directed graph to find the shortest path
        // from a starting node to an ending node. If there is no path between the
        // starting node and the destination node the returned value is set to be
        // Integer.MAX_VALUE.
        public int dijkstra(int start, int end) {

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
            prev = new Integer[n];

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
            return Integer.MAX_VALUE;
        }

        /**
         * Reconstructs the shortest path (of nodes) from 'start' to 'end' inclusive.
         *
         * @return An array of node indexes of the shortest path from 'start' to 'end'. If 'start' and
         * 'end' are not connected then an empty array is returned.
         */
        public ArrayList<Integer> reconstructPath(int start, int end) {
            if (end < 0 || end >= n) throw new IllegalArgumentException("Invalid node index");
            if (start < 0 || start >= n) throw new IllegalArgumentException("Invalid node index");
            ArrayList<Integer> path = new ArrayList<>();
            int dist = dijkstra(start, end);
            if (dist == Integer.MAX_VALUE) return path;
            for (Integer at = end; at != null; at = prev[at]) path.add(at);
            Collections.reverse(path);
            return path;
        }

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
}
