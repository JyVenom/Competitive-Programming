import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P463C2 {
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

        long[] maxE = new long[]{0, 0, 0};
        long[] maxO = new long[]{0, 0, 1};
        int N = n - 1;
        int[] tmp = new int[]{-1, 0};
        for (int i = 0; i < n; i++) {
            tmp[0]++;
            int[] tmp2 = tmp.clone();
            int start = 0;
            int[] tmp3 = new int[]{N, 0};
            for (int j = 0; j < n; j++) {
                if (tmp3[0] == tmp2[0] && tmp3[1] == tmp2[1]) {
                    start = j;
                    break;
                }
                tmp3[0]--;
            }
            if (i % 2 == 0) {
                for (int j = start; j < n2; j += 2) {
                    if (tmp2[0] < 0 || tmp2[1] == n) {
                        break;
                    }
                    long cur = neg[i][1] + pos[j][1] - grid[tmp2[0]][tmp2[1]];
                    if (cur > maxE[0]) {
                        maxE[0] = cur;
                        maxE[1] = tmp2[0];
                        maxE[2] = tmp2[1];
                    }
                    tmp2[0]--;
                    tmp2[1]++;
                }
            } else {
                for (int j = start; j < n2; j += 2) {
                    if (tmp2[0] < 0 || tmp2[1] == n) {
                        break;
                    }
                    long cur = neg[i][1] + pos[j][1] - grid[tmp2[0]][tmp2[1]];
                    if (cur > maxO[0]) {
                        maxO[0] = cur;
                        maxO[1] = tmp2[0];
                        maxO[2] = tmp2[1];
                    }
                    tmp2[0]--;
                    tmp2[1]++;
                }
            }
        }
        for (int i = n; i < n2; i++) {
            tmp[1]++;
            int[] tmp2 = tmp.clone();
            int start = 0;
            int[] tmp3 = new int[]{N, 0};
            for (int j = 0; j < n; j++) {
                if (tmp3[0] == tmp2[0] && tmp3[1] == tmp2[1]) {
                    start = j;
                    break;
                }
                tmp3[1]++;
            }
            if (i % 2 == 0) {
                for (int j = start; j < n2; j += 2) {
                    if (tmp2[0] < 0 || tmp2[1] == n) {
                        break;
                    }
                    long cur = neg[i][1] + pos[j][1] - grid[tmp2[0]][tmp2[1]];
                    if (cur > maxE[0]) {
                        maxE[0] = cur;
                        maxE[1] = tmp2[0];
                        maxE[2] = tmp2[1];
                    }
                    tmp2[0]--;
                    tmp2[1]++;
                }
            } else {
                for (int j = start; j < n2; j += 2) {
                    if (tmp2[0] < 0 || tmp2[1] == n) {
                        break;
                    }
                    long cur = neg[i][1] + pos[j][1] - grid[tmp2[0]][tmp2[1]];
                    if (cur > maxO[0]) {
                        maxO[0] = cur;
                        maxO[1] = tmp2[0];
                        maxO[2] = tmp2[1];
                    }
                    tmp2[0]--;
                    tmp2[1]++;
                }
            }
        }

        pw.println(maxE[0] + maxO[0]);
        pw.println((maxE[1] + 1) + " " + (maxE[2] + 1) + " " + (maxO[1] + 1) + " " + (maxO[2] + 1));
        pw.close();
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
