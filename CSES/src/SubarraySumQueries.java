import java.io.IOException;
import java.io.InputStream;

public class SubarraySumQueries {
    private static node[] tree;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        int m = ir.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextLong();
        }

        int n2 = 3 * n;
        tree = new node[n2];
        for (int i = 0; i < n2; i++) {
            tree[i] = new node();
        }
        int N = n - 1;
        build(arr, 0, N, 0);
        StringBuilder sb = new StringBuilder(3 * m);
        for (int i = 0; i < m; i++) {
            int k = ir.nextInt() - 1;
            long x = ir.nextInt();

            arr[k] = x;
            update(0, 0, N, k, x);
            sb.append(Math.max(query(0, 0, N, 0, N).maxsum, 0)).append("\n");
        }

        System.out.print(sb);
    }

    private static void build(long[] arr, int low, int high, int index) {
        if (low == high) {
            tree[index].sum = arr[low];
            tree[index].prefixsum = arr[low];
            tree[index].suffixsum = arr[low];
            tree[index].maxsum = arr[low];
        } else {
            int mid = (low + high) / 2;

            build(arr, low, mid, 2 * index + 1);
            build(arr, mid + 1, high, 2 * index + 2);

            tree[index].sum = tree[2 * index + 1].sum + tree[2 * index + 2].sum;
            tree[index].prefixsum = Math.max(tree[2 * index + 1].prefixsum, tree[2 * index + 1].sum + tree[2 * index + 2].prefixsum);
            tree[index].suffixsum = Math.max(tree[2 * index + 2].suffixsum, tree[2 * index + 2].sum + tree[2 * index + 1].suffixsum);
            tree[index].maxsum = Math.max(tree[index].prefixsum, Math.max(tree[index].suffixsum, Math.max(tree[2 * index + 1].maxsum, Math.max(tree[2 * index + 2].maxsum, tree[2 * index + 1].suffixsum + tree[2 * index + 2].prefixsum))));
        }
    }

    private static void update(int index, int low, int high, int idx, long value) {
        if (low == high) {
            tree[index].sum = value;
            tree[index].prefixsum = value;
            tree[index].suffixsum = value;
            tree[index].maxsum = value;
        } else {
            int mid = (low + high) / 2;

            if (idx <= mid) {
                update(2 * index + 1, low, mid, idx, value);
            } else {
                update(2 * index + 2, mid + 1, high, idx, value);
            }

            tree[index].sum = tree[2 * index + 1].sum + tree[2 * index + 2].sum;
            tree[index].prefixsum = Math.max(tree[2 * index + 1].prefixsum, tree[2 * index + 1].sum + tree[2 * index + 2].prefixsum);
            tree[index].suffixsum = Math.max(tree[2 * index + 2].suffixsum, tree[2 * index + 2].sum + tree[2 * index + 1].suffixsum);
            tree[index].maxsum = Math.max(tree[index].prefixsum, Math.max(tree[index].suffixsum, Math.max(tree[2 * index + 1].maxsum, Math.max(tree[2 * index + 2].maxsum, tree[2 * index + 1].suffixsum + tree[2 * index + 2].prefixsum))));
        }
    }

    private static node query(int index, int low, int high, int l, int r) {
        node result = new node();
        result.sum = result.prefixsum = result.suffixsum = result.maxsum = Integer.MIN_VALUE;

        if (r < low || high < l) {
            return result;
        }

        if (l <= low && high <= r) {
            return tree[index];
        }

        int mid = (low + high) / 2;

        if (l > mid) {
            return query(2 * index + 2, mid + 1, high, l, r);
        }

        if (r <= mid) {
            return query(2 * index + 1, low, mid, l, r);
        }

        node left = query(2 * index + 1, low, mid, l, r);
        node right = query(2 * index + 2, mid + 1, high, l, r);

        result.sum = left.sum + right.sum;
        result.prefixsum = Math.max(left.prefixsum, left.sum + right.prefixsum);
        result.suffixsum = Math.max(right.suffixsum, right.sum + left.suffixsum);
        result.maxsum = Math.max(result.prefixsum, Math.max(result.suffixsum, Math.max(left.maxsum, Math.max(right.maxsum, left.suffixsum + right.prefixsum))));

        return result;
    }

    private static class node {
        long sum, prefixsum, suffixsum, maxsum;
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() throws IOException {
            if (numChars == -1) {
                throw new IOException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new IOException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        private int nextInt() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new IOException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private long nextLong() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new IOException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == -1;
        }
    }
}
