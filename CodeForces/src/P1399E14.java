import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class P1399E14 {
    private static final ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        for (int t1 = 0; t1 < t; t1++) {
            int n = ir.nextInt();
            long s = ir.nextLong();
            adj.clear();
            for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

            for (int i = 0; i < n - 1; i++) {
                int a = ir.nextInt() - 1;
                int b = ir.nextInt() - 1;
                int dist = ir.nextInt();
                int[] a1 = {b, dist};
                int[] a2 = {a, dist};
                adj.get(a).add(a1);
                adj.get(b).add(a2);
            }

            PriorityQueue<EdgeValue> edgeTrueWeights = new PriorityQueue<>();
            dfsLeaves(0, -1, edgeTrueWeights);
            long sum = 0;
            for (EdgeValue weight : edgeTrueWeights) sum += (long) weight.dist * weight.leaves;
            int moves = 0;
            while (sum > s) {
                EdgeValue heaviest = edgeTrueWeights.remove();
                sum -= (long) heaviest.dist * heaviest.leaves;
                heaviest.dist /= 2;
                sum += (long) heaviest.dist * heaviest.leaves;
                int dist = heaviest.dist;
                int leaves = heaviest.leaves;
                heaviest.comparison = (long) dist * leaves - ((long) (dist / 2) * leaves);
                moves++;
                edgeTrueWeights.add(heaviest);
            }
            pw.println(moves);
        }

        pw.close();
    }

    private static int dfsLeaves(int node, int prev, PriorityQueue<EdgeValue> pq) {
        if (adj.get(node).size() == 1 && adj.get(node).get(0)[0] == prev) return 1;

        int leaves = 0;
        for (int[] adjacent : adj.get(node)) {
            if (adjacent[0] == prev) continue;
            int childLeaves = dfsLeaves(adjacent[0], node, pq);
            leaves += childLeaves;
            pq.add(new EdgeValue(adjacent[1], childLeaves));
        }

        return leaves;
    }

    private static class EdgeValue implements Comparable<EdgeValue> {
        int dist, leaves;
        long comparison;

        public EdgeValue(int dist, int leaves) {
            this.dist = dist;
            this.leaves = leaves;
            comparison = (long) dist * leaves - ((long) (dist / 2) * leaves);
        }

        @Override
        public int compareTo(EdgeValue o) {
            return Long.compare(o.comparison, comparison);
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