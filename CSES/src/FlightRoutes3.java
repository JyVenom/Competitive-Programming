import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FlightRoutes3 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int k = ir.nextInt();
        DijkstraShortestPath d = new DijkstraShortestPath(n);
        for (int i = 0; i < m; i++) {
            d.addEdge(ir.nextInt() - 1, ir.nextInt() - 1, ir.nextInt());
        }

        ArrayList<Long> ans = d.dijkstra(0, n - 1, k);
        StringBuilder sb = new StringBuilder(2 * k);
        for (long an : ans) {
            sb.append(an).append(" ");
        }

        System.out.print(sb);
    }

    private static class DijkstraShortestPath {
        private final int n;
        private final ArrayList<ArrayList<Edge>> graph;

        public DijkstraShortestPath(int n) {
            this.n = n;
            graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
        }

        public void addEdge(int from, int to, long cost) {
            graph.get(from).add(new Edge(to, cost));
        }

        public ArrayList<Long> dijkstra(int start, int end, int k) {
            ArrayList<Long> ans = new ArrayList<>();
            int[] count = new int[n];
            PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
            pq.add(new State(start, 0L));

            while (!pq.isEmpty() && count[end] < k) {
                State cur = pq.poll();
                int nodeId = cur.at;
                long minValue = cur.cost;

                count[nodeId]++;
                if (nodeId == end) {
                    ans.add(minValue);
                }
                if (count[nodeId] <= k) {
                    for (Edge edge : graph.get(nodeId)) {
                        pq.add(new State(edge.to, minValue + edge.cost));
                    }
                }
            }

            return ans;
        }

        private static class State {
            int at;
            long cost;

            public State(int at, long cost) {
                this.at = at;
                this.cost = cost;
            }
        }

        public static class Edge {
            int to;
            long cost;

            public Edge(int to, long cost) {
                this.to = to;
                this.cost = cost;
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
