import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P463C {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        long[][] grid = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = ir.nextLong();
            }
        }

        int n2 = (2 * n) - 1;
        long[][] pos = new long[n2][2];
        long[][] neg = new long[n2][2];
        neg[0][0] = 0;
        neg[0][1] = grid[0][0];
        for (int i = 1; i < n; i++) {
            int i2 = 2 * i;
            int i21 = i2 - 1;
            neg[i2][0] = i2;
            neg[i2][1] = findSumNeg(grid, i, i, n);
            neg[i21][0] = i21;
            neg[i21][1] = findSumNeg(grid, i - 1, i, n);
        }
        pos[0][0] = 0;
        int r = n - 1;
        pos[0][1] = grid[r][0];
        r--;
        for (int i = 1; i < n; i++) {
            int i2 = 2 * i;
            int i21 = i2 - 1;
            pos[i2][0] = i2;
            pos[i2][1] = findSumPos(grid, r, i, n);
            pos[i21][0] = i21;
            pos[i21][1] = findSumPos(grid, r + 1, i, n);
            r--;
        }

        long[] maxNE = new long[2];
        for (int i = 0; i < n2; i += 2) {
            if (neg[i][1] > maxNE[1]) {
                maxNE = neg[i];
            }
        }
        long[] maxNO = new long[2];
        for (int i = 1; i < n2; i += 2) {
            if (neg[i][1] > maxNO[1]) {
                maxNO = neg[i];
            }
        }
        long[] maxPE = new long[2];
        for (int i = 0; i < n2; i += 2) {
            if (pos[i][1] > maxPE[1]) {
                maxPE = pos[i];
            }
        }
        long[] maxPO = new long[2];
        for (int i = 1; i < n2; i += 2) {
            if (pos[i][1] > maxPO[1]) {
                maxPO = pos[i];
            }
        }
        int[] evenLoc, oddLoc;
        int N = n - 1;
        if (n % 2 == 1) {
            evenLoc = findLoc(maxNE[0] / 2, (maxNE[0] + 1) / 2, N - (maxPE[0] / 2), (maxPE[0] + 1) / 2, n);
            oddLoc = findLoc(maxNO[0] / 2, (maxNO[0] + 1) / 2, N - (maxPO[0] / 2), (maxPO[0] + 1) / 2, n);
        } else {
            evenLoc = findLoc(maxNE[0] / 2, (maxNE[0] + 1) / 2, N - (maxPO[0] / 2), (maxPO[0] + 1) / 2, n);
            oddLoc = findLoc(maxNO[0] / 2, (maxNO[0] + 1) / 2, N - (maxPE[0] / 2), (maxPE[0] + 1) / 2, n);
        }

        pw.println(maxNE[1] + maxNO[1] + maxPE[1] + maxPO[1] - grid[evenLoc[0]][evenLoc[1]] - grid[oddLoc[0]][oddLoc[1]]);
        pw.println((evenLoc[0] + 1) + " " + (evenLoc[1] + 1) + " " + (oddLoc[0] + 1) + " " + (oddLoc[1] + 1));
        pw.close();
    }

    private static int[] findLoc(long r1, long c1, long r2, long c2, int n) { //(r1, c1) is the neg one
        int r = (int) r1;
        for (int i = (int) c1; i < n; i++) {
            if (r < 0) {
                break;
            }
            if (i - c2 == r - r2) {
                return new int[]{r, i};
            }
            r--;
        }
        r = (int) r1 + 1;
        for (int i = (int) c1 - 1; i >= 0; i--) {
            if (r == n) {
                break;
            }
            if (i - c2 == r - r2) {
                return new int[]{r, i};
            }
            r++;
        }
        return new int[]{-1, -1};
    }

    private static long findSumNeg(long[][] grid, int row, int col, int n) {
        long sum = 0;
        int r = row;
        for (int i = col; i < n; i++) {
            if (r < 0) {
                break;
            }
            sum += grid[r][i];
            r--;
        }
        r = row + 1;
        for (int i = col - 1; i >= 0; i--) {
            if (r == n) {
                break;
            }
            sum += grid[r][i];
            r++;
        }
        return sum;
    }

    private static long findSumPos(long[][] grid, int row, int col, int n) {
        long sum = 0;
        int r = row;
        for (int i = col; i < n; i++) {
            if (r == n) {
                break;
            }
            sum += grid[r][i];
            r++;
        }
        r = row - 1;
        for (int i = col - 1; i >= 0; i--) {
            if (r < 0) {
                break;
            }
            sum += grid[r][i];
            r--;
        }
        return sum;
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 24;
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

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
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
