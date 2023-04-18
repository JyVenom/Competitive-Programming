import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class P432D6 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        char[] s = ir.nextLine().toCharArray();

        int n = s.length;
        int i = 1;
        int len = 0;
        int[] pi = new int[n];
        int[] cnt = new int[n + 1];
        while (i < n) {
            if (s[i] == s[len]) {
                pi[i++] = ++len;
            } else if (len != 0) {
                len = pi[len - 1];
            } else {
                pi[i++] = len;
            }
        }
        for (int j = 1; j < n; ++j) {
            if (pi[j] > 0) {
                ++cnt[pi[j]];
            }
        }
        for (int j = n - 1; j > 1; --j) {
            cnt[pi[j - 1]] += cnt[j];
        }
        for (int j = 0; j <= n; ++j) {
            ++cnt[j];
        }
        ArrayDeque<int[]> ans = new ArrayDeque<>();
        int cur = n;
        while (cur > 0) {
            ans.push(new int[]{cur, cnt[cur]});
            cur = pi[cur - 1];
        }

        pw.println(ans.size());
        while (!ans.isEmpty()) {
            pw.println(ans.peek()[0] + " " + ans.pop()[1]);
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
            return c == '\n' || c == '\r';
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
