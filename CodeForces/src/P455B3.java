import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P455B3 {
    private static int[][] t;
    private static boolean[] win;
    private static boolean[] lose;
    private static int szT;
    private static int root;

    private static int newT() {
        return ++szT;
    }

    private static void addTrie(String s) {
        int v = root;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (t[v][c] == 0) t[v][c] = newT();
            v = t[v][c];
        }
    }

    private static void dfs(int v) {
        win[v] = false;
        lose[v] = false;
        boolean isLeaf = true;
        for (int i = 0; i < 26; i++) {
            if (t[v][i] != 0) {
                isLeaf = false;
                int to = t[v][i];
                dfs(to);
                win[v] |= !win[to];
                lose[v] |= !lose[to];
            }
        }
        if (isLeaf) {
            lose[v] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        root = newT();
        for (int i = 0; i < n; i++) {
            addTrie(ir.nextLine());
        }

        t = new int[n][26];
        win = new boolean[n];
        lose = new boolean[n];
        dfs(root);

        if (k == 1) {
            pw.println(win[root] ? "First" : "Second");
        } else if (!win[root]) {
            pw.println("Second");
        } else if (lose[root]) {
            pw.println("First");
        } else if (k % 2 == 1) {
            pw.println("First");
        } else {
            pw.println("Second");
        }
        pw.close();
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