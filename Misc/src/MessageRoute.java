import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class MessageRoute {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        int m = ir.readInt();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = ir.readInt() - 1;
            int b = ir.readInt() - 1;

            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> ans = bfs(edges);
        if (ans.size() == 0) {
            sb.append("IMPOSSIBLE");
        } else {
            sb.append(ans.size()).append("\n");
            for (int i = ans.size() - 1; i >= 0; i--) {
                sb.append(ans.get(i)).append(" ");
            }
        }

        pw.print(sb);
        pw.close();
    }

    private static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> edges) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int[] prev = new int[edges.size()];
        boolean[] visited = new boolean[edges.size()];
        int N = edges.size() - 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = true;

            if (cur == N) {
                ArrayList<Integer> ans = new ArrayList<>();

                int temp = cur;
                while (!(temp == 0)) {
                    ans.add(temp + 1);
                    temp = prev[temp];
                }
                ans.add(1);

                return ans;
            }

            for (int next : edges.get(cur)) {
                if (!visited[next]) {
                    queue.offer(next);
                    prev[next] = cur;
                }
            }
        }
        return new ArrayList<>();
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        private int readInt() {
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
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
