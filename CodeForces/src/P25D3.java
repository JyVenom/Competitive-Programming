import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P25D3 {
    private static int n;
    private static ArrayList<ArrayList<Integer>> adj;
    private static int[] state;
    private static List<int[]> ans;
    private static int[] link;
    private static int[] sz;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        n = ir.nextInt();
        adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        link = new int[n + 1];
        Arrays.setAll(link, i -> i);
        sz = new int[n + 1];
        Arrays.fill(sz, 1);
        for (int it = 0; it < n - 1; it++) {
            int u = ir.nextInt(), v = ir.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
            if (notSame(u, v)) {
                unite(u, v);
            }
        }

        ans = new ArrayList<>();
        state = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (state[i] == 0) {
                dfs(i, -1);
            }
        }

        pw.println(ans.size());
        for (int[] a : ans) {
            for (int i = 0; i < 4; i++) {
                pw.print(a[i] + " ");
            }
            pw.println();
        }
        pw.close();
    }

    private static int find(int u) {
        while (u != link[u]) {
            u = link[u];
        }
        return u;
    }

    private static boolean notSame(int u, int v) {
        return find(u) != find(v);
    }

    private static void unite(int u, int v) {
        u = find(u);
        v = find(v);
        assert (u != v);
        if (sz[u] > sz[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        link[u] = v;
        sz[v] += sz[u];
    }

    private static void dfs(int u, int par) {
        state[u] = 1;
        for (int v : adj.get(u)) {
            if (v == par) {
                continue;
            }
            if (state[v] == 1) {
                // found cycle
                for (int i = 1; i <= n; i++) {
                    if (notSame(u, i)) {
                        unite(u, i);
                        ans.add(new int[]{u, v, u, i});
                        break;
                    }
                }
            } else if (state[v] == 0) {
                dfs(v, u);
            }
        }
        state[u] = 2;
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
