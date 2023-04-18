import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class P1388D10 {
    static int N;
    static long[] A;
    static int[] B;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);
        N = ir.nextIntNonNeg();
        A = new long[N];
        B = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = ir.nextLong();
        }
        for (int i = 0; i < N; i++) {
            B[i] = ir.nextInt();
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (B[i] != -1) cnt++;
        }
        int[] S = new int[cnt];
        int[] V = new int[cnt];
        cnt = 0;
        for (int i = 0; i < N; i++) {
            if (B[i] != -1) {
                int b = B[i] - 1;
                int idx = cnt++;
                S[idx] = i;
                V[idx] = b;
            }
        }
        int[][] G = adjD(N, S, V);
        int[] topo = khan(N, G);

        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();
        long ans = 0;
        for (int i = 0; i < N; i++) {
            assert topo != null;
            int idx = topo[i];
            long a = A[idx];
            int b = B[idx];
            ans += a;
            if (b != -1) {
                if (a >= 0) {
                    A[b - 1] += a;
                    plus.add(idx + 1);
                } else {
                    minus.add(idx + 1);
                }
            } else {
                plus.add(idx + 1);
            }
        }
        Collections.reverse(minus);
        plus.addAll(minus);

        pw.println(ans);
        StringBuilder sb = new StringBuilder();
        for (Integer integer : plus) {
            sb.append(integer).append(" ");
        }
        pw.println(sb);
        pw.close();
    }

    static int[][] adjD(int n, int[] from, int[] to) {
        int[][] adj = new int[n][];
        int[] cnt = new int[n];
        for (int f : from) {
            cnt[f]++;
        }
        for (int i = 0; i < n; i++) {
            adj[i] = new int[cnt[i]];
        }
        for (int i = 0; i < from.length; i++) {
            adj[from[i]][--cnt[from[i]]] = to[i];
        }
        return adj;
    }

    static int[] khan(int V, int[][] G) {
        int[] deg = new int[V];
        for (int[] tos : G) {
            for (int to : tos) {
                deg[to]++;
            }
        }

        int[] q = new int[V];
        int a = 0, b = 0;
        for (int v = 0; v < V; v++) {
            if (deg[v] == 0) q[b++] = v;
        }

        int[] ret = new int[V];
        int idx = 0;
        while (a != b) {
            int v = q[a++];
            ret[idx++] = v;
            for (int to : G[v]) {
                deg[to]--;
                if (deg[to] == 0) {
                    q[b++] = to;
                }
            }
        }

        for (int v = 0; v < V; v++) {
            if (deg[v] != 0) return null;
        }
        return ret;
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
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

        private int nextIntNonNeg() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = dis.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}
