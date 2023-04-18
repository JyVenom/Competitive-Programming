import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1156C4 {

    public static void main(String[] args) throws Exception {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt(), K = ir.nextInt();
        int[] xs = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = ir.nextInt();
        }

        radixSort(xs, n);
        int low = 0, high = n / 2 + 1;
        inner:
        while (high - low > 1) {
            int h = high + low >> 1;
            for (int i = 0; i < h; i++) {
                if (xs[n - 1 - h + 1 + i] - xs[i] < K) {
                    high = h;
                    continue inner;
                }
            }
            low = h;
        }

        pw.println(low);
        pw.close();
    }

    private static void radixSort(int[] f, int n) {
        int[] to = new int[n];

        int[] b = new int[65537];
        for (int i = 0; i < n; i++) b[1 + (f[i] & 0xffff)]++;
        for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
        for (int i = 0; i < n; i++) to[b[f[i] & 0xffff]++] = f[i];
        int[] d = f;
        f = to;
        to = d;

        b = new int[65537];
        for (int i = 0; i < n; i++) b[1 + (f[i] >>> 16)]++;
        for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
        for (int i = 0; i < n; i++) to[b[f[i] >>> 16]++] = f[i];
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
