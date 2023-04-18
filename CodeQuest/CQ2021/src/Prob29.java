import java.io.*;
import java.util.StringTokenizer;

public class Prob29 {
    private static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        loop:
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            char[][] map = new char[n][m];
            for (int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
            }

            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 'C') {
                        min = Integer.MAX_VALUE;
                        dfs(map, visited, i, j, 0);
                        pw.println(min);
                        continue loop;
                    }
                }
            }
        }

        pw.close();
    }

    private static void dfs(char[][] map, boolean[][] visited, int row, int col, int dist) {
        visited[row][col] = true;

        if (map[row][col] == '$') {
            min = Math.min(min, dist);
            return;
        }

        if (row > 0) {
            if (map[row - 1][col] != '#') {
                if (!visited[row - 1][col]) {
                    visited[row][col] = true;
                    dfs(map, visited, row - 1, col, dist + 1);
                    visited[row][col] = false;
                }
            }
        }
        if (col < visited[0].length - 1) {
            if (map[row][col + 1] != '#') {
                if (!visited[row][col + 1]) {
                    visited[row][col] = true;
                    dfs(map, visited, row, col + 1, dist + 1);
                    visited[row][col] = false;
                }
            }
        }
        if (row < visited.length - 1) {
            if (map[row + 1][col] != '#') {
                if (!visited[row + 1][col]) {
                    visited[row][col] = true;
                    dfs(map, visited, row + 1, col, dist + 1);
                    visited[row][col] = false;
                }
            }
        }
        if (col > 0) {
            if (map[row][col - 1] != '#') {
                if (!visited[row][col - 1]) {
                    visited[row][col] = true;
                    dfs(map, visited, row, col - 1, dist + 1);
                    visited[row][col] = false;
                }
            }
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
            return c == '\n' || c == '\r' || c == '\t' || c == -1;
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

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
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

        private void close() throws IOException {
            dis.close();
        }
    }
}
