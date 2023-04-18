import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Labyrinth2 {
    private static final int[] row = {-1, 0, 0, 1};
    private static final int[] col = {0, -1, 1, 0};
    private static int n, m;
    private static char[][] chars;
    private static int[][] dist, dirs;

    private static boolean bfs(int i, int j) {
        int[] next = new int[n * m * 2];
        int head = 0, cnt = 0;
        dist[i][j] = 0;
        next[head + cnt++] = i;
        next[head + cnt++] = j;
        while (cnt > 0) {
            i = next[head++];
            cnt--;
            j = next[head++];
            cnt--;
            int newDist = dist[i][j] + 1;
            for (int dir = 0; dir < 4; dir++) {
                int nxtI = i + row[dir];
                int nxtJ = j + col[dir];
                if (nxtI >= 0 && nxtI < n && nxtJ >= 0 && nxtJ < m && chars[nxtI][nxtJ] != '#' && dist[nxtI][nxtJ] > newDist) {
                    dist[nxtI][nxtJ] = newDist;
                    dirs[nxtI][nxtJ] = dir;
                    if (chars[nxtI][nxtJ] == 'B') {
                        return true;
                    }
                    next[head + cnt++] = nxtI;
                    next[head + cnt++] = nxtJ;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        n = r.nextInt();
        m = r.nextInt();
        chars = new char[n][m];
        for (int i = 0; i < n; i++) {
            r.readLine().getChars(0, m, chars[i], 0);
        }
        dist = new int[n][m];
        dirs = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = n * m;
            }
        }
        r.close();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (chars[i][j] == 'A') {
                    if (bfs(i, j)) {
                        pw.println("YES");
                        out:
                        for (i = 0; i < n; i++) {
                            for (j = 0; j < m; j++) {
                                if (chars[i][j] == 'B') {
                                    break out;
                                }
                            }
                        }
                        pw.println(dist[i][j]);
                        StringBuilder sb = new StringBuilder();
                        for (int d = dist[i][j]; d > 0; d--) {
                            int dir = dirs[i][j];
                            String[] temp = new String[]{"U", "L", "R", "D"};
                            sb.append(temp[dir]);
                            i -= row[dir];
                            j -= col[dir];
                        }
                        pw.println(sb.reverse());
                        pw.close();
                        return;
                    }
                    pw.println("NO");
                    pw.close();
                    return;
                }
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

        public String readLine() throws IOException {
            byte[] buf = new byte[1024]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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
