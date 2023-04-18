import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P459C4 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        int d = ir.nextInt();

        if (k >= n) {
            StringBuilder sb = new StringBuilder(n * n);
            for (int i = 1; i <= n; i++) {
                sb.append(i).append(" ");
            }
            for (int i = 0; i < d; i++) {
                pw.println(sb);
            }
        } else if (k == 1) {
            pw.println(-1);
        } else {
            int init = (n + k - 1) / k;
            int min = (int) (Math.ceil(Math.log(init) / Math.log(2)) + 1);
            if (min > d) {
                pw.println(-1);
            } else {
                int dif = d - min;
                if (dif > 0) {
                    StringBuilder sb = new StringBuilder(n * n);
                    for (int i = 1; i <= n; i++) {
                        sb.append(i).append(" ");
                    }
                    for (int i = 0; i < dif; i++) {
                        pw.println(sb);
                    }
                }

                int[][] ans = new int[min][n];
                int rem = init, prev = 0, min1 = min - 1;
                while (rem > 0) {
                    int sz = (int) Math.ceil(Math.log(rem) / Math.log(2));
                    int amt = binPow(sz);
                    int start = prev * k;
                    findAns(ans, start, amt, k, n);
                    if (sz < min1) {
                        fillRotate(ans, start, ++sz, k);
                    }
                    int tmp = sz;
                    while (++tmp < min) {
                        fillStandard(ans, start, tmp, k);
                    }
                    prev += amt;
                    rem -= amt;
                }
                for (int i = 0; i < n; i++) {
                    ans[0][i] = i % k;
                }
                for (int i = 0; i < min; i++) {
                    for (int j = 0; j < n; j++) {
                        pw.print((ans[i][j] + 1) + " ");
                    }
                    pw.println();
                }
            }
        }

        pw.close();
    }

    private static void fillRotate(int[][] ans, int start, int day, int k) {
        int end = start + k;
        for (int i = start; i < end; i++) {
            ans[day][i] = (i + 1) % k;
        }
    }

    private static void fillStandard(int[][] ans, int start, int day, int k) {
        int end = start + k;
        for (int i = start; i < end; i++) {
            ans[day][i] = i % k;
        }
    }

    private static void findAns(int[][] ans, int start, int sz, int k, int n) {
        int add = sz, half = sz / 2, cnt = 1;
        while (half > 0) {
            for (int i = half; i < sz; i += add) {
                int end = (i + half) * k;
                if (n < end) {
                    end = n;
                }
                for (int j = i * k, l = j + start; j < end; j++, l++) {
                    ans[cnt][l]++;
                    ans[cnt][l] %= k;
                }
            }
            add = half;
            half /= 2;
        }
    }

    private static int binPow(int b) {
        int res = 1, a = 2;
        while (b > 0) {
            if ((b & 1) == 1)
                res *= a;
            a *= a;
            b >>= 1;
        }
        return res;
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
