import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1208D8 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        long[] s = new long[n];
        for (int i = 0; i < n; i++) {
            s[i] = ir.nextLong();
        }

        BIT tree = new BIT(n);
        for (int i = 1; i <= n; i++) {
            tree.add(i, i);
        }
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            long cur = s[i];
            if (cur == 0) {
                ans[i] = binSearch(tree, 0, n);
            } else {
                ans[i] = binSearch(tree, cur, n);
            }
            tree.add(ans[i], -(ans[i]));
        }

        for (int an : ans) {
            pw.print(an + " ");
        }
        pw.close();
    }

    private static int binSearch(BIT tree, long key, int n) {
        int low = 1, high = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            long amt = tree.prefixSum(mid);
            if (amt <= key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static class BIT {
        final int N;
        private final long[] tree;

        public BIT(int sz) {
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

        public void add(int i, long v) {
            while (i < N) {
                tree[i] += v;
                i += lsb(i);
            }
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
