import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P432D4 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        String str = ir.nextLine();
        int n = str.length();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = str.charAt(i);
        }
        int[] z = Z(s);
        z[0] = n;
        int[] c = new int[n + 1];
        for (int i = 0; i < n; i++) {
            c[z[i]]++;
        }
        for (int i = n - 1; i >= 0; i--) {
            c[i] += c[i + 1];
        }
        int[] x = new int[n];
        int[] y = new int[n];
        int count = 0;
        for (int l = 1; l <= n; l++) {
            if (z[n - l] == l) {
                x[count] = l;
                y[count++] = c[l];
            }
        }

        pw.println(count);
        for (int i = 0; i < count; i++) {
            pw.println(x[i] + " " + y[i]);
        }
        pw.close();
    }

    private static int[] Z(int[] s) {
        int n = s.length;
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; ++i) {
            if (i <= r)
                z[i] = Math.min(r - i + 1, z[i - l]);
            while (i + z[i] < n && s[z[i]] == s[i + z[i]])
                ++z[i];
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
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
