import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class P61E8 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] a = new int[n];
        HashSet<Integer> set = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            a[i] = ir.nextInt();
            set.add(a[i]);
        }

        ArrayList<Integer> all = new ArrayList<>(set);
        Collections.sort(all);
        HashMap<Integer, Integer> map = new HashMap<>(all.size());
        for (int i = 0; i < all.size(); i++) {
            map.put(all.get(i), i);
        }
        int[] left = new int[n];
        int[] right = new int[n];
        FenwickTreeRangeQueryPointUpdate ft = new FenwickTreeRangeQueryPointUpdate(all.size());
        for (int i = n - 1; i >= 0; i--) {
            int loc = map.get(a[i]);
            if (loc > 0) {
                right[i] = ft.prefixSum(loc);
            }
            ft.add(loc + 1, 1);
        }
        ft.tree = new int[ft.N];
        for (int i = 0, I = n - 1; i < all.size(); i++, I--) {
            map.replace(all.get(i), I);
        }
        for (int i = 0; i < n; i++) {
            int loc = map.get(a[i]);
            if (loc > 0) {
                left[i] = ft.prefixSum(loc);
            }
            ft.add(loc + 1, 1);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) left[i] * right[i];
        }

        pw.println(ans);
        pw.close();
    }

    public static class FenwickTreeRangeQueryPointUpdate {
        final int N;
        private int[] tree;

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
