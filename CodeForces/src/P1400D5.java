import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1400D5 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        StringBuilder sb = new StringBuilder(3 * t);
        for (int i = 0; i < t; i++) {
            int n = ir.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = ir.nextInt() - 1;
            }

            int n1 = n + 1;
            int[] cntLeft = new int[n1];
            int[] cntRight = new int[n1];
            int N = n - 1;
            long ans = 0L;
            for (int k = N; k >= 0; k--) {
                cntRight[a[k]]++;
            }
            for (int j = 0; j < n; j++) {
                cntRight[a[j]]--;
                for (int k = N; k > j; k--) {
                    ans += (long) cntLeft[a[k]] * cntRight[a[j]];
                }
                cntLeft[a[j]]++;
            }

            sb.append(ans).append('\n');
        }

        pw.print(sb);
        pw.close();
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 24;
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
