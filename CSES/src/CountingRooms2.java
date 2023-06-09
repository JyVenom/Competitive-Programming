import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class CountingRooms2 {
    public static void main(String[] args) throws IOException {
        Reader fr = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = fr.nextInt();
        int m = fr.nextInt();
        boolean[][] floor = new boolean[n][m];
        boolean open = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                floor[i][j] = fr.isFloor();
                if (!floor[i][j]) {
                    open = false;
                }
            }
        }
        fr.close();

        int comp;
        if (open) {
            comp = 1;
        } else {
            int N = n - 1;
            int M = m - 1;
            comp = 0;
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (floor[i][j] && !visited[i][j]) {
                        bfs(visited, floor, i, j, N, M);
                        comp++;
                    }
                }
            }
        }

        pw.println(comp);
        pw.close();
    }

    private static void bfs(boolean[][] visited, boolean[][] floor, int row, int col, int N, int M) {
        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});

        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();

            visited[cur[0]][cur[1]] = true;
            if (row > 0 && floor[row - 1][col] && !visited[row - 1][col]) {
                q.add(new int[]{row - 1, col});
            }
            if (col < M && floor[row][col + 1] && !visited[row][col + 1]) {
                q.add(new int[]{row, col + 1});
            }
            if (row < N && floor[row + 1][col] && !visited[row + 1][col]) {
                q.add(new int[]{row + 1, col});
            }
            if (col > 0 && floor[row][col - 1] && !visited[row][col - 1]) {
                q.add(new int[]{row, col - 1});
            }
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

        private boolean isFloor() throws IOException {
            byte c = read();
            while (c == '\n' || c == '\r')
                c = read();
            return c == '.';
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
