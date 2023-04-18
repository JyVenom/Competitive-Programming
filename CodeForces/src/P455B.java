import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class P455B {
    private static int numOdd = 0;

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        ir.nextInt();
        Trie t = new Trie();
        for (int i = 0; i < n; i++) {
            t.insert(ir.nextLine());
        }

        if (numOdd > 0) {
            pw.println("First");
        } else {
            pw.println("Second");
        }

        pw.close();
    }

    public static class Trie {
        private final char rootCharacter = '\0';
        private final Node root = new Node(rootCharacter);

        public void insert(String key) {
            Node node = root;
            boolean tmp = !node.children.containsKey(key.charAt(0));

            for (int i = 0; i < key.length(); ++i) {
                char ch = key.charAt(i);
                Node nextNode = node.children.get(ch);

                if (nextNode == null) {
                    if (!tmp && i % 2 == 0) {
                        numOdd--;
                    }
                    tmp = true;
                    nextNode = new Node(ch);
                    node.addChild(nextNode);
                }

                node = nextNode;
            }
            if (node.children.size() == 0) {
                if (key.length() % 2 == 1) {
                    numOdd++;
                }
            }
        }

        private static class Node {
            char ch;
            HashMap<Character, Node> children = new HashMap<>();

            public Node(char ch) {
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
