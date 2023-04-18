import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class P314C {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = ir.nextInt();
        }

        ArrayList<ArrayList<Integer>> subsequences = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean notAdded = true;
            for (ArrayList<Integer> subsequence : subsequences) {
                if (vals[i] >= vals[subsequence.get(subsequence.size() - 1)]) {
                    notAdded = false;
                    subsequence.add(i);
                }
            }
            if (notAdded) {
                int sz = subsequences.size();
                subsequences.add(new ArrayList<>());
                subsequences.get(sz).add(i);
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                set.add(vals[i]);
            }
            ArrayList<Integer> all = new ArrayList<>(set);
            Collections.sort(all);
            for (int i = 0; i < all.size(); i++) {
                map.put(all.get(i), i + 1);
            }
        }
        FenwickTree ft = new FenwickTree(map.size());
        int[] less = new int[n];
        for (int i = 0; i < n; i++) {
            int loc = map.get(vals[i]);
            less[i] = (int) ft.prefixSum(loc);
            ft.add(loc, 1);
        }
        HashSet<ArrayList<Integer>> all = new HashSet<>();
        for (int i = 0; i < subsequences.size(); i++) {
            addAll(all, subsequences.get(i), new ArrayList<>(), vals, 0);
        }


        pw.close();
    }

    private static void addAll(HashSet<ArrayList<Integer>> all, ArrayList<Integer> subsequence, ArrayList<Integer> cur, int[] vals, int at) {
        if (at == subsequence.size()) {
            all.add(cur);
            return;
        }

        addAll(all, subsequence, cur, vals, at + 1);
        ArrayList<Integer> copy = new ArrayList<>(cur);
        copy.add(subsequence.get(vals[at]));
        addAll(all, subsequence, copy, vals, at + 1);
    }

    public static class FenwickTree {

        // The size of the array holding the Fenwick tree values
        final int N;

        // This array contains the Fenwick tree ranges
        private final long[] tree;

        // Create an empty Fenwick Tree with 'sz' parameter zero based.
        public FenwickTree(int sz) {
            tree = new long[(N = sz + 1)];
        }

        // Returns the value of the least significant bit (LSB)
        // lsb(108) = lsb(0b1101100) =     0b100 = 4
        // lsb(104) = lsb(0b1101000) =    0b1000 = 8
        // lsb(96)  = lsb(0b1100000) =  0b100000 = 32
        // lsb(64)  = lsb(0b1000000) = 0b1000000 = 64
        private static int lsb(int i) {

            // Isolates the lowest one bit value
            return i & -i;

            // An alternative method is to use the Java's built in method
            // return Integer.lowestOneBit(i);

        }

        // Computes the prefix sum from [1, i], O(log(n))
        private long prefixSum(int i) {
            long sum = 0L;
            while (i != 0) {
                sum += tree[i];
                i &= ~lsb(i); // Equivalently, i -= lsb(i);
            }
            return sum;
        }

        // Returns the sum of the interval [left, right], O(log(n))
        public long sum(int left, int right) {
            if (right < left) throw new IllegalArgumentException("Make sure right >= left");
            return prefixSum(right) - prefixSum(left - 1);
        }

        // Get the value at index i
        public long get(int i) {
            return sum(i, i);
        }

        // Add 'v' to index 'i', O(log(n))
        public void add(int i, long v) {
            while (i < N) {
                tree[i] += v;
                i += lsb(i);
            }
        }

        // Set index i to be equal to v, O(log(n))
        public void set(int i, long v) {
            add(i, v - sum(i, i));
        }

        @Override
        public String toString() {
            return java.util.Arrays.toString(tree);
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

        public InputReader2(String file_name) throws IOException {
            dis = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private String nextLine() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == '\f' || c == -1;
        }

//        private String nextLine() throws IOException {
//            byte[] buf = new byte[BUFFER_SIZE]; // line length
//            int cnt = 0, c;
//            while ((c = read()) != -1) {
//                if (c == '\n')
//                    break;
//                buf[cnt++] = (byte) c;
//            }
//            return new String(buf, 0, cnt);
//        }

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

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
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

        private void close() throws IOException {
            dis.close();
        }
    }
}
