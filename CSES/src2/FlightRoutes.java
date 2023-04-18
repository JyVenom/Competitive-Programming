import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FlightRoutes {
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

        ArrayList<Long> ans = bfs(graph, n - 1, k);
        StringBuilder sb = new StringBuilder(2 * k);
        for (long an : ans) {
            sb.append(an).append(" ");
        }

        System.out.print(sb);
    }

    private static ArrayList<Long> bfs(ArrayList<ArrayList<Edge>> graph, int N, int k) {
        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
        queue.add(new State(0, 0));
        ArrayList<Long> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.loc == N) {
                ans.add(cur.cost);
                if (ans.size() == k) {
                    return ans;
                }
            }

            for (Edge next : graph.get(cur.loc)) {
                queue.add(new State(next.to, cur.cost + next.cost));
            }
        }
        return ans; //should never happen
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