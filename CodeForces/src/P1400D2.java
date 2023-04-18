import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1400D2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        for (int i = 0; i < t; i++) {
            int n = ir.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = ir.nextInt() - 1;
            }

            long ans = 0L;
            int[][] count = new int[n][n];
            for (int j = n - 1; j >= 0; j--) {
                int k = j + 1;
                for (int l = k + 1; l < n; l++) {
                    count[a[k]][a[l]]++;
                }
                for (int l = 0; l < j; l++) {
                    ans += count[a[l]][a[j]];
                }
            }

            pw.println(ans);
        }

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
