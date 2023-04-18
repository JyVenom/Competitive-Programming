import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class DivisorAnalysis {
    public static void main(String[] args) {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        int[][] primes = new int[n][2];
        for (int i = 0; i < n; i++) {
            primes[i][0] = ir.readInt();
            primes[i][1] = ir.readInt();
        }

        StringBuilder sb = new StringBuilder();
        long num = 1;
        for (int i = 0; i < n; i++) {
            num *= (primes[i][1] + 1);
            num %= 1000000007;
        }
        sb.append(num).append(" ");
        long sum = 1;
        long modInv, temp;
        for (int i = 0; i < n; i++) {
            temp = binPow(primes[i][0], primes[i][1] + 1);
            if (temp == 0) {
                temp = 1000000006;
            } else {
                temp -= 1;
            }
            modInv = binPow(primes[i][0] - 1, 1000000005);
            temp *= modInv;
            temp %= 1000000007;
            sum *= temp;
            sum %= 1000000007;
        }
        sb.append(sum).append(" ");
        boolean square = true;
        for (int i = 0; i < n; i++) {

            if (primes[i][1] % 2 == 1) {
                square = false;
                break;
            }
        }
        long N = 1;
        if (square) {
            for (int i = 0; i < n; i++) {
                N *= binPow(primes[i][0], primes[i][1] / 2);
                N %= 1000000007;
            }
            long num2 = 1;
            for (int i = 0; i < n; i++) {
                num2 *= (primes[i][1] + 1);
                num2 %= 1000000006;
            }
            sb.append(binPow(N, num2));
        } else {
            for (int i = 0; i < n; i++) {
                N *= binPow(primes[i][0], primes[i][1]);
                N %= 1000000007;
            }
            long num2 = 1;
            int i;
            for (i = 0; i < n; i++) {
                num2 *= (primes[i][1] + 1);
                if (num2 % 2 == 0) {
                    num2 /= 2;
                    num2 %= 1000000006;
                    i++;
                    break;
                }
                num2 %= 1000000006;
            }
            for (; i < n; i++) {
                num2 *= (primes[i][1] + 1);
                num2 %= 1000000006;
            }
            sb.append(binPow(N, num2) % 1000000007);
        }

        pw.print(sb);
        pw.close();
    }

    private static long binPow(long a, long b) {
        assert (b >= 0);
        a %= 1000000007; //note: m*m must be less than 2^63 to avoid ll overflow
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1) //if n is odd
                res = res * a % 1000000007;
            a = a * a % 1000000007;
            b /= 2; //divide by two
        }
        return res;
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1 << 24];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() {
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

        private int readInt() {
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

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
