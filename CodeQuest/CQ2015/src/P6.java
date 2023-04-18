import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P6 {
    private static final int[] dirRow = new int[]{-1, -1, +0, +1, +1, +1, +0, -1};
    private static final int[] dirCol = new int[]{+0, +1, +1, +1, +0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int r = ir.nextInt();
            int c = ir.nextInt();
            int[][] grid = new int[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    grid[i][j] = ir.nextLine().charAt(0) - 'A';
                }
            }

            int n = ir.nextInt();
            while (n-- > 0) {
                String tmp = ir.nextLine();
                int[] word = new int[tmp.length()];
                for (int i = 0; i < tmp.length(); i++) {
                    word[i] = tmp.charAt(i) - 'A';
                }
                loop:
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (dfs(grid, word, 0, i, j)) {
                            pw.println(tmp);
                            break loop;
                        }
                    }
                }
            }
        }

        pw.close();
    }

    private static boolean dfs(int[][] grid, int[] word, int at, int row, int col) {
        if (at == word.length) {
            return true;
        } else if (row < 0
                || col < 0
                || row >= grid.length
                || col >= grid[row].length
                || grid[row][col] != word[at]) {
            return false;
        }

        at++;
        for (int i = 0; i < dirRow.length; i++) {
            if (dfs(grid, word, at, row + dirRow[i], col + dirCol[i])) {
                return true;
            }
        }
        return false;
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 16;
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
