import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P463C3 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = ir.nextInt();
            }
        }

        int n2 = 2 * n;
        int n21 = n2 - 1;
        long[] pos = new long[n21];
        long[] neg = new long[n21];
        neg[0] = grid[0][0];
        int i2;
        for (int i = 1; i < n; i++) {
            i2 = 2 * i;
            neg[i2] = findSumNeg(grid, i, i, n);
            neg[i2 - 1] = findSumNeg(grid, i - 1, i, n);
        }
        int r = n - 1;
        pos[0] = grid[r][0];
        for (int i = 1; i < n; i++) {
            r--;
            i2 = 2 * i;
            pos[i2] = findSumPos(grid, r, i, n);
            pos[i2 - 1] = findSumPos(grid, r + 1, i, n);
        }

        long[] maxE = new long[]{0, 0, 0};
        long[] maxO = new long[]{0, 0, 1};
        int[] tmp = new int[]{-1, 0};
        int[] tmp2 = new int[2];
        int start = n;
        int end;
        for (int i = 0; i < n; i++) {
            tmp[0]++;
            tmp2[0] = tmp[0];
            tmp2[1] = tmp[1];
            start--;
            end = n21 - start;
            if (i % 2 == 0) {
                for (int j = start; j < end; j += 2) {
                    long cur = neg[i] + pos[j] - grid[tmp2[0]][tmp2[1]];
                    if (cur > maxE[0]) {
                        maxE[0] = cur;
                        maxE[1] = tmp2[0];
                        maxE[2] = tmp2[1];
                    }
                    tmp2[0]--;
                    tmp2[1]++;
                }
            } else {
                for (int j = start; j < end; j += 2) {
                    long cur = neg[i] + pos[j] - grid[tmp2[0]][tmp2[1]];
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
        for (int i = n; i < n21; i++) {
            tmp[1]++;
            tmp2[0] = tmp[0];
            tmp2[1] = tmp[1];
            start++;
            end = n21 - start;
            if (i % 2 == 0) {
                for (int j = start; j < end; j += 2) {
                    long cur = neg[i] + pos[j] - grid[tmp2[0]][tmp2[1]];
                    if (cur > maxE[0]) {
                        maxE[0] = cur;
                        maxE[1] = tmp2[0];
                        maxE[2] = tmp2[1];
                    }
                    tmp2[0]--;
                    tmp2[1]++;
                }
            } else {
                for (int j = start; j < end; j += 2) {
                    long cur = neg[i] + pos[j] - grid[tmp2[0]][tmp2[1]];
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

    private static long findSumNeg(int[][] grid, int row, int col, int n) {
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

    private static long findSumPos(int[][] grid, int row, int col, int n) {
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

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
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
