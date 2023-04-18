import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1328E4 {
    static int[][] G;
    static int[] inT, outT, parent, depth;
    static int time;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int tmp = n - 1;
        int[] from = new int[tmp];
        int[] to = new int[tmp];
        for (int i = 0; i < tmp; i++) {
            from[i] = ir.nextInt();
            to[i] = ir.nextInt();
        }

        int N = n + 1;
        inT = new int[N];
        outT = new int[N];
        depth = new int[N];
        parent = new int[N];
        time = 0;
        G = packGraph(from, to, N, tmp);
        dfs(1, -1, 0);
        while (m-- > 0) {
            int k = ir.nextInt();
            int[] nodes = new int[k];
            for (int i = 0; i < k; i++) nodes[i] = ir.nextInt();
            int highest = 0;
            for (int i = 0; i < k; i++) if (depth[nodes[i]] > depth[nodes[highest]]) highest = i;
            boolean flag = true;
            for (int i = 0; i < k; i++) {
                if (isA(nodes[i], nodes[highest]) || isA(parent[nodes[i]], nodes[highest])) continue;
                flag = false;
                break;
            }
            pw.println(flag ? "YES" : "NO");
        }

        pw.close();
    }

    public static void dfs(int node, int prev, int d) {
        inT[node] = time++;
        parent[node] = prev;
        depth[node] = d;
        int newD = d + 1;
        for (int i : G[node])
            if (i != prev)
                dfs(i, node, newD);
        outT[node] = time;
    }

    public static boolean isA(int a, int b) {
        return inT[a] <= inT[b] && outT[a] >= outT[b];
    }

    public static int[][] packGraph(int[] from, int[] to, int N, int m) {
        int[][] g = new int[N][];
        int[] p = new int[N];
        for (int i = 0; i < m; i++) p[from[i]]++;
        for (int i = 0; i < m; i++) p[to[i]]++;
        for (int i = 0; i < N; i++) g[i] = new int[p[i]];
        for (int i = 0; i < m; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
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
