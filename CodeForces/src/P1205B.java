import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class P1205B {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        ArrayList<ArrayList<Integer>> edges = ir.getEdges(n);
        int max = edges.size() + 1;
        int ans = max;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, bfs(edges, i, ans));
        }

        pw.println(ans == max ? -1 : ans);
        pw.close();
    }

    private static int bfs(ArrayList<ArrayList<Integer>> edges, int start, int ans) {
        ArrayDeque<Integer> queue = new ArrayDeque<>(edges.size());
        int[] dist = new int[edges.size()];
        int[] parent = new int[edges.size()];

        Arrays.fill(parent, -1);
        Arrays.fill(dist, edges.size());

        dist[start] = 0;
        queue.addLast(start);
        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();

            int newDist = dist[cur] + 1;
            for (int next : edges.get(cur)) {
                if (dist[next] == edges.size()) {
                    dist[next] = newDist;
                    parent[next] = cur;
                    queue.addLast(next);
                } else if (parent[cur] != next && parent[next] != cur) {
                    ans = Math.min(ans, dist[cur] + dist[next] + 1);
                }
            }
        }
        return ans;
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 16;
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
            do
                ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do
                ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        private ArrayList<ArrayList<Integer>> getEdges(int n) throws IOException {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextLong();
            }

            ArrayList<ArrayList<Integer>> edges = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                edges.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if ((a[i] & a[j]) != 0) {
                        edges.get(i).add(j);
                        edges.get(j).add(i);
                    }
                }
            }
            return edges;
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
