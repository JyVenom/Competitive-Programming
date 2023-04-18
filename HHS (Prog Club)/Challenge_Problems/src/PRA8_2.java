import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class PRA8_2 {
    private static final int[] dirRow = new int[]{0, 1, 0, -1};
    private static final int[] dirCol = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
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
        visited[startRow][startCol] = true;
        while (!queue.isEmpty()) {
            state cur = queue.removeFirst();

            if (map[cur.row][cur.col] == 'B') {
                return cur.cost;
            }

            visited[cur.row][cur.col] = true;

            int newCost = cur.cost + 1;
            for (int i = 0; i < 4; i++) {
                int nextRow = cur.row + dirRow[i];
                int nextCol = cur.col + dirCol[i];
                if (pos(map, visited, nextRow, nextCol)) {
                    queue.addLast(new state(nextRow, nextCol, newCost));
                    visited[nextRow][nextCol] = true;
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
}
