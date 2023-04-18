import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class P61E9 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = ir.nextInt();

        {
            int[] tmp = a.clone();
            Arrays.sort(tmp);
            for (int i = 0; i < n; i++)
                a[i] = Arrays.binarySearch(tmp, a[i]) + 1;
        }
        FenwickTreeRangeQueryPointUpdate ft = new FenwickTreeRangeQueryPointUpdate(n);
        long result = 0;
        for (int i = n - 1; i >= 0; i--) {
            int smaller = ft.prefixSum(a[i]);
            int greater = i - (a[i] - 1 - smaller);
            result += (long) smaller * greater;
            ft.add(a[i]);
        }

        pw.println(result);
        pw.close();
    }

    public static class FenwickTreeRangeQueryPointUpdate {
        final int N;
        private final int[] tree;

        public FenwickTreeRangeQueryPointUpdate(int sz) {
            tree = new int[(N = sz + 1)];
        }

        private static int lsb(int i) {
            return i & -i;
        }

        public int prefixSum(int i) {
            int sum = 0;
            while (i != 0) {
                sum += tree[i];
                i &= ~lsb(i);
            }
            return sum;
        }

        public void add(int i) {
            while (i < N) {
                tree[i] += 1;
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
