import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;

public class DistinctColors {
    private static int[] ans;
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
           arr[i] = ir.nextInt();
        }
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int a = ir.nextInt() - 1;
            int b = ir.nextInt()- 1;

            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        ans = new int[n];
        dfs(edges, arr, 0, -1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ans[i]).append(" ");
        }

        System.out.print(sb);
    }

    private static HashSet<Integer> dfs (ArrayList<ArrayList<Integer>> edges, int[] arr, int at, int prev) {
        HashSet<Integer> cur = new HashSet<>();
        for (int next : edges.get(at)) {
            if (next != prev) {
                cur.addAll(dfs(edges, arr, next, at));
            }
        }
        cur.add(arr[at]);
        ans[at] = cur.size();
        return cur;
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private void close() throws IOException {
            stream.close();
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
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private interface SpaceCharFilter {
            boolean isSpaceChar(int ch);
        }
    }
}
