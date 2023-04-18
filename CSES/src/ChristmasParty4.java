import java.io.IOException;

public class ChristmasParty4 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();

        int n = ir.nextInt();

        if (n == 1)
            System.out.print(0);
        else {
            long a = 0, b = 1, k = 1;
            for (int i = 2; i < n; i++) {
                k = ((a + b) * i % 1000000007);
                a = b;
                b = k;
            }
            System.out.print(k);
        }
    }

    private static class InputReader {
        private final byte[] buf = new byte[8];
        private int curChar;

        public InputReader() throws IOException {
            curChar = 0;
            System.in.read(buf);
        }

        private int nextInt() {
            int c = buf[curChar++];
            int res = 0;
            do {
                res *= 10;
                res += c - '0';
                c = buf[curChar++];
            } while (c != '\n');
            return res;
        }
    }
}
