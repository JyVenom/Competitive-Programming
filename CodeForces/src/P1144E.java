import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1144E {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int k = ir.nextInt();
        int[] s = ir.nextIntArr(k);
        int[] t = ir.nextIntArr(k);

        int K = k + 1;
        int[] sum = new int[K];
        for (int i = k - 1, I = k; i >= 0; i--, I--) {
            sum[I] += s[i] + t[i];
            if (sum[I] > 25) {
                sum[I] -= 26;
                sum[i]++;
            }
        }
        int[] avg = new int[K];
        int rem = sum[0];
        for (int i = 1; i <= k; i++) {
            rem *= 26;
            int tmp = rem + sum[i];
            avg[i] = tmp / 2;
            rem = tmp % 2;
        }

        StringBuilder sb = new StringBuilder(k);
        for (int i = 1; i <= k; i++) {
            sb.append((char) (avg[i] + 'a'));
        }

        pw.println(sb);
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

        private int[] nextIntArr(int k) throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = c - 'a';
                c = read();
            }
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
