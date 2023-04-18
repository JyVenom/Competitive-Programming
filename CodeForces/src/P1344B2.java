import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1344B2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        boolean[][] grid = new boolean[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (ir.isBlack()) {
                    grid[i][j] = true;
                }
            }
        }

        boolean vflip = false;
        for (int i = 1; i <= n; i++) {
            int flips = 0;
            for (int j = 0; j < m + 2; j++) {
                if (grid[i][j] && !grid[i][j - 1]) {
                    flips++;
                }
            }
            if (flips == 0) vflip = true;
            if (flips >= 2) {
                pw.println(-1);
                pw.close();
                return;
            }
        }
        boolean hflip = false;
        for (int j = 1; j <= m; j++) {
            int flips = 0;
            for (int i = 0; i < n + 2; i++) {
                if (grid[i][j] && !grid[i - 1][j]) {
                    flips++;
                }
            }
            if (flips == 0) hflip = true;
            if (flips >= 2) {
                pw.println(-1);
                pw.close();
                return;
            }
        }
        if ((vflip && !hflip) || (!vflip && hflip)) {
            pw.println(-1);
            pw.close();
            return;
        }
        int l = 0;
        int r = m + 1;
        int res = 0;
        boolean nblank, blank = true;
        for (int i = 0; i < n + 2; i++) {
            boolean fl = false;
            int nl = 0;
            int nr = m + 1;
            for (int j = 0; j < m + 2; j++) {
                if (grid[i][j] && !fl) {
                    fl = true;
                    nl = j;
                }
                if (!grid[i][j] && fl) {
                    nr = j - 1;
                    break;
                }
            }
            nblank = (nl == 0) && (nr == m + 1);
            if (!blank && nblank) res++;
            if (!blank && (nr < l || nl > r)) {
                res++;
            }
            blank = nblank;
            l = nl;
            r = nr;
        }

        pw.println(res);
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

        private boolean isBlack() throws IOException {
            byte c = read();
            while (!(c == '#' || c == '.'))
                c = read();
            return c == '#';
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