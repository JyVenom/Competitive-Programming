import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class DistinctColors4 {
    private static final ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    private static final ArrayList<HashSet<Integer>> col = new ArrayList<>();
    private static long[] ans;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        for (int i = 0; i < n; i++) {
            col.add(new HashSet<>());
        }
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            col.get(i).add(ir.nextInt());
        }
        for (int i = 1; i < n; i++) {
            int a = ir.nextInt() - 1;
            int b = ir.nextInt() - 1;

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        ans = new long[n];
        dfs(0, -1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ans[i]).append(" ");
        }

        System.out.print(sb);
    }

    private static void dfs(int at, int prev) {
        for (int next : adj.get(at)) {
            if (next != prev) {
                dfs(next, at);
                if (col.get(at).size() < col.get(next).size()) {
                    Collections.swap(col, at, next);
                }
                col.get(at).addAll(col.get(next));
                col.get(next).clear();
            }
        }
        ans[at] = col.get(at).size();
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

        private double nextDouble() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, nextInt());
                }
                if (c < '0' || c > '9') {
                    throw new IOException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return res * Math.pow(10, nextInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new IOException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
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
