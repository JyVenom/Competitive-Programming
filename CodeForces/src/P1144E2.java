import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P1144E2 {
    private static final byte[] inbuf = new byte[1024];
    public static int lenbuf = 0, ptrbuf = 0;
    static InputStream is = System.in;

    public static void main(String[] args) {
        PrintWriter pw = new PrintWriter(System.out);

        int n = ni();
        char[] s = ns(n);
        char[] t = ns(n);
        //char[] a=new char[k];
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s[i] - 'a' + t[i] - 'a';
        }
        //a[n-1]/=2;
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                a[i] /= 2;
            } else {
                a[i] /= 2;
                a[i + 1] += 26;
            }
        }
        for (int i = n - 1; i > 0; i--) {
            a[i - 1] += a[i] / 26;
            a[i] %= 26;
        }
        for (int i = 0; i < n; i++) {
            pw.print((char) (a[i] + 'a'));
        }

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

    private static boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private static int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    private static char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private static int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
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
