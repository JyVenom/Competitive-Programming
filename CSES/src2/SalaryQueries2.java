import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SalaryQueries2 {
    private static final int N = 200000;
    private static final int Q = N;

    private static final int[] pp = new int[N + Q * 2];
    private static final int[] ii = new int[N + Q * 2];
    private static final int[] cc = new int[N];
    private static final int[] aa = new int[N];
    private static final int[] bb = new int[N];

    private static final int[] tt = new int[N + Q * 2];

    private static void update(int i, int n, int x) {
        while (i < n) {
            tt[i] += x;
            i |= i + 1;
        }
    }

    private static int query(int i) {
        int x = 0;
        while (i >= 0) {
            x += tt[i];
            i &= i + 1;
            i--;
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int q = ir.nextInt();
        for (int i = 0; i < n; i++) {
            pp[i] = ir.nextInt();
        }

        int k = n;
        for (int i = 0; i < q; i++) {
            int c = ir.nextChar();
            int a = ir.nextInt();
            int b = ir.nextInt();
            cc[i] = c;
            if (cc[i] == '?') {
                pp[aa[i] = k++] = a - 1;
                pp[bb[i] = k++] = b;
            } else {
                aa[i] = a - 1;
                pp[bb[i] = k++] = b;
            }
        }
        for (int h = 0; h < k; h++)
            ii[h] = h;
//        Arrays.sort(ii, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return 0;
//            }
//        });
        int p = 0;
        for (int h = 0; h < k; h++)
            pp[ii[h]] = h + 1 == k || pp[ii[h]] != pp[ii[h + 1]] ? p++ : p;

        for (int i = 0; i < n; i++)
            update(pp[i], p, 1);
        for (int i = 0; i < q; i++)
            if (cc[i] == '?')
                pw.println(query(pp[bb[i]]) - query(pp[aa[i]]));
            else {
                update(pp[aa[i]], p, -1);
                update(pp[bb[i]], p, 1);
                pp[aa[i]] = pp[bb[i]];
            }

        pw.close();
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader() throws FileNotFoundException {
            this.stream = new FileInputStream("test.txt");
        }

        private int read() throws IOException {
            if (numChars == -1) {
                throw new IOException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new IOException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        private int nextChar() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            return c;
        }

        private int nextInt() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new IOException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == -1;
        }
    }
}
