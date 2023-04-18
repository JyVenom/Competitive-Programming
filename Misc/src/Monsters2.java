import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Monsters2 {
    private static final ArrayList<Character> path = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = fr.nextInt();
        int m = fr.nextInt();
        boolean[][] lab = new boolean[n][m];
        ArrayList<int[]> monsters = new ArrayList<>();
        int[] start = new int[2];
        for (int i = 0; i < n; i++) {
            char[] line = fr.getCharArr();
            for (int j = 0; j < m; j++) {
                if (line[j] != '#') {
                    lab[i][j] = true;

                    if (line[j] == 'M') {
                        monsters.add(new int[]{i, j});
                    } else if (line[j] == 'A') {
                        start[0] = i;
                        start[1] = j;
                    }
                }
            }
        }

        int[][] dist = new int[n][m];
        int MAX = n * m;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], MAX);
        }
        for (int[] monster : monsters) {
            BFS(lab, dist, monster[0], monster[1]);
        }
        boolean res = DFS(lab, new boolean[n][m], dist, start[0], start[1], 0, n - 1, m - 1);

        if (res) {
            pw.println("YES");
            pw.println(path.size());
            StringBuilder sb = new StringBuilder();
            for (char c : path) {
                sb.append(c);
            }
            pw.print(sb);
        } else {
            pw.println("NO");
        }
        pw.close();
    }

    private static void BFS(boolean[][] lab, int[][] dist, int row, int col) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col, 0});
        boolean[][] visited = new boolean[dist.length][dist[0].length];
        int N = dist.length - 1;
        int M = dist[0].length - 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            dist[cur[0]][cur[1]] = Math.min(dist[cur[0]][cur[1]], cur[2]);
            visited[cur[0]][cur[1]] = true;

            if (cur[0] > 0 && !visited[cur[0] - 1][cur[1]] && lab[cur[0] - 1][cur[1]]) {
                queue.offer(new int[]{cur[0] - 1, cur[1], cur[2] + 1});
            }
            if (cur[1] < M && !visited[cur[0]][cur[1] + 1] && lab[cur[0]][cur[1] + 1]) {
                queue.offer(new int[]{cur[0], cur[1] + 1, cur[2] + 1});
            }
            if (cur[0] < N && !visited[cur[0] + 1][cur[1]] && lab[cur[0] + 1][cur[1]]) {
                queue.offer(new int[]{cur[0] + 1, cur[1], cur[2] + 1});
            }
            if (cur[1] > 0 && !visited[cur[0]][cur[1] - 1] && lab[cur[0]][cur[1] - 1]) {
                queue.offer(new int[]{cur[0], cur[1] - 1, cur[2] + 1});
            }
        }
    }

    private static boolean DFS(boolean[][] lab, boolean[][] visited, int[][] dist, int row, int col, int cost, int N, int M) {
        if (cost >= dist[row][col]) {
            return false;
        }

        if (row == 0 || row == N || col == 0 || col == M) {
            return true;
        }

        visited[row][col] = true;

        int sz = path.size();
        if (!visited[row - 1][col] && lab[row - 1][col]) {
            path.add('U');
            if (DFS(lab, visited, dist, row - 1, col, cost + 1, N, M)) {
                return true;
            }
            path.remove(sz);
        }
        if (!visited[row][col + 1] && lab[row][col + 1]) {
            path.add('R');
            if (DFS(lab, visited, dist, row, col + 1, cost + 1, N, M)) {
                return true;
            }
            path.remove(sz);
        }
        if (!visited[row + 1][col] && lab[row + 1][col]) {
            path.add('D');
            if (DFS(lab, visited, dist, row + 1, col, cost + 1, N, M)) {
                return true;
            }
            path.remove(sz);
        }
        if (!visited[row][col - 1] && lab[row][col - 1]) {
            path.add('L');
            if (DFS(lab, visited, dist, row, col - 1, cost + 1, N, M)) {
                return true;
            }
            path.remove(sz);
        }
        visited[row][col] = false;

        return false;
    }

    private static class FastReader {
        final private int BUFFER_SIZE = 1 << 20;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private char[] getCharArr() throws IOException {
            char[] buf = new char[1024]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (char) c;
            }
            return buf;
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

        public void close() throws IOException {
            dis.close();
        }
    }
}
