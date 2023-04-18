import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SpacedOut6 {
    private static int n, N, max = 0;
    private static int[][] data;

    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        n = ir.nextInt();
        N = n - 1;
        data = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = ir.nextInt();
            }
        }

        if (n <= 10) {
            dfs(0, 0, new boolean[n][n]);
        } else {
            int rows = 0;
            for (int i = 0; i < n; i += 2) {
                for (int j = 0; j < n; j++) {
                    rows += data[i][j];
                }
            }
            max = rows;
            rows = 0;
            for (int i = 1; i < n; i += 2) {
                for (int j = 0; j < n; j++) {
                    rows += data[i][j];
                }
            }
            max = Math.max(max, rows);

            int cols = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j += 2) {
                    cols += data[i][j];
                }
            }
            max = Math.max(max, cols);
            cols = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < n; j += 2) {
                    cols += data[i][j];
                }
            }
            max = Math.max(max, cols);
        }

        pw.println(max);
        pw.close();
    }

    private static void dfs(int row, int col, boolean[][] used) {
        if (row == N && col == N) {
            if (valid(used)) {
                max = Math.max(max, val(used));
            }
            used[row][col] = true;
            if (valid(used)) {
                max = Math.max(max, val(used));
            }
            used[row][col] = false;
            return;
        }

        if (row > 0 && col > 0) {
            int count = 0;
            if (used[row - 1][col]) {
                count++;
            }
            if (used[row - 1][col - 1]) {
                count++;
            }
            if (used[row][col - 1]) {
                count++;
            }
            if (count == 0 || count > 2) {
                return;
            }
            if (count == 1) {
                used[row][col] = true;
                if (col < N) {
                    dfs(row, col + 1, used);
                } else {
                    dfs(row + 1, 0, used);
                }
                used[row][col] = false;
            }
            if (count == 2) {
                if (col < N) {
                    dfs(row, col + 1, used);
                } else {
                    dfs(row + 1, 0, used);
                }
            }
        } else {
            if (col < N) {
                dfs(row, col + 1, used);
            } else {
                dfs(row + 1, 0, used);
            }
            used[row][col] = true;
            if (col < N) {
                dfs(row, col + 1, used);
            } else {
                dfs(row + 1, 0, used);
            }
            used[row][col] = false;
        }
    }

    private static int val(boolean[][] used) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (used[i][j]) {
                    sum += data[i][j];
                }
            }
        }
        return sum;
    }

    private static boolean valid(boolean[][] used) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                int count = 0;
                if (used[i][j]) {
                    count++;
                }
                if (used[i - 1][j]) {
                    count++;
                }
                if (used[i - 1][j - 1]) {
                    count++;
                }
                if (used[i][j - 1]) {
                    count++;
                }
                if (count != 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class InputReader {
        final private int BUFFER_SIZE = 1 << 24;
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
