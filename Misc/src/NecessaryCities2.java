import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class NecessaryCities2 {
    private static boolean[] curPoint;
    private static int[][] adj;
    private static int[] size;
    private static int n;

    public static void main(String[] $) throws IOException {
        InputReader ir = new InputReader();

        n = ir.nextInt() + 1;
        int m = ir.nextInt();
        size = new int[n];
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = ir.nextInt();
            to[i] = ir.nextInt();
            size[from[i]]++;
            size[to[i]]++;
        }

        adj = new int[n][];
        for (int i = 0; i < n; i++) adj[i] = new int[size[i]];
        for (int i = 0; i < m; i++) {
            adj[from[i]][--size[from[i]]] = to[i];
            adj[to[i]][--size[to[i]]] = from[i];
        }
        dfs();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int u = 0; u < n; u++) {
            if (curPoint[u]) {
                sb.append(u).append(" ");
                count++;
            }
        }
        sb.insert(0, count + "\n");

        System.out.print(sb);
    }

    private static void dfs() {
        int timer = 0;
        int[] tin = new int[n];
        int[] low = new int[n];
        int[] p = new int[n];
        int[] children = new int[n];
        boolean[] visited = new boolean[n];
        curPoint = new boolean[n];

        Arrays.fill(tin, -1);
        Arrays.fill(low, -1);
        Arrays.fill(p, -1);

        int s = 1;
        int u = s;
        visited[u] = true;
        tin[u] = low[u] = timer++;
        while (true) {
            while (size[u] < adj[u].length) {
                int v = adj[u][size[u]++];
                if (!visited[v]) {
                    children[u]++;
                    p[v] = u;
                    u = v;
                    visited[u] = true;
                    tin[u] = low[u] = timer++;
                } else if (p[u] != v && tin[v] < low[u]) {
                    low[u] = tin[v];
                }
            }
            if (u == s && size[s] == adj[s].length) break;

            int v = u;
            u = p[v];

            low[u] = Math.min(low[u], low[v]);
            if ((p[u] != -1 && low[v] >= tin[u]) || (p[u] == -1 && children[u] > 1)) {
                curPoint[u] = true;
            }
        }
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[8388608];
        private int curChar;
        private int numChars;

        public InputReader() {
            this.stream = System.in;
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
            }
            return buf[curChar++];
        }

        private int nextInt() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
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
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n';
        }
    }
}
