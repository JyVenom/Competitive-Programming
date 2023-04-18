/*
ID: jerryya2
LANG: JAVA
TASK: starry
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class starry {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("starry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("starry.out")));

        int w = Integer.parseInt(br.readLine());
        int h = Integer.parseInt(br.readLine());
        int[][] sky = new int[h][w];
        for (int i = 0; i < h; i++) {
            sky[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int comp = 0;
        int[][] clusters = new int[h][w];
        boolean[][] visited = new boolean[h][w];
        ArrayList<cluster> clusters2 = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (sky[i][j] == 1 && clusters[i][j] == 0) {
                    comp++;
                    cluster c = new cluster(i, j);
                    dfs(sky, clusters, visited, i, j, comp, c);
                    c.updateWH();
                    clusters2.add(c);
                }
            }
        }
        ArrayList<Integer> rem = new ArrayList<>();
        for (int i = 1; i <= comp; i++) {
            rem.add(i);
        }
        for (int i = 0; i < clusters2.size(); i++) {
            for (int j = i + 1; j < clusters2.size(); j++) {
                cluster a = clusters2.get(i);
                cluster b = clusters2.get(j);
                if (compareIfSimilar(sky, a, b)) {
                    if (clusters[a.sr][a.sc] == clusters[b.sr][b.sc]) {
                        continue;
                    }
                    if (clusters[a.sr][a.sc] < clusters[b.sr][b.sc]) {
                        int temp = clusters[a.sr][a.sc];
                        rem.remove((Integer) clusters[b.sr][b.sc]);
                        dfs(sky, clusters, new boolean[h][w], b.sr, b.sc, temp, b);
                    } else {
                        int temp = clusters[b.sr][b.sc];
                        rem.remove((Integer) clusters[a.sr][a.sc]);
                        dfs(sky, clusters, new boolean[h][w], a.sr, a.sc, temp, a);
                    }
                }
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (clusters[i][j] == 0) {
                    pw.print(0);
                } else {
                    pw.print((char) (Collections.binarySearch(rem, clusters[i][j]) + 'a'));
                }
            }
            pw.println();
        }
        pw.close();
    }

    private static void dfs(int[][] map, int[][] clusters, boolean[][] visited, int row, int col, int comp, cluster c) {
        clusters[row][col] = comp;
        visited[row][col] = true;
        c.updateTB(row);
        c.updateLR(col);

        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;

        if (row > 0) {
            up = true;
        }
        if (row < map.length - 1) {
            down = true;
        }
        if (col > 0) {
            left = true;
        }
        if (col < map[0].length - 1) {
            right = true;
        }

        if (up) {
            if (map[row - 1][col] == 1 && !visited[row - 1][col]) {
                dfs(map, clusters, visited, row - 1, col, comp, c);
            }
            if (right) {
                if (map[row - 1][col + 1] == 1 && !visited[row - 1][col + 1]) {
                    dfs(map, clusters, visited, row - 1, col + 1, comp, c);
                }
            }
        }
        if (right) {
            if (map[row][col + 1] == 1 && !visited[row][col + 1]) {
                dfs(map, clusters, visited, row, col + 1, comp, c);
            }
            if (down) {
                if (map[row + 1][col + 1] == 1 && !visited[row + 1][col + 1]) {
                    dfs(map, clusters, visited, row + 1, col + 1, comp, c);
                }
            }
        }
        if (down) {
            if (map[row + 1][col] == 1 && !visited[row + 1][col]) {
                dfs(map, clusters, visited, row + 1, col, comp, c);
            }
            if (left) {
                if (map[row + 1][col - 1] == 1 && !visited[row + 1][col - 1]) {
                    dfs(map, clusters, visited, row + 1, col - 1, comp, c);
                }
            }
        }
        if (left) {
            if (map[row][col - 1] == 1 && !visited[row][col - 1]) {
                dfs(map, clusters, visited, row, col - 1, comp, c);
            }
            if (up) {
                if (map[row - 1][col - 1] == 1 && !visited[row - 1][col - 1]) {
                    dfs(map, clusters, visited, row - 1, col - 1, comp, c);
                }
            }
        }
    }

    private static boolean compareIfSimilar(int[][] map, cluster a, cluster b) {
        boolean good = false;
        boolean good2 = false;
        if (a.w == b.w && a.h == b.h) {
            good = true;
        }
        if (a.h == b.w && a.w == b.h) {
            good2 = true;
        }
        if (!good && !good2) {
            return false;
        }

        int[][] A = new int[a.h][a.w];
        int[][] B;
        for (int i = 0; i < a.h; i++) {
            if (a.w >= 0) System.arraycopy(map[i + a.t], a.l, A[i], 0, a.w);
        }
//        if (good2 && !good) {
        if (good2) {
            B = new int[b.w][b.h];
            for (int i = 0; i < b.h; i++) {
                for (int j = 0; j < b.w; j++) {
                    B[j][i] = map[i + b.t][j + b.l];
                }
            }
        } else {
            B = new int[b.h][b.w];
            for (int i = 0; i < b.h; i++) {
                if (b.w >= 0) System.arraycopy(map[i + b.t], b.l, B[i], 0, b.w);
            }
        }

        if (checkIfSame(A, B)) {
            return true;
        }
        flipH(A);
        if (checkIfSame(A, B)) {
            return true;
        }
        flipV(A);
        if (checkIfSame(A, B)) {
            return true;
        }
        flipH(A);
        if (checkIfSame(A, B)) {
            return true;
        }
        if (good && good2) {
            flipV(A);
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < i; j++) {
                    int temp = B[i][j];
                    B[i][j] = B[j][i];
                    B[j][i] = temp;
                }
            }
            if (checkIfSame(A, B)) {
                return true;
            }
            flipH(A);
            if (checkIfSame(A, B)) {
                return true;
            }
            flipV(A);
            if (checkIfSame(A, B)) {
                return true;
            }
            flipH(A);
            return checkIfSame(A, B);
        }
        return false;
    }

    private static boolean checkIfSame(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void flipV(int[][] arr) {
        int half = arr.length / 2;
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                int temp = arr[i][j];
                int next = arr.length - i - 1;
                arr[i][j] = arr[next][j];
                arr[next][j] = temp;
            }
        }
    }

    private static void flipH(int[][] arr) {
        int half = arr[0].length / 2;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < half; j++) {
                int temp = arr[i][j];
                int next = arr[0].length - j - 1;
                if (next == -1) {
                    System.out.println();
                }
                arr[i][j] = arr[i][next];
                arr[i][next] = temp;
            }
        }
    }

    public static class cluster {
        int w, h;
        int sr, sc;
        int t = Integer.MAX_VALUE, b = 0, l = Integer.MAX_VALUE, r = 0;

        public cluster(int startRow, int startCol) {
            sr = startRow;
            sc = startCol;
        }

        private void updateTB(int n) {
            this.t = Math.min(t, n);
            this.b = Math.max(b, n);
        }

        private void updateLR(int n) {
            this.l = Math.min(l, n);
            this.r = Math.max(r, n);
        }

        private void updateWH() {
            this.w = r - l + 1;
            this.h = b - t + 1;
        }
    }
}
