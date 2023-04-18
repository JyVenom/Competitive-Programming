import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P1328E {
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int N = n + 1;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int u = ir.nextInt();
            int v = ir.nextInt();

            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        int[] dist = new int[N];
        int[] tIn = new int[N];
        int[] tOut = new int[N];
        int[] par = new int[N];
        dfs(edges, tIn, tOut, par, dist, 1, -1, 0);
        for (int i = 0; i < m; i++) {
            int k = ir.nextInt();
            int[] v = new int[k];
            for (int j = 0; j < k; j++) {
                v[j] = ir.nextInt();
            }

            int u = v[0];
            for (int j = 1; j < k; j++) {
                if (dist[u] < dist[v[j]]) {
                    u = v[j];
                }
            }
            for (int j = 0; j < k; j++) {
                if (v[j] == u) {
                    continue;
                }
                if (par[v[j]] != -1) {
                    v[j] = par[v[j]];
                }
            }
            boolean ok = true;
            for (int j = 0; j < k; j++) {
                if (notAnc(tIn, tOut, v[j], u)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                pw.println("YES");
            } else {
                pw.println("NO");
            }
        }

        pw.close();
    }

    private static boolean notAnc(int[] tIn, int[] tOut, int v, int u) {
        return tIn[v] > tIn[u] || tOut[u] > tOut[v];
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, int[] tIn, int[] tOut, int[] par, int[] dist, int at, int prev, int cost) {
        tIn[at] = time++;
        dist[at] = cost;
        par[at] = prev;
        int newCost = cost + 1;
        for (int next : edges.get(at)) {
            if (next != prev) {
                dfs(edges, tIn, tOut, par, dist, next, at, newCost);
            }
        }
        tOut[at] = time;
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
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
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
