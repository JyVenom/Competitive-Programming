//e*log(v)/log(e/v)
//v = mn
//e = 4v
//1<= n,m <= 1,000
//39,863,137.1

import java.io.*;
import java.util.*;

public class BessiesDream5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dream.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
        String[] line = br.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int max = n * m;
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Dijkstra d = new Dijkstra(max, m);
        d.setGraph2(map);
        int N = n - 1;
        int M = m - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    if (i > 0) {
                        if (map[i - 1][j] != 0) {
                            d.addEdge(i * n + j, (i - 1) * n + j, 1);
                        }
                    }
                    if (i < N) {
                        if (map[i + 1][j] != 0) {
                            d.addEdge(i * n + j, (i + 1) * n + j, 1);
                        }
                    }
                    if (j > 0) {
                        if (map[i][j - 1] != 0) {
                            d.addEdge(i * n + j, i * n + j - 1, 1);
                        }
                    }
                    if (j < M) {
                        if (map[i][j + 1] != 0) {
                            d.addEdge(i * n + j, i * n + j + 1, 1);
                        }
                    }
                }
            }
        }

        int ans = d.dijkstra(0, n * m - 1);

        pw.println(ans);
        pw.close();
    }

    public static class Dijkstra {
        private final int n;
        private final int m;
        private int edgeCount;
        private List<List<Edge>> graph;
        private int[] graph2;

        /**
         * Initialize the solver by providing the graph size and a starting node. Use the {@link #addEdge}
         * method to actually add edges to the graph.
         *
         * @param n - The number of nodes in the graph.
         */
        public Dijkstra(int n, int m) {
            this.n = n;
            this.m = m;
            createEmptyGraph();
        }

        // Construct an empty graph with n nodes including the source and sink nodes.
        private void createEmptyGraph() {
            graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        }

        private void setGraph2(int[][] graph) {
            graph2 = new int[graph.length * m];
            for (int i = 0; i < graph.length; i++) {
                System.arraycopy(graph[i], 0, graph2, i * graph.length, graph[0].length);
            }
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

//        /**
//         * Use {@link #addEdge} method to add edges to the graph and use this method to retrieve the
//         * constructed graph.
//         */
//        public List<List<Edge>> getGraph() {
//            return graph;
//        }

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

            boolean[][] visited = new boolean[n][2];
            Integer[] prev = new Integer[n];

            boolean orange = false;
            int dir; //0 means from above, 1 means from below, 2 means from left, 3 means from right
            int nodeId;
            while (!ipq.isEmpty()) {
                nodeId = ipq.peekMinKeyIndex();

                int isSmelly = orange ? 1 : 0;
                visited[nodeId][isSmelly] = true;
                int minValue = ipq.pollMinValue();

                // We already found a better path before we got to
                // processing this node so we can ignore it.
                if (minValue > dist[nodeId]) continue;

                if (graph2[nodeId] == 2) {
                    orange = true;
                    for (Edge edge : graph.get(nodeId)) {

                        // We cannot get a shorter path by revisiting
                        // a node we have already visited before.
                        if (visited[edge.to][1]) continue;

                        // Relax edge by updating minimum cost if applicable.
                        int newDist = dist[nodeId] + edge.cost;
                        prev[edge.to] = nodeId;
                        dist[edge.to] = newDist;
                        // Insert the cost of going to a node for the first time in the PQ,
                        // or try and update it to a better value by calling decrease.
                        if (!ipq.contains(edge.to)) ipq.insert(edge.to, newDist);
                        else ipq.decrease(edge.to, newDist);
                    }
                } else if (graph2[nodeId] == 4) {
                    orange = false;
                    if (prev[nodeId] == nodeId - 1) {
                        dir = 2;
                    } else if (prev[nodeId] == nodeId + 1) {
                        dir = 3;
                    } else if (prev[nodeId] == nodeId - m) {
                        dir = 0;
                    } else {
                        dir = 1;
                    }
                    boolean broke = false;
                    for (Edge edge : graph.get(nodeId)) {
                        if (visited[edge.to][0]) continue;

                        if (edge.to == nodeId - 1) {
                            if (dir == 3) {
                                if (graph2[edge.to] == 3) {
                                    broke = true;
                                    break;
                                }
                                // Relax edge by updating minimum cost if applicable.
                                int newDist = dist[nodeId] + edge.cost;

                                prev[edge.to] = nodeId;
                                dist[edge.to] = newDist;
                                // Insert the cost of going to a node for the first time in the PQ,
                                // or try and update it to a better value by calling decrease.
                                if (!ipq.contains(edge.to)) ipq.insert(edge.to, newDist);
                                else ipq.decrease(edge.to, newDist);


                            }
                        } else if (edge.to == nodeId + 1) {
                            if (dir == 2) {
                                if (graph2[edge.to] == 3) {
                                    broke = true;
                                    break;
                                }
                                // Relax edge by updating minimum cost if applicable.
                                int newDist = dist[nodeId] + edge.cost;
                                prev[edge.to] = nodeId;
                                dist[edge.to] = newDist;
                                // Insert the cost of going to a node for the first time in the PQ,
                                // or try and update it to a better value by calling decrease.
                                if (!ipq.contains(edge.to)) ipq.insert(edge.to, newDist);
                                else ipq.decrease(edge.to, newDist);

                            }
                        } else if (edge.to == nodeId - m) {
                            if (dir == 1) {
                                if (graph2[edge.to] == 3) {
                                    broke = true;
                                    break;
                                }
                                // Relax edge by updating minimum cost if applicable.
                                int newDist = dist[nodeId] + edge.cost;
                                prev[edge.to] = nodeId;
                                dist[edge.to] = newDist;
                                // Insert the cost of going to a node for the first time in the PQ,
                                // or try and update it to a better value by calling decrease.
                                if (!ipq.contains(edge.to)) ipq.insert(edge.to, newDist);
                                else ipq.decrease(edge.to, newDist);

                            }
                        } else {
                            if (dir == 0) {
                                if (graph2[edge.to] == 3) {
                                    broke = true;
                                    break;
                                }
                                // Relax edge by updating minimum cost if applicable.
                                int newDist = dist[nodeId] + edge.cost;
                                prev[edge.to] = nodeId;
                                dist[edge.to] = newDist;
                                // Insert the cost of going to a node for the first time in the PQ,
                                // or try and update it to a better value by calling decrease.
                                if (!ipq.contains(edge.to)) ipq.insert(edge.to, newDist);
                                else ipq.decrease(edge.to, newDist);

                            }
                        }
                    }

                    if (broke) {
                        for (Edge edge : graph.get(nodeId)) {
                            if (visited[edge.to][0]) continue;
                            if (graph2[edge.to] == 3) continue;

                            int newDist = dist[nodeId] + edge.cost;
                            prev[edge.to] = nodeId;
                            dist[edge.to] = newDist;
                            // Insert the cost of going to a node for the first time in the PQ,
                            // or try and update it to a better value by calling decrease.
                            if (!ipq.contains(edge.to)) ipq.insert(edge.to, newDist);
                            else ipq.decrease(edge.to, newDist);
                        }
                    }
                } else {
                    for (Edge edge : graph.get(nodeId)) {

                        // We cannot get a shorter path by revisiting
                        // a node we have already visited before.
                        if (visited[edge.to][isSmelly]) continue;

                        if (graph2[edge.to] == 3 && !orange) {
                            continue;
                        }

                        // Relax edge by updating minimum cost if applicable.
                        int newDist = dist[nodeId] + edge.cost;
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

//            public int pollMinKeyIndex() {
//                int minKey = peekMinKeyIndex();
//                delete(minKey);
//                return minKey;
//            }

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

//            @SuppressWarnings("unchecked")
//            public T valueOf(int ki) {
//                keyExistsOrThrow(ki);
//                return (T) values[ki];
//            }

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

//            @SuppressWarnings("unchecked")
//            public T update(int ki, T value) {
//                keyExistsAndValueNotNullOrThrow(ki, value);
//                final int i = pm[ki];
//                T oldValue = (T) values[ki];
//                values[ki] = value;
//                sink(i);
//                swim(i);
//                return oldValue;
//            }

            // Strictly decreases the value associated with 'ki' to 'value'
            public void decrease(int ki, T value) {
                keyExistsAndValueNotNullOrThrow(ki, value);
                if (less(value, values[ki])) {
                    values[ki] = value;
                    swim(pm[ki]);
                }
            }

//            // Strictly increases the value associated with 'ki' to 'value'
//            public void increase(int ki, T value) {
//                keyExistsAndValueNotNullOrThrow(ki, value);
//                if (less(values[ki], value)) {
//                    values[ki] = value;
//                    sink(pm[ki]);
//                }
//            }

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
