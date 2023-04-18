import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

public class ReachableNodes7 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(new FileInputStream("test.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        int m = ir.readInt();
        ArrayList<node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new node());
        }
        for (int i = 0; i < m; i++) {
            int a = ir.readInt() - 1;
            int b = ir.readInt() - 1;

            add(nodes.get(a), nodes.get(b));
        }
        ir.close();

        LinkedList<node> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (nodes.get(i).children.size() == 0) {
                queue.add(nodes.get(i));
            }
        }
        while (!queue.isEmpty()) {
            node cur = queue.pollFirst();

            cur.ans++;

            LinkedList<node> temp = new LinkedList<>();
            if (cur.parents.size() > 1) {
                for (node parent : cur.parents) {
                    parent.repeats.add(cur);
                    parent.repeats.removeAll(cur.repeats);
                    parent.children.remove(cur);
                    if (parent.children.size() == 0) {
                        temp.add(parent);
                    }
                }
            } else if (cur.parents.size() == 1) {
                for (node parent : cur.parents) {
                    parent.ans += cur.ans;
                    parent.repeats.addAll(cur.repeats);
                    parent.children.remove(cur);
                    if (parent.children.size() == 0) {
                        temp.add(parent);
                    }
                }
            }
            temp.sort(Comparator.comparingInt(o -> o.parents.size()));
            queue.addAll(temp);

            for (node repeat : cur.repeats) {
                cur.ans += repeat.ans;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(nodes.get(i).ans).append(" ");
        }

        pw.println(sb);
        pw.close();
    }

    private static void add(node n1, node n2) {
        n1.children.add(n2);
        n2.parents.add(n1);
    }

    private static class node {
        HashSet<node> parents = new HashSet<>();
        HashSet<node> children = new HashSet<>();
        HashSet<node> repeats = new HashSet<>();
        long ans = 0;
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
