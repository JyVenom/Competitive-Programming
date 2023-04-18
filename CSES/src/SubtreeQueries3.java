import java.io.DataInputStream;
import java.io.IOException;

public class SubtreeQueries3 {
    public static void main(String[] args) throws Exception {
        Reader br = new Reader();
        int n = br.nextInt(), q = br.nextInt();
        int[] val = new int[n];
        for (int i = 0; i < n; ++i) val[i] = br.nextInt();
        int N = n - 1;
        int[] from = new int[N], to = new int[N], cnt = new int[n];
        for (int i = 0; i < N; ++i) {
            int a = br.nextInt() - 1, b = br.nextInt() - 1;
            from[i] = a;
            to[i] = b;
            cnt[from[i]]++;
            cnt[to[i]]++;
        }
        int[][] adj = new int[n][];
        for (int i = 0; i < n; ++i) adj[i] = new int[cnt[i]];
        for (int i = 0; i < N; ++i) {
            adj[from[i]][--cnt[from[i]]] = to[i];
            adj[to[i]][--cnt[to[i]]] = from[i];
        }
        int[] tin = new int[n], tout = new int[n], dq = new int[n];
        int t = 1, p = 1, P = 0;
        tin[0] = 1;
        while (0 != p) {
            while (cnt[dq[P]] != adj[dq[P]].length) {
                int root = dq[P], child = adj[root][cnt[root]++];
                if (0 != tin[child]) continue;
                tin[child] = ++t;
                dq[p++] = child;
                P++;
            }
            tout[dq[--p]] = ++t;
            P--;
        }
        long[] bit = new long[t + 1];
        for (int i = 0; i < n; ++i) {
            update(bit, tin[i], val[i]);
        }
        StringBuilder sb = new StringBuilder(q << 1);
        while (q-- > 0) {
            if (br.nextInt() == 1) {
                int s = br.nextInt() - 1, v = br.nextInt() - val[s];
                val[s] += v;
                update(bit, tin[s], v);
            } else {
                int s = br.nextInt() - 1;
                sb.append(get(bit, tout[s] - 1) - get(bit, tin[s] - 1)).append('\n');
            }
        }
        System.out.print(sb);
    }

    private static long get(long[] bit, int i) {
        long sum = 0;
        while (0 < i) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }

    private static void update(long[] bit, int i, int val) {
        while (i <= bit.length) {
            bit[i] += val;
            i += i & -i;
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buf;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buf = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buf, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buf[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buf[bufferPointer++];
        }

        public void close() throws IOException {
            din.close();
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }
    }
}
