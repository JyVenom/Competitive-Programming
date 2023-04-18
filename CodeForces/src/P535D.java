import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;

public class P535D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int[] p = ir.nextIntArr();

        int[] s = new int[n];
        Arrays.fill(s, -1);
        for (int i = 0; i < m; i++) {
            int loc = ir.nextInt();
            for (int j = 0, k = loc; j < p.length; j++, k++) {
                if (k == n || (s[k] != -1 && s[k] != p[j])) {
                    pw.println(0);
                    pw.close();
                    return;
                }
                s[k] = p[j];
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == -1) {
                count++;
            }
        }

        pw.println(binPow(count));
        pw.close();
    }

    private static long binPow(long b) {
        long a = 26;
        long res = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                res = res * a % 1000000007;
            a = a * a % 1000000007;
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

        private int[] nextIntArr() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            do {
                dq.addLast(c);
                c = read();
            } while (!isSpaceChar(c));
            int[] res = new int[dq.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = dq.removeFirst() - 'a';
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
    }
}
