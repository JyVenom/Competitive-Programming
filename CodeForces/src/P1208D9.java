import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1208D9 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = ir.nextLong();
        }

        Fenwick f = new Fenwick(n);
        for (int i = 1; i <= n; i++) {
            f.add(i, i);
        }
        int[] list = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int ind = f.indexWithGivenCumFreq(a[i]);
            f.add(ind, -ind);
            list[i] = ind;
        }

        for (int i = 0; i < n; i++) {
            pw.print(list[i] + " ");
        }
        pw.close();
    }

    static class Fenwick {
        public final long[] bit;
        public final int size;

        public Fenwick(int size) {
            this.size = size + 1;
            bit = new long[this.size];
        }

        public void add(int i, long delta) {
            for (++i; i < size; i += (i & -i)) {
                bit[i] += delta;
            }
        }

        public int indexWithGivenCumFreq(long v) {
            int i = 0, n = size;
            for (int b = Integer.highestOneBit(n); b != 0; b >>= 1) {
                if ((i | b) < n && bit[i | b] <= v) {
                    i |= b;
                    v -= bit[i];
                }
            }
            return i;
        }
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

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
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
