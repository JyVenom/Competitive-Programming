import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class P1399E12 {
    private static ArrayList<ArrayList<Edge>> g = new ArrayList<>();
    private static int[] cnt;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            long s = ir.nextLong();

            int N = n - 1;
            int[] w = new int[N];
            cnt = new int[N];
            g = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                g.add(new ArrayList<>());
            }
            for (int i = 0; i < N; i++) {
                int v = ir.nextInt() - 1;
                int u = ir.nextInt() - 1;
                w[i] = ir.nextInt();

                g.get(u).add(new Edge(v, i));
                g.get(v).add(new Edge(u, i));
            }

            dfs();
            PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o2.dif, o1.dif));
            long cur = 0;
            for (int i = 0; i < n - 1; ++i) {
                pq.add(new Pair(i, (long) w[i] * cnt[i] - (long) (w[i] / 2) * cnt[i]));
                cur += (long) w[i] * cnt[i];
            }
            int ans = 0;
            while (cur > s) {
                Pair p = pq.poll();
                assert p != null;
                cur -= p.dif;
                w[p.ind] /= 2;
                pq.add(new Pair(p.ind, (long) w[p.ind] * cnt[p.ind] - (long) (w[p.ind] / 2) * cnt[p.ind]));
                ans++;
            }
            pw.println(ans);
        }

        pw.close();
    }

    private static void dfs() {
        for (Edge e : g.get(0)) {
            dfs(e.to, e.ind);
        }
    }

    private static void dfs(int v, int p) {
        if (g.get(v).size() == 1) cnt[p] = 1;
        for (Edge e : g.get(v)) {
            if (e.ind == p) continue;
            dfs(e.to, e.ind);
            if (p != -1) cnt[p] += cnt[e.ind];
        }
    }

    private static class Pair {
        int ind;
        long dif;

        public Pair(int ind, long dif) {
            this.ind = ind;
            this.dif = dif;
        }
    }

    private static class Edge {
        int to, ind;

        public Edge(int to, int ind) {
            this.to = to;
            this.ind = ind;
        }
    }

    private static class InputReader {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
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
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
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