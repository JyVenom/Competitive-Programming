import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class P1156C3 {
    private static final byte[] inbuf = new byte[1024];
    public static int lenbuf = 0, ptrbuf = 0;

    private static int[] radixSort(int[] f) {
        return radixSort(f, f.length);
    }

    private static int[] radixSort(int[] f, int n) {
        int[] to = new int[n];
        {
            int[] b = new int[65537];
            for (int i = 0; i < n; i++) b[1 + (f[i] & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < n; i++) to[b[f[i] & 0xffff]++] = f[i];
            int[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < n; i++) b[1 + (f[i] >>> 16)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < n; i++) to[b[f[i] >>> 16]++] = f[i];
            f = to;
        }
        return f;
    }

    public static void main(String[] args) throws Exception {
        PrintWriter pw = new PrintWriter(System.out);

        int n = ni(), K = ni();
        int[] xs = radixSort(na(n));

        int low = 0, high = n / 2 + 1;
        inner:
        while (high - low > 1) {
            int h = high + low >> 1;
            for (int i = 0; i < h; i++) {
                if (xs[n - 1 - h + 1 + i] - xs[i] < K) {
                    high = h;
                    continue inner;
                }
            }
            low = h;
        }
        pw.println(low);
        pw.close();
    }

    private static int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = System.in.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private static int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private static int ni() {
        int num = 0, b = readByte();
        boolean minus = false;
        while (b != -1 && !((b >= '0' && b <= '9') || b == '-')) b = readByte();
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
