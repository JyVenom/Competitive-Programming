import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class P449B7 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int k = ir.nextInt();
        int N = n + 1;
        ArrayList<ArrayList<Edge>> node = new ArrayList<>(N);
        for (int i = 0; i <= n; i++) {
            node.add(new ArrayList<>());
        }
        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        for (int i = 1; i <= m; ++i) {
            int u = ir.nextInt();
            int v = ir.nextInt();
            int w = ir.nextInt();
            node.get(u).add(new Edge(v, w));
            node.get(v).add(new Edge(u, w));
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int ans = 0;
        boolean[] hasTrain = new boolean[N]; // Multiple trains that go to same city
        boolean[] inq = new boolean[N]; // In queue
        for (int i = 1; i <= k; ++i) {
            int u = ir.nextInt();
            int v = ir.nextInt();
            if (hasTrain[u])
                ans++;
            else {
                hasTrain[u] = true;
                queue.add(u);
                inq[u] = true;
            }
            if (v < dist[u]) {
                dist[u] = v;
            }
        }
        queue.add(1);
        dist[1] = 0;
        inq[1] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            inq[cur] = false;
            for (Edge next : node.get(cur)) {
                long newDist = dist[cur] + next.val;
                if (newDist <= dist[next.to] && hasTrain[next.to]) {
                    hasTrain[next.to] = false;
                    ans++;
                }
                if (newDist < dist[next.to]) {
                    dist[next.to] = newDist;
                    if (!inq[next.to]) {
                        inq[next.to] = true;
                        queue.add(next.to);
                    }
                }
            }
        }

        pw.println(ans);
        pw.close();
    }

    private static class Edge {
        int to, val;

        public Edge(int to, int val) {
            this.to = to;
            this.val = val;
        }
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dist;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dist = new DataInputStream(System.in);
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

        private void fillBuffer() throws IOException {
            bytesRead = dist.read(buffer, bufferPointer = 0, BUFFER_SIZE);
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
