import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MeetInTheMiddle4 {
    private static long[] temp;

    static long[] build(int[] arr, int l, int r) {
        long[] res = new long[1 << r - l];
        for (int i = 1; l < r; l++, i <<= 1) {
            int cur = arr[l];
            for (int j = 0; j < i; j++) {
                temp[j] = res[j];
                temp[i + j] = res[j] + cur;
            }
            int p = 0, q = i, q_ = i + i, h = 0;
            while (p < i && q < q_) {
                if (temp[p] < temp[q]) {
                    res[h++] = temp[p++];
                } else {
                    res[h++] = temp[q++];
                }
            }
            while (p < i) {
                res[h++] = temp[p++];
            }
            while (q < q_) {
                res[h++] = temp[q++];
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = fr.nextInt();
        int x = fr.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fr.nextInt();
        }
        fr.close();

        int k0 = n / 2, k1 = n - k0, n0 = 1 << k0, n1 = 1 << k1;
        temp = new long[n1];
        long[] a0 = build(arr, 0, k0);
        long[] a1 = build(arr, k0, n);
        long ans = 0;
        for (int i = 0, p = n1 - 1, q = p; i < n0; i++) {
            long y = 0;
            while (q >= 0 && (y = a0[i] + a1[q]) > x) {
                q--;
            }
            if (q < 0) {
                break;
            }
            if (y == x) {
                if (p > q) {
                    p = q;
                }
                while (p > 0 && a1[p - 1] == a1[q]) {
                    p--;
                }
                ans += q - p + 1;
            }
        }

        pw.println(ans);
        pw.close();
    }

    private static class FastReader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
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

        private void close() throws IOException {
            dis.close();
        }
    }
}
