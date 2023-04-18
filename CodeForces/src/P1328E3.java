import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class P1328E3 {
    public static void main(String[] args) throws Exception {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt(), Q = ir.nextInt();
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = ir.nextInt() - 1;
            to[i] = ir.nextInt() - 1;
        }

        int[][] g = packU(n, from, to);
        int[][] pars = parents(g, 0);
        int[] par = pars[0];
        int[][] rs = makeRights(g, par, 0);
        int[] right = rs[2], iord = rs[1];
        for (int z = 0; z < Q; z++) {
            int K = ir.nextInt();
            int inf = 0, sup = n - 1;
            for (int i = 0; i < K; i++) {
                int c = ir.nextInt() - 1;
                int p = par[c];
                if (p != -1) c = p;
                inf = Math.max(inf, iord[c]);
                sup = Math.min(sup, right[iord[c]]);
            }
            pw.println(inf <= sup ? "YES" : "NO");
        }

        pw.close();
    }

    public static int[] sortByPreorder(int[][] g, int root) {
        int n = g.length;
        int[] stack = new int[n];
        int[] ord = new int[n];
        boolean[] ved = new boolean[n];
        stack[0] = root;
        int p = 1;
        int r = 0;
        ved[root] = true;
        while (p > 0) {
            int cur = stack[p - 1];
            ord[r++] = cur;
            p--;
            for (int e : g[cur]) {
                if (!ved[e]) {
                    ved[e] = true;
                    stack[p++] = e;
                }
            }
        }
        return ord;
    }

    public static int[][] makeRights(int[][] g, int[] par, int root) {
        int n = g.length;
        int[] ord = sortByPreorder(g, root);
        int[] iord = new int[n];
        for (int i = 0; i < n; i++) iord[ord[i]] = i;

        int[] right = new int[n];
        for (int i = n - 1; i >= 1; i--) {
            if (right[i] == 0) right[i] = i;
            int p = iord[par[ord[i]]];
            right[p] = Math.max(right[p], right[i]);
        }
        return new int[][]{ord, iord, right};
    }

    public static int[][] parents(int[][] g, int root) {
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);

        int[] depth = new int[n];
        depth[0] = 0;

        int[] q = new int[n];
        q[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = q[p];
            for (int nex : g[cur]) {
                if (par[cur] != nex) {
                    q[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][]{par, q, depth};
    }

    static int[][] packU(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
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
