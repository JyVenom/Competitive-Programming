import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class BinomialCoefficients2 {
    static final int MD = 1000000007, A = 1000000;
    static int[] ff, gg;

    static long power(long a, int k) {
        if (k == 0)
            return 1;
        long p = power(a, k / 2);
        p = p * p % MD;
        if (k % 2 == 1)
            p = p * a % MD;
        return p;
    }

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();

        ff = new int[A + 1];
        gg = new int[A + 1];
        long f = 1;
        for (int a = 0; a <= A; a++) {
            ff[a] = (int) f;
            gg[a] = (int) power(f, MD - 2);
            f = f * (a + 1) % MD;
        }
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            int a = ir.readInt();
            int b = ir.readInt();
            sb.append((long) ff[a] * gg[b] % MD * gg[a - b] % MD).append("\n");
        }
        ir.close();

        pw.print(sb);
        pw.close();
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public void close() throws IOException {
            stream.close();
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
