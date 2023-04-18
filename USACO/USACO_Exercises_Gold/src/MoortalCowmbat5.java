import java.io.*;
import java.util.Arrays;

public class MoortalCowmbat5 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
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
        r.close();
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();

        int N = n + 1;
        FloydWarshallSolver f = new FloydWarshallSolver(costs);
        int[][] dist = f.getAPSPMatrix();
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        int[][] cost = new int[N][m];
        int[][] sums = new int[N][m];
        for (int i = 1; i <= n; i++) {
            int I = i - 1;
            for (int j = 0; j < m; j++) {
                cost[i][j] = dist[s.charAt(I) - 'a'][j];
                sums[i][j] = sums[I][j] + cost[i][j];
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        int[][] dp = new int[N][m];
        int[] min = new int[N];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 100000000);
        }
        Arrays.fill(min, 100000000);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        min[0] = 0;
        for (int i = 1; i <= n; i++) {
            int ik = i - k;
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + cost[i][j]);
                if (i >= k) {
                    dp[i][j] = Math.min(dp[i][j], sums[i][j] - sums[ik][j] + min[ik]);
                }
                min[i] = Math.min(min[i], dp[i][j]);
            }
        }

        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        pw.println(min[n]);
        pw.close();
        System.out.println(System.currentTimeMillis() - start);
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
        private final int[][] dp;
        private final Integer[][] next;
        private boolean solved;

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
