import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class ComfortableCows12 {
    private static final int[] dirRow = new int[]{-1, 0, 1, 0};
    private static final int[] dirCol = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        boolean[][] map = new boolean[2001][2001];
        ArrayList<int[]> comfortable = new ArrayList<>();
        ArrayList<Integer> add = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int col = ir.nextInt() + 1000;
            int row = ir.nextInt() + 1000;

            map[row][col] = true;
            if (comf(map, row, col)) {
                comfortable.add(new int[]{row, col});
            }
            for (int j = 0; j < 4; j++) {
                int newRow = row + dirRow[j];
                int newCol = col + dirCol[j];
                if (map[newRow][newCol]) {
                    if (comf(map, newRow, newCol)) {
                        comfortable.add(new int[]{newRow, newCol});
                    }
                }
            }

            pw.println(findAns(comfortable, map));
        }

        pw.close();
    }

    private static boolean comf(boolean[][] map, int row, int col) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (map[newRow][newCol]) {
                count++;
            }
        }
        return count == 3;
    }


    private static Point findMiss(boolean[][] map, int row, int col) {
        for (int i = 0; i < 4; i++) {
            int newRow = row + dirRow[i];
            int newCol = col + dirCol[i];
            if (!map[newRow][newCol]) {
                return new Point(newRow, newCol);
            }
        }
        return new Point(-1, -1);
    }

    private static int findAns(ArrayList<int[]> comfortable, boolean[][] map) {
        HashSet<Point> needToAdd = new HashSet<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        boolean[][] map2 = new boolean[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, map2[i], 0, map[i].length);
        }

        for (int i = 0; i < comfortable.size(); i++) {
            int[] p = comfortable.get(i);
            if (!comf(map2, p[0], p[1])) {
                continue;
            }
            visited[p[0]][p[1]] = true;
            Point tmp = findMiss(map2, p[0], p[1]);
            needToAdd.add(tmp);
            map2[tmp.row][tmp.col] = true;
            if (comf(map2, tmp.row, tmp.col)) {
                comfortable.add(new int[]{tmp.row, tmp.col});
            }

            for (int j = 0; j < 4; j++) {
                int newRow = tmp.row + dirRow[j];
                int newCol = tmp.col + dirCol[j];
                if (map2[newRow][newCol] && !visited[newRow][newCol]) {
                    if (comf(map2, newRow, newCol)) {
                        comfortable.add(new int[]{newRow, newCol});
                    }
                }
            }
        }

        return needToAdd.size();
    }

    private static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (row != point.row) return false;
            return col == point.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
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
