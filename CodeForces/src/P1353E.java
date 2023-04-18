import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1353E {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            int k = ir.nextInt();
            boolean[] s = ir.nextBoolArr(n);
            int[] count = new int[k];
            int total = 0;
            for (int i = 0; i < n; i++) {
                if (s[i]) {
                    total++;
                    count[(i % k)]++;
                }
            }
            if (n == 1) {
                pw.println(0);
                continue;
            }
            int N = n - 1;
            int min = 1000000;
            for (int i = 0; i < k; i++) {
                int tmp = total - (2 * count[i]) + (((N - i) / k) + 1);
                if (tmp < min) {
                    min = tmp;
                }
            }
            pw.println(min);
        }
        pw.close();
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

        private boolean[] nextBoolArr(int n) throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            boolean[] res = new boolean[n];
            int tmp = -1;
            while (++tmp < n) {
                res[tmp] = (c == '1');
                c = read();
            }
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
