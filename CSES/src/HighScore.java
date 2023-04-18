import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class HighScore {
    private static final long MIN = (long) -(2.5e12);
    private static boolean inf = false;

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = fr.nextInt();
        int m = fr.nextInt();
        ArrayList<ArrayList<int[]>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            edges.get(fr.nextInt() - 1).add(new int[]{fr.nextInt() - 1, fr.nextInt()});
        }

        long[] max = new long[n];
        Arrays.fill(max, MIN);
        dfs(edges, max, 0, 0);

        if (inf) {
            pw.println(-1);
        } else {
            pw.println(max[n - 1]);
        }
        pw.close();
    }

    private static void dfs(ArrayList<ArrayList<int[]>> edges, long[] max, long cost, int at) {
        if (max[at] != MIN) {
            inf = true;
            return;
        }

        max[at] = cost;

        for (int[] next : edges.get(at)) {
            long newCost = cost + next[1];
            if (newCost > max[next[0]]) {
                dfs(edges, max, newCost, next[0]);
                if (inf) {
                    return;
                }
            }
        }
    }

    private static class FastReader {
        final private int BUFFER_SIZE = 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
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

        public void close() throws IOException {
            dis.close();
        }
    }
}
