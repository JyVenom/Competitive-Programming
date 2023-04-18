import java.io.*;

public class MoortalCowmbat {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader("cowmbat.in");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowmbat.out")));

        int n = r.nextInt();
        int m = r.nextInt();
        int k = r.nextInt();
        String s = r.readLine();
        int[][] costs = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                costs[i][j] = r.nextInt();
            }
        }

        FloydWarshallSolver f = new FloydWarshallSolver(costs);
        int[][] dist = f.getAPSPMatrix();
        int[] orig = new int[n];
        for (int i = 0; i < n; i++) {
            orig[i] = s.charAt(i) - 'a';
        }
        int[][] dist2 = new int[n][m];
        if (m >= 0) System.arraycopy(dist[orig[0]], 0, dist2[0], 0, m);
        for (int i = 1; i < n; i++) {
            int I = i - 1;
            for (int j = 0; j < m; j++) {
                dist2[i][j] = dist2[I][j] + dist[orig[i]][j];
            }
        }

        int K = k - 1;
        int k2 = 2 * k;
        int[] best = new int[n];
        for (int i = k - 1; i < n; i++) {
            best[i] = findMin2(dist2, i, m);
            int min = Math.max(i - k2 + 1, K);
            for (int j = i - k; j >= min; j--) {
                best[i] = Math.min(best[i], best[j] + findMin(dist2, j, i, m));
            }
        }

        pw.println(best[n - 1]);
        pw.close();
    }

    private static int findMin(int[][] dist2, int k, int j, int m) {
        int min = 100000000;

        for (int i = 0; i < m; i++) {
            int cur = dist2[j][i] - dist2[k][i];

            if (cur < min) {
                min = cur;
            }
        }
        return min;
    }

    private static int findMin2(int[][] dist2, int j, int m) {
        int min = 100000000;

        for (int i = 0; i < m; i++) {
            if (dist2[j][i] < min) {
                min = dist2[j][i];
            }
        }
        return min;
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 17;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[BUFFER_SIZE]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }
    }


    public static class FloydWarshallSolver {

        private final int n;
        private boolean solved;
        private final int[][] dp;
        private final Integer[][] next;

        /**
         * As input, this class takes an adjacency matrix with edge weights between nodes, where
         * POSITIVE_INFINITY is used to indicate that two nodes are not connected.
         *
         * <p>NOTE: Usually the diagonal of the adjacency matrix is all zeros (i.e. matrix[i][i] = 0 for
         * all i) since there is typically no cost to go from a node to itself, but this may depend on
         * your graph and the problem you are trying to solve.
         */
        public FloydWarshallSolver(int[][] matrix) {
            n = matrix.length;
            dp = new int[n][n];
            next = new Integer[n][n];

            // Copy input matrix and setup 'next' matrix for path reconstruction.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    next[i][j] = j;
                    dp[i][j] = matrix[i][j];
                }
            }
        }

        /**
         * Runs Floyd-Warshall to compute the shortest distance between every pair of nodes.
         *
         * @return The solved All Pairs Shortest Path (APSP) matrix.
         */
        public int[][] getAPSPMatrix() {
            solve();
            return dp;
        }

        // Executes the Floyd-Warshall algorithm.
        public void solve() {
            if (solved) return;

            // Compute all pairs shortest paths.
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (dp[i][k] + dp[k][j] < dp[i][j]) {
                            dp[i][j] = dp[i][k] + dp[k][j];
                            next[i][j] = next[i][k];
                        }
                    }
                }
            }

            solved = true;
        }
    }
}
