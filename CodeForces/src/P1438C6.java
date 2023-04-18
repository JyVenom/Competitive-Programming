import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class P1438C6 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            int m = ir.nextInt();
            int n1 = n - 1;
            int m1 = m - 1;

            int[][] c = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    c[i][j] = ir.nextInt();
                }
            }

            HashMap<Integer, HashMap<Integer, cell>> map = new HashMap<>(n);
            ArrayList<cell> cells = new ArrayList<>(n * m);
            for (int i = 0; i < n; i++) {
                map.put(i, new HashMap<>());
                for (int j = 0; j < m; j++) {
                    map.get(i).put(j, new cell(i, j));
                    cells.add(map.get(i).get(j));
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    if (c[i][j] == c[i][j - 1]) {
                        map.get(i).get(j).connections.add(3);
                        map.get(i).get(j - 1).connections.add(1);
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (c[j][i] == c[j - 1][i]) {
                        map.get(j).get(i).connections.add(0);
                        map.get(j - 1).get(i).connections.add(2);
                    }
                }
            }

            PriorityQueue<cell> pq = new PriorityQueue<>((o1, o2) -> {
                if (o1.connections.size() > o2.connections.size()) {
                    return -1;
                } else if (o1.connections.size() == o2.connections.size()) {
                    if (o1.row < o2.row) {
                        return -1;
                    } else if (o1.row == o2.row) {
                        return Integer.compare(o1.col, o2.col);
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            });
            pq.addAll(cells);
            int count = 0;
            int[][] map2 = new int[n][m];
            while (!pq.isEmpty()) {
                cell cur = pq.poll();

                map2[cur.row][cur.col] = count++;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    pw.print(map2[i][j] + " ");
                }
                pw.println();
            }
        }

        pw.close();
    }

    private static class cell {
        int row, col;
        boolean visited = false;
        HashSet<Integer> connections = new HashSet<>();

        public cell(int row, int col) {
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
