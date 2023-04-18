import java.io.*;
import java.util.ArrayDeque;

public class P535D5 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = ir.nextInt(), m = ir.nextInt();
        char[] s = ir.nextCharArr();

        int[] z = zAlgorithm(s);
        int pos = 0, cnt = 0;
        for (int i = 0; i < m; i++) {
            int loc = ir.nextInt() - 1;
            if (loc < pos) {
                int d = pos - loc;
                if (z[s.length - d] != d) {
                    pw.println(0);
                    pw.close();
                    return;
                }
            }
            if (pos < loc) {
                cnt += loc - pos;
            }
            pos = loc + s.length;
        }
        cnt += n - pos;

        pw.println(binPow(cnt));
        pw.close();
    }

    private static long binPow(long b) {
        long a = 26;
        long res = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                res = res * a % 1000000007;
            a = a * a % 1000000007;
            b >>= 1;
        }
        return res;
    }

    private static int[] zAlgorithm(char[] s) {
        int[] z = new int[s.length];
        int left = 0, right = 0;
        for (int i = 1; i < s.length; i++) {
            if (i > right) {
                int j = 0;
                while (i + j < s.length && s[i + j] == s[j]) {
                    j++;
                }
                z[i] = j;
                left = i;
                right = i + j - 1;
            } else if (z[i - left] < right - i + 1) {
                z[i] = z[i - left];
            } else {
                int j = 1;
                while (right + j < s.length && s[right + j] == s[right - i + j]) {
                    j++;
                }
                z[i] = right - i + j;
                left = i;
                right = right + j - 1;
            }
        }
        return z;
    }

    private static class InputReader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private char[] nextCharArr() throws IOException {
            byte c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            ArrayDeque<Character> dq = new ArrayDeque<>();
            do {
                dq.addLast((char) c);
                c = read();
            } while (!isSpaceChar(c));
            char[] res = new char[dq.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = dq.removeFirst();
            }
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
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