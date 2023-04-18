import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P1438C9 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            int m = ir.nextInt();
            int n1 = n - 1;
            int m1 = m - 1;

            int[][] c = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    c[i][j] = ir.nextInt();
                }
            }

            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && count(c, i, j, n1, m1) > 0) {
                        dfs(c, visited, i, j, n1, m1);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    pw.print(c[i][j] + " ");
                }
                pw.println();
            }
        }

        pw.close();
    }

    private static boolean dfs(int[][] c, boolean[][] visited, int row, int col, int n1, int m1) {
        if (visited[row][col]) {
            return false;
        }

        visited[row][col] = true;
        c[row][col]++;

        if (row > 0) {
            if (c[row][col] == c[row - 1][col]) {
                boolean successful = dfs(c, visited, row - 1, col, n1, m1);
                if (!successful) {
                    visited[row][col] = false;
                    c[row][col]--;
                    return false;
                }
            }
        }
        if (col > 0) {
            if (c[row][col] == c[row][col - 1]) {
                boolean successful = dfs(c, visited, row, col - 1, n1, m1);
                if (!successful) {
                    visited[row][col] = false;
                    c[row][col]--;
                    return false;
                }
            }
        }
        if (row < n1) {
            if (c[row][col] == c[row + 1][col]) {
                boolean successful = dfs(c, visited, row + 1, col, n1, m1);
                if (!successful) {
                    visited[row][col] = false;
                    c[row][col]--;
                    return false;
                }
            }
        }
        if (col < m1) {
            if (c[row][col] == c[row][col + 1]) {
                boolean successful = dfs(c, visited, row, col + 1, n1, m1);
                if (!successful) {
                    visited[row][col] = false;
                    c[row][col]--;
                    return false;
                }
            }
        }

        return true;
    }

    private static int count(int[][] c, int row, int col, int n1, int m1) {
        int count = 0;

        if (row > 0) {
            if (c[row][col] == c[row - 1][col]) {
                count++;
            }
        }
        if (col > 0) {
            if (c[row][col] == c[row][col - 1]) {
                count++;
            }
        }
        if (row < n1) {
            if (c[row][col] == c[row + 1][col]) {
                count++;
            }
        }
        if (col < m1) {
            if (c[row][col] == c[row][col + 1]) {
                count++;
            }
        }

        return count;
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
