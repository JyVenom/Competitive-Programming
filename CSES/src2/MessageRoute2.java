import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class MessageRoute2 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.readInt();
        int m = ir.readInt();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            edges.add(new ArrayList<>());
        while (m-- > 0) {
            int a = ir.readInt() - 1;
            int b = ir.readInt() - 1;

            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        int[] pp = new int[n];
        Arrays.fill(pp, -1);
        int[] qq = new int[n];
        int head = 0, cnt = 0;
        pp[0] = 0;
        qq[head + cnt++] = 0;
        while (cnt > 0) {
            int i = qq[head++];
            cnt--;
            if (i == n - 1) {
                cnt = 0;
                while (i != 0) {
                    qq[cnt++] = i;
                    i = pp[i];
                }
                qq[cnt++] = 0;
                StringBuilder sb = new StringBuilder();
                sb.append(cnt).append("\n");
                while (cnt-- > 0)
                    sb.append(qq[cnt] + 1).append(" ");
                pw.print(sb);
                pw.close();
                return;
            }
            ArrayList<Integer> adj = edges.get(i);
            for (int j : adj)
                if (pp[j] == -1) {
                    pp[j] = i;
                    qq[head + cnt++] = j;
                }
        }
        pw.print("IMPOSSIBLE");
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
