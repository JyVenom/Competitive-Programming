import java.io.*;

public class BuildGates {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gates.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));

        int n = Integer.parseInt(br.readLine());
        int[] dirs = new int[n];
        String s = br.readLine();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'N') {
                dirs[i] = 0;
            }
            else if (c == 'E') {
                dirs[i] = 1;
            }
            else if (c == 'S') {
                dirs[i] = 2;
            }
            else if (c == 'W') {
                dirs[i] = 3;
            }
        }

        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        int[] cur = new int[2]; //row, col
        for (int i = 0; i < n; i++) {
            if (dirs[i] == 0) {
                cur[0]++;
            }
            else if (dirs[i] == 1) {
                cur[1]++;
            }
            else if (dirs[i] == 2) {
                cur[0]--;
            }
            else if (dirs[i] == 3) {
                cur[1]--;
            }
            minX = Math.min(minX, cur[1]);
            maxX = Math.max(maxX, cur[1]);
            minY = Math.min(minY, cur[0]);
            maxY = Math.max(maxY, cur[0]);
        }
        int totX = maxX - minX + 2;
        int totY = maxY - minY + 2;
        int difX = -1 * minX + 1;
        int difY = -1 * minY + 1;
        boolean[][][] fence = new boolean[totY][totX][4];
        cur = new int[2]; //row, col
        cur[0] += difY;
        cur[1] += difX;
        for (int i = 0; i < n; i++) {
            if (dirs[i] == 0) {
                fence[cur[0]][cur[1]][3] = true;
                fence[cur[0]][cur[1] - 1][1] = true;
                cur[0]++;
            } else if (dirs[i] == 1) {
                fence[cur[0]][cur[1]][2] = true;
                fence[cur[0] - 1][cur[1]][0] = true;
                cur[1]++;
            } else if (dirs[i] == 2) {
                cur[0]--;
                fence[cur[0]][cur[1]][3] = true;
                fence[cur[0]][cur[1] - 1][1] = true;
            } else if (dirs[i] == 3) {
                cur[1]--;
                fence[cur[0]][cur[1]][2] = true;
                fence[cur[0] - 1][cur[1]][0] = true;
            }
        }
        boolean[][] visited = new boolean[totY][totX];
        int comp = 0;
        totX--;
        totY--;
        for (int i = 0; i < totY; i++) {
            for (int j = 0; j < totX; j++) {
                if (visited[i][j]) {
                    continue;
                }

                comp++;
                dfs(visited, fence, totX, totY, i, j);
            }
        }

        pw.println(comp - 1);
        pw.close();
    }

    private static void dfs (boolean[][] visited, boolean[][][] fence, int totX, int totY, int row, int col) {
        if (visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        if (row < totY && !fence[row][col][0]) {
            dfs(visited, fence, totX, totY, row + 1, col);
        }
        if (col < totX && !fence[row][col][1]) {
            dfs(visited, fence, totX, totY, row, col + 1);
        }
        if (row > 0 && !fence[row][col][2]) {
            dfs(visited, fence, totX, totY, row - 1, col);
        }
        if (col > 2 && !fence[row][col][3]) {
            dfs(visited, fence, totX, totY, row, col - 1);
        }
    }
}
