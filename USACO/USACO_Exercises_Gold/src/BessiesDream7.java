//e * log (v)
//v < 1,000,000
//e < 4v == 4,000,000
//28,000,000
//dad says not best alg
//it worked but could be better

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BessiesDream7 {
    static final int N = 1005;

    static int[][] tile;
    static int[][][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int n, m;

    static PriorityQueue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dream.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = new PriorityQueue<>();

        tile = new int[N][N];
        visited = new int[N][N][2];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                int color = Integer.parseInt(st.nextToken());
                tile[i][j] = color;
                visited[i][j][0] = visited[i][j][1] = 100000000;
            }
        }
        q.add(new int[]{0, 0, 0, 0});

        while (q.size() > 0) {
            int[] front = q.remove();
            int dist = front[0];
            int row = front[1];
            int col = front[2];
            int isSmelly = front[3];

            if (row == m - 1 && col == n - 1) {
                pw.println(dist);
                pw.close();
                return;
            }
            if (visited[col][row][isSmelly] != 100000000) {
                continue;
            }
            visited[col][row][isSmelly] = dist;

            for (int[] dir : dirs) {
                int nx = row + dir[0];
                int ny = col + dir[1];
                int nd = dist + 1;
                int nSmelly = isSmelly;

                if (!isPathable(nx, ny, isSmelly)) continue;

                if (tile[ny][nx] == 4) {
                    while (isPathable(nx + dir[0], ny + dir[1], isSmelly) &&
                            tile[ny][nx] == 4) {
                        nx += dir[0];
                        ny += dir[1];
                        nd++;
                        nSmelly = 0;
                    }
                }
                if (tile[ny][nx] == 2) {
                    nSmelly = 1;
                }
                if (visited[ny][nx][nSmelly] <= nd) continue;
                q.add(new int[]{nd, nx, ny, nSmelly});
            }
        }
        pw.println("-1");

        pw.close();
    }

    public static boolean isPathable(int x, int y, int smellsNice) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (tile[y][x] == 0) return false;
        if (tile[y][x] == 3) return (smellsNice > 0);

        return true;
    }
}

