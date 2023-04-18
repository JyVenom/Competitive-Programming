import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class PRA8 {
    private static final int[] dirX = new int[]{0, 1, 0, -1};
    private static final int[] dirY = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = ir.nextLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'A') {
                    pw.println(bfs(map, i, j));
                    break;
                }
            }
        }

        pw.close();
    }

    private static int bfs(char[][] map, int startRow, int startCol) {
        ArrayDeque<state> queue = new ArrayDeque<>();
        queue.addLast(new state(startRow, startCol, 0));
        boolean[][] visited = new boolean[map.length][map[0].length];

        while (!queue.isEmpty()) {
            state cur = queue.removeFirst();

            if (map[cur.row][cur.col] == 'B') {
                return cur.cost;
            }

            visited[cur.row][cur.col] = true;

            int newCost = cur.cost + 1;
            for (int i = 0; i < 4; i++) {
                int nextRow = cur.row + dirY[i];
                int nextCol = cur.col + dirX[i];
                if (pos(map, visited, nextRow, nextCol)) {
                    queue.add(new state(nextRow, nextCol, newCost));
                }
            }
        }
        return -1;
    }

    private static boolean pos(char[][] map, boolean[][] visited, int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length && !visited[row][col] && map[row][col] != '#';
    }

    private static class state {
        int row, col, cost;

        public state(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
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

        private String nextLine() throws IOException {
            byte[] buf = new byte[BUFFER_SIZE]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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
