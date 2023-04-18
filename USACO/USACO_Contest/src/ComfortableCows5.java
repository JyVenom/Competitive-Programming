import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ComfortableCows5 {
    private static final int[] dirRow = new int[]{-1, 0, 1, 0};
    private static final int[] dirCol = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int N = n + 1;
        boolean[][] map = new boolean[N][N];
        boolean[][] all = new boolean[N][N];
        int count = 0;
        for (int i = 0; i < n; i++) {
            int col = ir.nextInt();
            int row = ir.nextInt();

            map[row][col] = true;
            if (comf(map, row, col, n)) {
                count++;
                all[row][col] = true;
            }
            for (int j = 0; j < 4; j++) {
                int newRow = row + dirRow[j];
                int newCol = col + dirCol[j];
                if (isValid(newRow, newCol, n) && map[newRow][newCol]) {
                    if (all[newRow][newCol]) {
                        count--;
                        all[newRow][newCol] = false;
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                int newRow = row + dirRow[j];
                int newCol = col + dirCol[j];
                if (isValid(newRow, newCol, n) && map[newRow][newCol]) {
                    if (comf(map, newRow, newCol, n)) {
                        count++;
                        all[newRow][newCol] = true;
                    }
                }
            }
            pw.println(count);
        }

        pw.close();
    }

    private static boolean comf(boolean[][] map, int row, int col, int n) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (isValid(newRow, newCol, n) && map[newRow][newCol]) {
                count++;
            }
        }
        return count == 3;
    }

    private static boolean isValid(int row, int col, int n) {
        return row >= 0 && col >= 0 && row <= n && col <= n;
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
