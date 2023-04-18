import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class P455B5 {
    static int n, k;
    static Boolean[] win, lose;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        n = ir.nextInt();
        k = ir.nextInt();
        Trie t = new Trie();
        for (int i = 0; i < n; i++) t.insert(ir.nextLine());

        win = new Boolean[n];
        lose = new Boolean[n];
        boolean forceWin = t.dpw(t.root);
        boolean forceLose = t.dpl(t.root);

        if (forceWin && forceLose) {
            pw.println("First");
        } else {
            if (forceWin) {
                pw.println(k % 2 == 1 ? "First" : "Second");
            } else {
                pw.println("Second");
            }
        }
        pw.close();
    }

    public static class Trie {
        private final char rootCharacter = '\0';
        private int cnt = 0;
        private final Node root = new Node(cnt++, rootCharacter);

        public void insert(String key) {
            Node node = root;

            for (int i = 0; i < key.length(); ++i) {
                char ch = key.charAt(i);
                Node nextNode = node.children.get(ch);

                if (nextNode == null) {
                    nextNode = new Node(cnt++, ch);
                    node.addChild(nextNode);
                }

                node = nextNode;
            }
        }

        boolean dpw(Node node) {
            if (node.num == cnt) return false;
            if (win[node.num] != null) return win[node.num];
            boolean ans = false;
            for (char key : node.children.keySet()) {
                Node child = node.children.get(key);
                ans = !dpw(child);
                if (ans) break;
            }
            return win[node.num] = ans;
        }

        boolean dpl(Node node) {
            if (node.num == cnt) return true;
            if (lose[node.num] != null) return lose[node.num];
            boolean allBad = true;
            boolean ans = false;
            for (char key : node.children.keySet()) {
                Node child = node.children.get(key);
                allBad = false;
                ans = !dpl(child);
                if (ans) break;
            }
            if (allBad) ans = true;
            return lose[node.num] = ans;
        }

        private static class Node {
            int num;
            char ch;
            HashMap<Character, Node> children = new HashMap<>();

            public Node(int num, char ch) {
                this.num = num;
                this.ch = ch;
            }

            public void addChild(Node node) {
                children.put(node.ch, node);
            }
        }
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
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

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = dis.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}