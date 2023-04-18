import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

public class PRS8_10 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        Trie t = new Trie();
        makeTrie(ir, t, n);

        int q = ir.nextInt();
        while (q-- > 0) {
            pw.println(t.findMax(ir.nextLine()));
        }

        pw.close();
    }

    private static void makeTrie(InputReader ir, Trie t, int n) throws IOException {
        int[] rs = new int[n];
        for (int i = 0; i < n; i++) {
            rs[i] = ir.nextInt();
        }
        for (int i = 0; i < n; i++) {
            t.insert(ir.nextLine(), rs[i], i + 1);
        }
    }

    private static class Trie {
        private final char rootCharacter = '\0';
        private final Node root = new Node(rootCharacter, -1, -1);

        public void insert(String key, int val, int num) {
            Node node = root;

            for (int i = 0; i < key.length(); ++i) {
                char ch = key.charAt(i);
                Node nextNode = node.children.get(ch);

                if (nextNode == null) {
                    nextNode = new Node(ch, val, num);
                    node.addChild(nextNode, ch);
                }

                node = nextNode;
                node.updateMax(val, num);
                node.count += 1;
            }

            if (node != root) node.isWordEnding = true;
        }

        public int findMax(String key) {
            Node node = root;

            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                if (node == null) return 1;
                node = node.children.get(ch);
            }

            if (node != null) return node.maxInd;
            return 1;
        }

        private static class Node {
            char ch;
            int val, num, max = 0, maxInd = 0;
            int count = 0;
            boolean isWordEnding = false;
            HashMap<Character, Node> children = new HashMap<>();

            public Node(char ch, int val, int num) {
                this.ch = ch;
                this.val = val;
                this.num = num;
            }

            public void updateMax(int val, int num) {
                if (val > max) {
                    max = val;
                    maxInd = num;
                }
            }

            public void addChild(Node node, char c) {
                children.put(c, node);
            }
        }
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 16];
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

        private String nextLine() throws IOException {
            int c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (c != '\n');
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == -1;
        }
    }
}
