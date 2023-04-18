import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class AcowdemiaI {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int l = ir.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Math.min(n, ir.nextInt());
        }

        n++;
        FenwickTreeRangeQueryPointUpdate ft = new FenwickTreeRangeQueryPointUpdate(n + 1);
        {
            int tmp = n - 1;
            for (int i = 0; i < tmp; i++) {
                ft.add(n - arr[i], 1);
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            int loc = n - i;
            if (ft.prefixSum(loc) + Math.min(l, ft.get(loc + 1)) >= i) {
                max = i;
            }
        }

        pw.println(max);
        pw.close();
    }

    private static class FenwickTreeRangeQueryPointUpdate {
        final int N;
        private final long[] tree;

        public FenwickTreeRangeQueryPointUpdate(int sz) {
            tree = new long[(N = sz + 1)];
        }

        private static int lsb(int i) {
            return i & -i;
        }

        private long prefixSum(int i) {
            long sum = 0L;
            while (i != 0) {
                sum += tree[i];
                i &= ~lsb(i);
            }
            return sum;
        }

        public long sum(int left, int right) {
            if (right < left) throw new IllegalArgumentException("Make sure right >= left");
            return prefixSum(right) - prefixSum(left - 1);
        }

        public long get(int i) {
            return sum(i, i);
        }

        public void add(int i, long v) {
            while (i < N) {
                tree[i] += v;
                i += lsb(i);
            }
        }
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
            do
                ret = ret * 10 + c - '0';
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
