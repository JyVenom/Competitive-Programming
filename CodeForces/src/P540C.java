import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class P540C {
    private static final int[] dirR = new int[]{-1, 0, 1, 0};
    private static final int[] dirC = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        boolean[][] lvl = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                lvl[i][j] = ir.isSolid();
            }
        }

        int r1 = ir.nextInt() - 1;
        int c1 = ir.nextInt() - 1;
        int r2 = ir.nextInt() - 1;
        int c2 = ir.nextInt() - 1;
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r2 + dirR[i];
            int nc = c2 + dirC[i];
            if (isValid(lvl, nr, nc)) {
                cnt++;
            }
        }
        if (r1 == r2 && c1 == c2) {
            pw.println(cnt >= 1 ? "YES" : "NO");
        } else if ((r1 == r2 && c1 == c2 + 1) || (r1 == r2 && c1 == c2 - 1) || (r1 == r2 + 1 && c1 == c2) || (r1 == r2 - 1 && c1 == c2)) {
            if (lvl[r2][c2]) {
                pw.println(cnt >= 1 ? "YES" : "NO");
            } else {
                pw.println("YES");
            }
        } else if (lvl[r2][c2]) {
            if (cnt < 2) {
                pw.println("NO");
            } else {
                pw.println(bfs(lvl, r1, c1, r2, c2) ? "YES" : "NO");
            }
        } else {
            if (cnt < 1) {
                pw.println("NO");
            } else {
                pw.println(bfs(lvl, r1, c1, r2, c2) ? "YES" : "NO");
            }
        }

        pw.close();
    }

    private static boolean bfs(boolean[][] lvl, int r1, int c1, int r2, int c2) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r1, c1});
        boolean[][] visited = new boolean[lvl.length][lvl[0].length];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visited[cur[0]][cur[1]]) {
                continue;
            }

            visited[cur[0]][cur[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dirR[i];
                int nc = cur[1] + dirC[i];
                if (nr == r2 && nc == c2) {
                    return true;
                }
                if (isValid(lvl, nr, nc) && !visited[nr][nc]) {
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        return false;
    }

    private static boolean isValid(boolean[][] lvl, int r, int c) {
        return r >= 0 && r < lvl.length && c >= 0 && c < lvl[r].length && lvl[r][c];
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

        private boolean isSolid() throws IOException {
            byte c = read();
            while (!(c == '.' || c == 'X'))
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
    }
}
