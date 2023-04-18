import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class P455B7 {
    static final int ALPHABET = 26;
    static ArrayList<Node> trie;
    static int n, k;

    public static void main(String[] args) throws IOException {
        InputReader2 in = new InputReader2();
        PrintWriter out = new PrintWriter(System.out);

        n = in.nextInt();
        k = in.nextInt();
        trie = new ArrayList<>();
        trie.add(new Node());
        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            addString(s);
        }

        boolean e = canEven(0);
        boolean o = canOdd(0);

        boolean first;
        if (e && o) {
            first = true;
        } else if (e) {
            first = false;
        } else if (o) {
            first = k % 2 == 1;
        } else {
            first = false;
        }

        out.println(first ? "First" : "Second");
        out.close();
    }

    static void addString(String s) {
        int v = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (trie.get(v).next[c] == -1) {
                trie.get(v).next[c] = trie.size();
                trie.add(new Node());
            }
            v = trie.get(v).next[c];
        }
        trie.get(v).isLeaf = true;
    }

    static boolean canOdd(int v) {
        boolean ret = false;
        boolean hasNext = false;
        for (int c = 0; c < ALPHABET; c++) {
            if (trie.get(v).next[c] != -1) {
                hasNext = true;
                ret = ret || !canOdd(trie.get(v).next[c]);
            }
        }
        return hasNext && ret;
    }

    static boolean canEven(int v) {
        boolean ret = false;
        boolean hasNext = false;
        for (int c = 0; c < ALPHABET; c++) {
            if (trie.get(v).next[c] != -1) {
                hasNext = true;
                ret = ret || !canEven(trie.get(v).next[c]);
            }
        }
        return !hasNext || ret;
    }

    static class Node {
        int[] next;
        boolean isLeaf;

        Node() {
            next = new int[ALPHABET];
            Arrays.fill(next, -1);
            isLeaf = false;
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