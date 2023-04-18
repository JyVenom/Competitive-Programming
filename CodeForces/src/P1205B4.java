import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P1205B4 {
    private static final byte[] inbuf = new byte[1024];
    public static int lenbuf = 0;
    public static int ptrbuf = 0;
    static InputStream is;

    static int[] minCycleSizes(int n, int[] from, int[] to) {
        int[][] g = packU(n, from, to);
        int m = from.length;
        int[] q = new int[n];

        int[] ret = new int[m];
        Arrays.fill(ret, -1);
        for (int i = 0; i < m; i++) {
            int[] ds = new int[n];
            Arrays.fill(ds, n + 999);
            ds[from[i]] = 0;
            int p = 0;
            q[p++] = from[i];
            for (int j = 0; j < p; j++) {
                int cur = q[j];
                if (cur == to[i]) {
                    ret[i] = ds[cur] + 1;
                    break;
                }
                for (int e : g[cur]) {
                    if (cur == from[i] && e == to[i]) continue;
                    if (ds[e] > ds[cur] + 1) {
                        ds[e] = ds[cur] + 1;
                        q[p++] = e;
                    }
                }
            }
        }
        return ret;
    }

    public static int[][] packU(int n, int[] from, int[] to) {
        return packU(n, from, to, from.length);
    }

    public static int[][] packU(int n, int[] from, int[] to, int sup) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < sup; i++) p[from[i]]++;
        for (int i = 0; i < sup; i++) p[to[i]]++;
        for (int i = 0; i < n; i++) g[i] = new int[p[i]];
        for (int i = 0; i < sup; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    public static void main(String[] args) throws Exception {
        is = System.in;
        PrintWriter pw = new PrintWriter(System.out);

        int n = ni();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nl();
        }

        long[] fs = new long[3];
        for (int i = 0; i < n; i++) {
            long x = a[i];
            for (int j = 0; j < 3; j++) {
                long nx = x & fs[j];
                fs[j] |= x;
                x = nx;
            }
            if (fs[2] != 0) {
                pw.println(3);
                pw.close();
                return;
            }
        }

        {
            long[] b = new long[a.length];
            int p = 0;
            for (long l : a) {
                if (l != 0) b[p++] = l;
            }
            a = Arrays.copyOf(b, p);
        }
        int p = a.length;

        int[] from = new int[3600];
        int[] to = new int[3600];

        int q = 0;
        for (int i = 0; i < p; i++) {
            for (int j = i + 1; j < p; j++) {
                if ((a[i] & a[j]) != 0) {
                    from[q] = i;
                    to[q] = j;
                    q++;
                }
            }
        }

        from = Arrays.copyOf(from, q);
        to = Arrays.copyOf(to, q);

        int[] mcs = minCycleSizes(n, from, to);

        int min = 99999;
        for (int v : mcs) {
            if (v != -1) {
                min = Math.min(min, v);
            }
        }
        pw.println(min == 99999 ? -1 : min);
        pw.close();
    }

    private static int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private static int ni() {
        int num = 0, b = readByte();
        boolean minus = false;
        while (b != -1 && !((b >= '0' && b <= '9') || b == '-')) {
            b = readByte();
        }
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static long nl() {
        long num = 0;
        int b = readByte();
        boolean minus = false;
        while (b != -1 && !((b >= '0' && b <= '9') || b == '-')) {
            b = readByte();
        }
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
}