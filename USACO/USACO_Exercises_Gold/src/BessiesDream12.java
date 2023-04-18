//n * m + e * log (v)
//v < 1,000,000
//e < 4v == 4,000,000
//28,000,000
//dad says not best alg
//it worked but could be better

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BessiesDream12 {
    static int[][] tile;
    static int[][][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int n, m;

    static PriorityQueue<Pair<Integer, Pair<Integer, Pair<Integer, Integer>>>> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dream.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = new PriorityQueue<>();

        tile = new int[n][m];
        visited = new int[n][m][2];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                int color = Integer.parseInt(st.nextToken());
                tile[i][j] = color;
                visited[i][j][0] = visited[i][j][1] = 100000000;
            }
        }
        q.add(fromInts(0, 0, 0, 0));

        while (q.size() > 0) {
            Pair<Integer, Pair<Integer, Pair<Integer, Integer>>> front = q.remove();
            int dist = front.left;
            int row = front.right.left;
            int col = front.right.right.left;
            int isSmelly = front.right.right.right;

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
                    nSmelly = 0;
                    int[] temp = findEnd(nx, ny, dir, nd);
                    nx = temp[0];
                    ny = temp[1];
                    nd = temp[2];
//                    int nnx = nx + dir[0];
//                    int nny = ny + dir[1];
//                    while (isPathable2(nnx, nny) && tile[ny][nx] == 4) {
//                        nx = nnx;
//                        ny = nny;
//                        nd++;
//
//                        nnx += dir[0];
//                        nny += dir[1];
//                    }
                } else if (tile[ny][nx] == 2) {
                    nSmelly = 1;
                }
                if (visited[ny][nx][nSmelly] <= nd) continue;
                q.add(fromInts(nd, nx, ny, nSmelly));
            }
        }
        pw.println("-1");

        pw.close();
    }

    public static int[] findEnd (int row, int col, int[] dir, int nd) {
        int nx = row;
        int ny = col;
        if (Arrays.equals(dir, new int[]{-1, 0})) {
            for (int i = row - 1; i >= 0; i--) {
                if (tile[i][col] == 4) {
                    nd++;
                    continue;
                }
                if (tile[i][col] == 0 || tile[i][col] == 3) {
                    nx = i + 1;
                }
            }
        }
        else if (Arrays.equals(dir, new int[]{1, 0})) {
            for (int i = row + 1; i < tile.length; i++) {
                if (tile[i][col] == 4) {
                    nd++;
                    continue;
                }
                if (tile[i][col] == 0 || tile[i][col] == 3) {
                    nx = i - 1;
                }
            }
        }
        else if (Arrays.equals(dir, new int[]{0, -1})) {
            for (int i = col - 1; i >= 0; i--) {
                if (tile[row][i] == 4) {
                    nd++;
                    continue;
                }
                if (tile[i][col] == 0 || tile[i][col] == 3) {
                    ny = i + 1;
                }
            }
        }
        else if (Arrays.equals(dir, new int[]{0, 1})) {
            for (int i = col + 1; i < tile[0].length; i++) {
                if (tile[row][i] == 4) {
                    nd++;
                    continue;
                }
                if (tile[i][col] == 0 || tile[i][col] == 3) {
                    ny = i - 1;
                }
            }
        }

        return new int[]{nx, ny, nd};
    }

    public static boolean isPathable(int x, int y, int smellsNice) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (tile[y][x] == 0) return false;
        if (tile[y][x] == 3) return (smellsNice > 0);

        return true;
    }

//    public static boolean isPathable2(int x, int y) {
//        if (x < 0 || x >= m || y < 0 || y >= n) return false;
//        if (tile[y][x] == 0) return false;
//        return tile[y][x] != 3;
//    }

    public static Pair<Integer, Pair<Integer, Pair<Integer, Integer>>> fromInts(int a, int b, int c, int d) {
        Pair<Integer, Integer> p1 = new Pair<>(c, d);
        Pair<Integer, Pair<Integer, Integer>> p2 = new Pair<>(b, p1);
        return new Pair<>(a, p2);
    }

    static class Pair<L extends Comparable<L>, R> implements Comparable<Pair<L, R>> {
        public L left;
        public R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public int compareTo(Pair<L, R> other) {
            return this.left.compareTo(other.left);
        }
    }
}

