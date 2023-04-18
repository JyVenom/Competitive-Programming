import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Labyrinth {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = r.nextInt();
        int m = r.nextInt();
        boolean[][] map = new boolean[n][m];
        int[] start = new int[2];
        int[] end = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int type = r.type();
                if (type == 0) {
                    map[i][j] = true;
                } else if (type == 1) {
                    map[i][j] = true;
                    start[0] = i;
                    start[1] = j;
                } else if (type == 2) {
                    map[i][j] = true;
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        r.close();

        String ans = bfs(map, start, end);

        if (ans.length() == 0) {
            pw.println("NO");
        } else {
            pw.println("YES");
            pw.println(ans.length());
            pw.println(ans);
        }
        pw.close();
    }

    private static String bfs(boolean[][] map, int[] at, int[] end) {
        LinkedList<state> queue = new LinkedList<>();
        queue.offer(new state(at[0], at[1], ""));
        boolean[][] visited = new boolean[map.length][map[0].length];
        int N = map.length - 1;
        int M = map[0].length - 1;

        while (!queue.isEmpty()) {
            state cur = queue.poll();

            if (cur.row == end[0] && cur.col == end[1]) {
                return cur.path;
            }

            visited[cur.row][cur.col] = true;

            if (cur.row > 0 && !visited[cur.row - 1][cur.col] && map[cur.row - 1][cur.col]) {
                queue.offer(new state(cur.row - 1, cur.col, cur.path + "U"));
            }
            if (cur.col < M && !visited[cur.row][cur.col + 1] && map[cur.row][cur.col + 1]) {
                queue.offer(new state(cur.row, cur.col + 1, cur.path + "R"));
            }
            if (cur.row < N && !visited[cur.row + 1][cur.col] && map[cur.row + 1][cur.col]) {
                queue.offer(new state(cur.row + 1, cur.col, cur.path + "D"));
            }
            if (cur.col > 0 && !visited[cur.row][cur.col - 1] && map[cur.row][cur.col - 1]) {
                queue.offer(new state(cur.row, cur.col - 1, cur.path + "L"));
            }
        }
        return "";
    }

    private static class state {
        int row, col;
        String path;

        public state(int row, int col, String path) {
            this.row = row;
            this.col = col;
            this.path = path;
        }
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
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

        private int type() throws IOException {
            byte c = read();
            while (c == '\n' || c == '\r')
                c = read();
            if (c == '.') {
                return 0;
            } else if (c == '#') {
                return -1;
            } else if (c == 'A') {
                return 1;
            } else {
                return 2;
            }
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

        private void close() throws IOException {
            dis.close();
        }
    }
}
