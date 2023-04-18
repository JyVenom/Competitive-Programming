import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class ComfortableCows14 {
    private static final int[] dirRow = new int[]{-1, 0, 1, 0};
    private static final int[] dirCol = new int[]{0, 1, 0, -1};
    private static final boolean[][] map2 = new boolean[2001][2001];

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int prev = 0;
        for (int i = 0; i < n; i++) {
            int col = ir.nextInt() + 1000;
            int row = ir.nextInt() + 1000;

            if (map2[row][col]) {
                prev--;
                pw.println(prev);
            } else {
                map2[row][col] = true;
                ArrayDeque<int[]> queue = new ArrayDeque<>();
                if (comf(row, col)) {
                    queue.add(new int[]{row, col});
                }
                for (int j = 0; j < 4; j++) {
                    int newRow = row + dirRow[j];
                    int newCol = col + dirCol[j];
                    if (map2[newRow][newCol]) {
                        if (comf(newRow, newCol)) {
                            queue.add(new int[]{newRow, newCol});
                        }
                    }
                    prev = findAns(queue, prev);
                }

                pw.println(prev);
            }
        }

        pw.close();
    }

    private static boolean comf(int row, int col) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (map2[newRow][newCol]) {
                count++;
            }
        }
        return count == 3;
    }


    private static Point findMiss(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (!map2[newRow][newCol]) {
                return new Point(newRow, newCol);
            }
        }
        return new Point(-1, -1);
    }

    private static int findAns(ArrayDeque<int[]> queue, int prev) {
        boolean[][] visited = new boolean[2001][2001];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            visited[cur[0]][cur[1]] = true;
            Point tmp = findMiss(cur[0], cur[1]);
            if (map2[tmp.row][tmp.col]) {
                continue;
            }
            prev++;
            map2[tmp.row][tmp.col] = true;
            if (comf(tmp.row, tmp.col)) {
                queue.add(new int[]{tmp.row, tmp.col});
            }

            for (int j = 0; j < 4; j++) {
                int newRow = tmp.row + dirRow[j];
                int newCol = tmp.col + dirCol[j];
                if (map2[newRow][newCol] && !visited[newRow][newCol]) {
                    if (comf(newRow, newCol)) {
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
        }
        return prev;
    }

    private static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
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
