import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class P1399E13 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t > 0) {
            int n = ir.nextInt();
            long s = ir.nextLong();
            ArrayList<ArrayList<Edge>> tree = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                tree.add(new ArrayList<>());
            }
            Edge[] edges = new Edge[n];
            for (int i = 0; i < n - 1; ++i) {
                int u = ir.nextInt();
                int v = ir.nextInt();
                int w = ir.nextInt();

                Edge e = new Edge(i, u, v, w);
                edges[i] = e;

                tree.get(u).add(e);
                tree.get(v).add(e);

            }
            pw.println(solve(n, s, tree, edges));
            t--;
        }
        pw.close();
    }

    public static long solve(int n, long s, ArrayList<ArrayList<Edge>> tree, Edge[] edges) {
        dfs(1, 0, tree);

        long total = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < n - 1; ++i) {
            long val = (long) edges[i].wt * edges[i].leaves;
            total += val;
            long half = ((long) edges[i].wt / 2) * edges[i].leaves;
            edges[i].contri = val - half;
            pq.add(edges[i]);
        }

        if (total <= s) {
            return 0;
        }

        long count = 0;
        while (total > s) {
            Edge e = pq.remove();
            total -= e.contri;
            count++;
            e.wt = e.wt / 2;
            long val = (long) e.wt * e.leaves;
            long half = ((long) e.wt / 2) * e.leaves;
            e.contri = val - half;
            pq.add(e);
        }

        return count;
    }

    public static int dfs(int root, int p, ArrayList<ArrayList<Edge>> tree) {

        boolean leaf = true;
        int count = 0;
        for (Edge e : tree.get(root)) {
            int nbr = e.u == root ? e.v : e.u;
            if (nbr != p) {
                leaf = false;
                e.leaves += dfs(nbr, root, tree);
                count += e.leaves;
            }

        }

        if (leaf) {
            return 1;
        }
        return count;
    }

    public static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int wt;
        int id;
        int leaves;
        long contri;

        Edge(int id, int u, int v, int wt) {
            this.u = u;
            this.v = v;
            this.id = id;
            this.wt = wt;
            leaves = 0;
            contri = 0;
        }

        @Override
        public int compareTo(Edge arg0) {
            return Long.compare(arg0.contri, this.contri);
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