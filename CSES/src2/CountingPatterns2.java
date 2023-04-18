import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;

public class CountingPatterns2 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);

        byte[] s = ir.nextByteArr();
        int k = ir.nextInt();

        int n = s.length;
        StringBuilder sb = new StringBuilder(3 * k);
        for (int i = 0; i < k; i++) {
            byte[] pat = ir.nextByteArr();
            int m = pat.length;
            int m1 = m + 1;
            int nm = n + m1;
            byte[] all = Arrays.copyOf(pat, nm);
            System.arraycopy(s, 0, all, m1, n);
            int[] locs = new int[nm];
            locs[0] = -1;
            int cnt = 0;
            for (int j = 1; j < nm; j++) {
                int l = locs[j - 1] + 1;
                while (all[l] != all[j]) {
                    if (l == 0) {
                        l = -1;
                        break;
                    }
                    l = locs[l - 1] + 1;
                }
                locs[j] = l;
                if (l + 1 == m)
                    cnt++;
            }

            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private byte read() throws IOException {
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
            byte c = read();
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

        private byte[] nextByteArr() throws IOException {
            byte c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            ArrayDeque<Byte> q = new ArrayDeque<>();
            do {
                q.addLast(c);
                c = read();
            } while (!isSpaceChar(c));
            byte[] res = new byte[q.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = q.removeFirst();
            }
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == -1;
        }
    }
}
