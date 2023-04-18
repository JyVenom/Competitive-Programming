import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class ReachableNodes2 {
    private static ArrayList<HashSet<Integer>> num;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        int m = ir.readInt();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>(n);
        HashSet<Integer> sources = new HashSet<>(n);
        num = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
            sources.add(i);
            num.add(new HashSet<>());
        }
        for (int i = 0; i < m; i++) {
            int a = ir.readInt() - 1;
            int b = ir.readInt() - 1;

            edges.get(a).add(b);
            sources.remove(b);
        }
        ir.close();

        for (int source : sources) {
            dfs(edges, source);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(num.get(i).size()).append(" ");
        }

        pw.println(sb);
        pw.close();
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, int at) {
        for (int next : edges.get(at)) {
            if (num.get(next).size() == 0) {
                dfs(edges, next);
            }

            num.get(at).addAll(num.get(next));
        }

        num.get(at).add(at);
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
