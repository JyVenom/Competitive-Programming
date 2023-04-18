import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class P1344B {
    private static final int[] dirR = new int[]{-1, 0, 1, 0};
    private static final int[] dirC = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        boolean[][] map = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = ir.isBlack();
            }
        }

        HashSet<Integer> rows = new HashSet<>();
        HashSet<Integer> cols = new HashSet<>();
        for (int i = 0; i < n; i++) {
            rows.add(i);
        }
        for (int i = 0; i < m; i++) {
            cols.add(i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[j][i]) {
                    boolean sawWhite = false;
                    for (int k = j + 1; k < n; k++) {
                        if (!map[k][i]) {
                            sawWhite = true;
                        } else {
                            if (sawWhite) {
                                pw.println(-1);
                                pw.close();
                                return;
                            }
                        }
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j]) {
                    rows.remove(i);
                    cols.remove(j);
                    boolean sawWhite = false;
                    for (int k = j + 1; k < m; k++) {
                        if (!map[i][k]) {
                            sawWhite = true;
                        } else {
                            if (sawWhite) {
                                pw.println(-1);
                                pw.close();
                                return;
                            }
                            cols.remove(k);
                        }
                    }
                    break;
                }
            }
        }
        if (!((rows.isEmpty() && cols.isEmpty()) || (rows.size() > 0 && cols.size() > 0))) {
            pw.println(-1);
            pw.close();
            return;
        }
        int count = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] && !visited[i][j]) {
                    dfs(visited, map, i, j);
                    count++;
                }
            }
        }

        pw.println(count);
        pw.close();
    }

    private static void dfs(boolean[][] visited, boolean[][] map, int row, int col) {
        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int nr = row + dirR[i];
            int nc = col + dirC[i];
            if (isValid(map, visited, nr, nc)) {
                dfs(visited, map, nr, nc);
            }
        }
    }

    private static boolean isValid(boolean[][] map, boolean[][] visited, int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[r].length && map[r][c] && !visited[r][c];
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

        private boolean isBlack() throws IOException {
            byte c = read();
            while (!(c == '#' || c == '.'))
                c = read();
            return c == '#';
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
