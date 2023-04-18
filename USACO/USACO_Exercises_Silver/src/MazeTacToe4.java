import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class MazeTacToe4 {
    private static final char[][][] board = new char[25][25][3];
    private static final HashSet<Integer> answers = new HashSet<>();
    private static final boolean[][][] visited = new boolean[25][25][19683];
    private static final int[] pow3 = new int[10];

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int n3 = 3 * n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n3; j++) {
                board[i][j / 3][j % 3] = (char) ir.read();
            }
            ir.read();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j][0] == 'M' || board[i][j][0] == 'O') {
                    board[i][j][1] -= '1';
                    board[i][j][2] -= '1';
                }
            }
        }
        int bI = -1, bJ = -1, bState = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j][0] == 'B') {
                    bI = i;
                    bJ = j;
                    break;
                }
            }
        }
        pow3[0] = 1;
        for (int i = 1; i <= 9; i++) pow3[i] = pow3[i - 1] * 3;
        dfs(bI, bJ, bState);

        pw.println(answers.size());
        pw.close();
    }

    private static boolean test(int b) {
        int[][] cells = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                cells[i][j] = b % 3;
                b /= 3;
            }
        for (int r = 0; r < 3; r++) {
            if (cells[r][0] == 1 && cells[r][1] == 2 && cells[r][2] == 2) return true;
            if (cells[r][0] == 2 && cells[r][1] == 2 && cells[r][2] == 1) return true;
        }
        for (int c = 0; c < 3; c++) {
            if (cells[0][c] == 1 && cells[1][c] == 2 && cells[2][c] == 2) return true;
            if (cells[0][c] == 2 && cells[1][c] == 2 && cells[2][c] == 1) return true;
        }
        if (cells[0][0] == 1 && cells[1][1] == 2 && cells[2][2] == 2) return true;
        if (cells[0][0] == 2 && cells[1][1] == 2 && cells[2][2] == 1) return true;
        if (cells[2][0] == 1 && cells[1][1] == 2 && cells[0][2] == 2) return true;
        return cells[2][0] == 2 && cells[1][1] == 2 && cells[0][2] == 1;
    }

    private static void dfs(int i, int j, int b) {
        if (visited[i][j][b]) return;
        visited[i][j][b] = true;
        if (board[i][j][0] == 'M' || board[i][j][0] == 'O') {
            int r = board[i][j][1], c = board[i][j][2], idx = r * 3 + c;
            int current_char = (b / pow3[idx]) % 3;
            if (current_char == 0) {
                int new_char = board[i][j][0] == 'M' ? 1 : 2;
                b = (b % pow3[idx]) + new_char * pow3[idx] + (b - b % pow3[idx + 1]);
                if (!visited[i][j][b] && test(b)) {
                    answers.add(b);
                    return;
                }
                visited[i][j][b] = true;
            }
        }
        if (board[i - 1][j][0] != '#') dfs(i - 1, j, b);
        if (board[i][j + 1][0] != '#') dfs(i, j + 1, b);
        if (board[i + 1][j][0] != '#') dfs(i + 1, j, b);
        if (board[i][j - 1][0] != '#') dfs(i, j - 1, b);
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
