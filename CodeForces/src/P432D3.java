import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class P432D3 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        char[] s = ir.nextLine().toCharArray();

        int n = s.length;
        int[] pi = new int[n];
        for (int i = 1; i < n; ++i) {
            int j = pi[i - 1];
            while (j > 0 && s[i] != s[j]) {
                j = pi[j - 1];
            }

            if (s[i] == s[j]) ++j;
            pi[i] = j;
        }
        int[] ans = new int[n + 1];
        for (int i = n - 1; i >= 1; --i) ans[pi[i]]++;
        for (int i = n - 1; i >= 1; --i) ans[pi[i - 1]] += ans[i];
        for (int i = n; i >= 1; --i) ans[i]++;
        int j = pi[n - 1];
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        while (j > 0) {
            stack.push(new int[]{j, ans[j]});
            j = pi[j - 1];
        }

        pw.println(stack.size() + 1);
        while (!stack.isEmpty()) {
            pw.println(stack.peek()[0] + " " + stack.pop()[1]);
        }
        pw.println(n + " 1");
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
