import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DistinctColors5 {
    private static final ArrayDeque<Node> stack = new ArrayDeque<>();
    static Random random;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        random = new Random();

        int n = ir.nextInt();
        Node[] nodes = new Node[n];
        int[] ans = new int[n], en = new int[n], val = new int[n], id = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            val[i] = ir.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = ir.nextInt() - 1, v = ir.nextInt() - 1;
            nodes[u].adj.add(nodes[v]);
            nodes[v].adj.add(nodes[u]);
        }

        DFS(nodes[0]);
        Fenwick fenwick = new Fenwick(n);
        int last = 0;
        for (Node node : nodes) {
            en[node.start] = node.end;
            id[node.start] = last;
            last++;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            int v = val[id[i]];
            int next = map.getOrDefault(v, -1);
            if (next != -1) {
                fenwick.add(next);
            }
            map.put(v, i);

            ans[id[i]] = fenwick.prefix(en[i]) - fenwick.prefix(i - 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int x : ans) {
            sb.append(x).append(' ');
        }
        System.out.print(sb);
    }

    static void DFS(Node node) {
        stack.add(node);
        int clock = -1;
        outer:
        while (!stack.isEmpty()) {
            node = stack.peekLast();
            if (node.ptr == -1) {
                node.visited = true;
                node.start = ++clock;
            }

            for (node.ptr++; node.ptr < node.adj.size(); node.ptr++) {
                Node neigh = node.adj.get(node.ptr);
                if (neigh.visited) continue;
                stack.add(neigh);
                continue outer;
            }

            node.end = clock;
            stack.pollLast();
        }
    }

    private static class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n];
            for (int i = 0; i < n; i++) {
                bit[i]++;
                if (i + ((i + 1) & -(i + 1)) < n)
                    bit[i + ((i + 1) & -(i + 1))] += bit[i];
            }
        }

        void add(int i) {
            if (i < 0) return;
            while (i < bit.length) {
                bit[i] -= 1;
                i += Integer.lowestOneBit(i + 1);
            }
        }

        int prefix(int i) {
            if (i >= bit.length) return 0;
            int ret = 0;
            while (i >= 0) {
                ret += bit[i];
                i -= Integer.lowestOneBit(i + 1);
            }
            return ret;
        }
    }

    private static class Node {
        int start, end;
        ArrayList<Node> adj = new ArrayList<>();
        int ptr = -1;
        boolean visited = false;
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
