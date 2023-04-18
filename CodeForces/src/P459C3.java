import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P459C3 {
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
            int min = (int) Math.floor(Math.log(init) / Math.log(2)) + 1;
            if (min > d) {
                pw.println(-1);
            } else {
                int dif = d - min;
                StringBuilder sb = new StringBuilder(n * n);
                for (int i = 1; i <= n; i++) {
                    sb.append(i).append(" ");
                }
                for (int i = 0; i < dif; i++) {
                    pw.println(sb);
                }

                int[][] ans = new int[min][n];
                int rem = init, prev = 0;
                while (rem > 0) {
                    int sz = (int) Math.floor(Math.log(rem) / Math.log(2));
                    int amt = (int) binPow(sz);
                    findAns(ans, prev, sz, k);
                    prev += sz;
                    rem -= binPow(sz);
                }
                for (int i = 0; i < min; i++) {
                    for (int j = 0; j < n; j++) {
                        pw.print(ans[i][j] + " ");
                    }
                }
            }
        }

        pw.close();
    }

    private static void findAns(int[][] ans, int start, int sz, int k) {
        int add = sz, half = sz / 2, cnt = 0;
        while (half > 0) {
            for (int i = half; i < sz; i += add) {
                int end = (i + 1) * k;
                for (int j = i * k, l = j + start; j < end; j++, l++) {
                    ans[cnt][l]++;
                    ans[cnt][l] %= k;
                }
            }
            add = half;
            half /= 2;
        }
    }

    private static long binPow(long b) {
        long res = 1, a = 2;
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
