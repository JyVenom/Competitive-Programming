import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class ReachableNodes3 {
    private static int[] num;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        int m = ir.readInt();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        HashSet<Integer> sources = new HashSet<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
            sources.add(i);
        }
        for (int i = 0; i < m; i++) {
            int a = ir.readInt() - 1;
            int b = ir.readInt() - 1;

            edges.get(a).add(b);
            sources.remove(b);
        }
        ir.close();

        num = new int[n];
        for (int source : sources) {
            dfs(edges, new boolean[n], source, -1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(num[i]).append(" ");
        }

        pw.println(sb);
        pw.close();
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, boolean[] visited, int at, int prev) {
        visited[at] = true;

        for (int next : edges.get(at)) {
            if (visited[next]) {
                if (prev != -1) {
                    num[prev] -= num[next];
                } else {
                    num[at] -= num[next];
                }
            } else {
                dfs(edges, visited, next, at);
            }

            num[at] += num[next];
        }

        num[at]++;
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

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

        private int readInt() throws IOException {
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

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == -1;
        }
    }
}
