import java.io.*;
import java.util.ArrayList;

public class TimeIsMooney {
    private static int[] best;

    public static void main(String[] args) throws IOException {
        Reader r = new Reader("time.in");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));

        int n = r.nextInt();
        int m = r.nextInt();
        int c = r.nextInt();
        int[] earn = new int[n];
        for (int i = 0; i < n; i++) {
            earn[i] = r.nextInt();
        }
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            edges.get(r.nextInt() - 1).add(r.nextInt() - 1);
        }
        r.close();

        best = new int[n];
        dfs(edges, earn, c, 0, 0, 0);

        pw.println(best[0]);
        pw.close();
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, int[] earn, int c, int at, int cost, int earned) {
        int tot = earned - (cost * cost * c);
        if (best[at] > tot) {
            return;
        }

        best[at] = tot;

        for (int next : edges.get(at)) {
            dfs(edges, earn, c, next, cost + 1, earned + earn[next]);
        }
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
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
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        private void close() throws IOException {
            din.close();
        }
    }
}
