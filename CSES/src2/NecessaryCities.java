import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NecessaryCities {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        int m = ir.readInt();
        Graph g = new Graph(n);
        for (int i = 0; i < m; i++) {
            g.addEdge(ir.readInt() - 1, ir.readInt() - 1);
        }
        ir.close();

        StringBuilder ans = g.AP();

        pw.print(ans);

        pw.close();
    }

    private static class Graph {
        static final int NIL = -1;
        private final int V;
        private final ArrayList<ArrayList<Integer>> adj;
        int time = 0;

        Graph(int v) {
            V = v;
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; ++i) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int v, int w) {
            adj.get(v).add(w);
            adj.get(w).add(v);
        }

        void APUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent, boolean[] ap) {
            int children = 0;
            visited[u] = true;
            disc[u] = low[u] = ++time;
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    children++;
                    parent[v] = u;
                    APUtil(v, visited, disc, low, parent, ap);
                    low[u] = Math.min(low[u], low[v]);
                    if (parent[u] == NIL && children > 1)
                        ap[u] = true;
                    if (parent[u] != NIL && low[v] >= disc[u])
                        ap[u] = true;
                } else if (v != parent[u])
                    low[u] = Math.min(low[u], disc[v]);
            }
        }

        private StringBuilder AP() {
            boolean[] visited = new boolean[V];
            int[] disc = new int[V];
            int[] low = new int[V];
            int[] parent = new int[V];
            boolean[] ap = new boolean[V];

            for (int i = 0; i < V; i++) {
                parent[i] = NIL;
                visited[i] = false;
                ap[i] = false;
            }

            for (int i = 0; i < V; i++)
                if (!visited[i])
                    APUtil(i, visited, disc, low, parent, ap);

            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int i = 0; i < V; i++) {
                if (ap[i]) {
                    sb.append(i + 1).append(" ");
                    count++;
                }
            }
            sb.insert(0, count + "\n");
            return sb;
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

        private void close() throws IOException {
            stream.close();
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

        private int readInt() throws IOException {
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
            return c == ' ' || c == '\n' || c == -1;
        }
    }
}
