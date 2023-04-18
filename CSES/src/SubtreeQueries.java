import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SubtreeQueries {
    private static int[] start, end;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();

        int n = ir.nextInt();
        int q = ir.nextInt();
        int N = n + 1;
        int[] values = new int[N];
        for (int i = 1; i < N; i++) {
            values[i] = ir.nextInt();
        }
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int a = ir.nextInt();
            int b = ir.nextInt();
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        start = new int[N];
        end = new int[N];
        dfs(edges, 1, 0);
        FenwickTree ft = new FenwickTree(N);
        for (int i = 1; i < N; i++) {
            ft.set(start[i], values[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int type = ir.nextInt();
            int s = ir.nextInt();
            if (type == 1) {
                ft.set(start[s], ir.nextInt());
            } else {
                sb.append(ft.prefixSum(end[s]) - ft.prefixSum(start[s] - 1)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, int at, int prev) {
        start[at] = ++count;
        for (int next : edges.get(at)) {
            if (next != prev) {
                dfs(edges, next, at);
            }
        }
        end[at] = count;
    }

    public static class FenwickTree {
        final int N;
        private final long[] tree;

        public FenwickTree(int sz) {
            tree = new long[(N = sz)];
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

        private long sum(int left, int right) {
            return prefixSum(right) - prefixSum(left - 1);
        }

        private void add(int i, long v) {
            while (i < N) {
                tree[i] += v;
                i += lsb(i);
            }
        }

        private void set(int i, long v) {
            add(i, v - sum(i, i));
        }
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader() {
            this.stream = System.in;
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
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new IOException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == -1;
        }
    }
}
