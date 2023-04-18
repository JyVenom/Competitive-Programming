import java.io.IOException;
import java.io.InputStream;

public class RemovalGame {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();

        int n = ir.nextInt();
        int[] arr = new int[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextInt();
            sum += arr[i];
        }

        if (n == 1) {
            System.out.print(arr[0]);
        } else {
            long[][] dp = new long[n][n];
            int N = n - 1;
            dp[N][N] = arr[N];
            for (int i = n - 2; i >= 0; i--) {
                dp[i][i] = arr[i];
                for (int j = i + 1; j < n; j++) {
                    dp[i][j] = Math.max(arr[j] - dp[i][j - 1], arr[i] - dp[i + 1][j]);
                }
            }
            System.out.print(((sum - dp[0][N]) / 2) + dp[0][N]);
        }
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader() {
            this.stream = System.in;
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
                    throw new IOException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == -1;
        }
    }
}
