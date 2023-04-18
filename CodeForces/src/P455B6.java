import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class P455B6 {
    static final int N = (int) 1e5 + 20;

    static int[][] trie;
    static int node = 1;
    static int n, k;
    static Boolean[] win, lose;

    public static void main(String[] args) throws IOException {
        InputReader2 in = new InputReader2();
        PrintWriter out = new PrintWriter(System.out);
        n = in.nextInt();
        k = in.nextInt();
        trie = new int[26][N];
        for (int[] a : trie) Arrays.fill(a, -1);
        for (int i = 0; i < n; ++i) insert(in.nextLine());
        win = new Boolean[N];
        lose = new Boolean[N];
        boolean forceWin = dpw(0);
        boolean forceLose = dpl(0);
        if (forceWin && forceLose) {
            out.println("First");
        } else {
            if (forceWin) {
                out.println(k % 2 == 1 ? "First" : "Second");
            } else {
                out.println("Second");
            }
        }
        out.close();
    }

    static void insert(String s) {
        int cur = 0;
        for (char i : s.toCharArray()) {
            int c = i - 'a';
            if (trie[c][cur] == -1) trie[c][cur] = node++;
            cur = trie[c][cur];
        }
    }

    static boolean dpw(int node) {
        if (node == N) return false;
        if (win[node] != null) return win[node];
        boolean ans = false;
        for (char c = 0; c < 26; ++c) {
            if (trie[c][node] == -1) continue;
            ans = !dpw(trie[c][node]);
            if (ans) break;
        }
        return win[node] = ans;
    }

    static boolean dpl(int node) {
        if (node == N) return true;
        if (lose[node] != null) return lose[node];
        boolean allBad = true;
        boolean ans = false;
        for (char c = 0; c < 26; ++c) {
            if (trie[c][node] == -1) continue;
            allBad = false;
            ans = !dpl(trie[c][node]);
            if (ans) break;
        }
        if (allBad) ans = true;
        return lose[node] = ans;
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