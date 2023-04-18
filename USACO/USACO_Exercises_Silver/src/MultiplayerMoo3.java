import java.io.*;
import java.util.ArrayList;

public class MultiplayerMoo3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("multimoo.in"));
        PrintWriter out = new PrintWriter(new File("multimoo.out"));

        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        int temp;
        String line;
        for (int i = 0; i < n; i++) {
            temp = 0;
            line = br.readLine();
            for (int j = 0; j < n - 1; j++) {
                grid[i][j] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
                temp = line.indexOf(' ', temp) + 1;
            }
            grid[i][n - 1] = Integer.parseInt(line.substring(temp));
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    int size = 0;
                    size = region(i, j, grid[i][j], grid, visited, size);
                    max = Math.max(max, size);
                }
            }
        }

        ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();
        boolean[][] empty = new boolean[n][n];
        visited = new boolean[n][n];
        boolean good;
        int twoMax = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                int size = 0;
                if (grid[i][j] != grid[i][j + 1]) {
                    good = true;
                    for (ArrayList<Integer> pair : pairs) {
                        if (pair.contains(grid[i][j]) && pair.contains(grid[i][j + 1])) {
                            good = false;
                            break;
                        }
                    }
                    if (good) {
                        for (int k = 0; k < n - 1; k++){
                            for (int l = 0; l < n - 1; l++){
                                if (!visited[k][l]) {
                                    if (grid[k][l] == grid[i][j] && grid[k][l + 1] == grid[i][j + 1]){
                                        size = twoRegion(k, l, grid[i][j], grid[i][j + 1], grid, visited, size, 3);
                                    }
                                    else if (grid[k][l] == grid[i][j + 1] && grid[k][l + 1] == grid[i][j]){
                                        size = twoRegion(k, l, grid[i][j], grid[i][j + 1], grid, visited, size, 3);
                                    }
                                    else if (grid[k + 1][l] == grid[i][j] && grid[k][l] == grid[i][j + 1]){
                                        size = twoRegion(k, l, grid[i][j], grid[i][j + 1], grid, visited, size, 1);
                                    }
                                    else if (grid[k + 1][l] == grid[i][j + 1] && grid[k][l] == grid[i][j]){
                                        size = twoRegion(k, l, grid[i][j], grid[i][j + 1], grid, visited, size, 1);
                                    }
                                    twoMax = Math.max(twoMax, size);
                                }
                            }
                        }
                        pairs.add(new ArrayList<>());
                        pairs.get(pairs.size() - 1).add(grid[i][j]);
                        pairs.get(pairs.size() - 1).add(grid[i][j + 1]);
                        for (int k = 0; k < n; k++) {
                            System.arraycopy(empty[k], 0, visited[k], 0, n);
                        }
                        size = 0;
                    }
                }
                if (grid[i + 1][j] != grid[i][j]) {
                    good = true;
                    for (ArrayList<Integer> pair : pairs) {
                        if (pair.contains(grid[i + 1][j]) && pair.contains(grid[i][j])) {
                            good = false;
                            break;
                        }
                    }
                    if (good) {
                        for (int k = 0; k < n - 1; k++){
                            for (int l = 0; l < n - 1; l++){
                                if (!visited[k][l]) {
                                    if (grid[k][l] == grid[i + 1][j] && grid[k][l + 1] == grid[i][j]){
                                        size = twoRegion(k, l, grid[i + 1][j], grid[i][j], grid, visited, size, 3);
                                    }
                                    else if (grid[k][l] == grid[i][j] && grid[k][l + 1] == grid[i + 1][j]){
                                        size = twoRegion(k, l, grid[i + 1][j], grid[i][j], grid, visited, size, 3);
                                    }
                                    else if (grid[k + 1][l] == grid[i + 1][j] && grid[k][l] == grid[i][j]){
                                        size = twoRegion(k, l, grid[i + 1][j], grid[i][j], grid, visited, size, 1);
                                    }
                                    else if (grid[k + 1][l] == grid[i][j] && grid[k][l] == grid[i + 1][j]){
                                        size = twoRegion(k, l, grid[i + 1][j], grid[i][j], grid, visited, size, 1);
                                    }
                                    twoMax = Math.max(twoMax, size);
                                }
                            }
                        }
                        pairs.add(new ArrayList<>());
                        pairs.get(pairs.size() - 1).add(grid[i + 1][j]);
                        pairs.get(pairs.size() - 1).add(grid[i][j]);
                        for (int k = 0; k < n; k++) {
                            System.arraycopy(empty[k], 0, visited[k], 0, n);
                        }
                    }
                }
            }
        }

        out.println(max);
        out.println(twoMax);
        out.close();
    }

    private static int region(int i, int j, int value, int[][] grid, boolean[][] visited, int size) {
        visited[i][j] = true;
        size++;
        if (i != 0) {
            if (grid[i - 1][j] == value && !visited[i - 1][j]) {
                size = region(i - 1, j, value, grid, visited, size);
            }
        }
        if (j != 0) {
            if (grid[i][j - 1] == value && !visited[i][j - 1]) {
                size = region(i, j - 1, value, grid, visited, size);
            }
        }
        if (i != grid.length - 1 && !visited[i + 1][j]) {
            if (grid[i + 1][j] == value && !visited[i + 1][j]) {
                size = region(i + 1, j, value, grid, visited, size);
            }
        }
        if (j != grid[i].length - 1) {
            if (grid[i][j + 1] == value && !visited[i][j + 1]) {
                size = region(i, j + 1, value, grid, visited, size);
            }
        }
        return size;
    }

    private static int twoRegion(int i, int j, int valueO, int valueT, int[][] grid, boolean[][] visited, int size, int d) { //d for direction, 0 = up, 1 = down, 2 = left, 3 = right
        visited[i][j] = true;
        size++;
        if (i != 0) {
            if ((grid[i - 1][j] == valueO || grid[i - 1][j] == valueT) && !visited[i - 1][j]) {
                size = twoRegion(i - 1, j, valueO, valueT, grid, visited, size, 0);
            }
        }
        if (j != 0) {
            if ((grid[i][j - 1] == valueO || grid[i][j - 1] == valueT) && !visited[i][j - 1]) {
                size = twoRegion(i, j - 1, valueO, valueT, grid, visited, size, 2);
            }
        }
        if (i != grid.length - 1 && !visited[i + 1][j]) {
            if ((grid[i + 1][j] == valueO || grid[i + 1][j] == valueT) && !visited[i + 1][j]) {
                size = twoRegion(i + 1, j, valueO, valueT, grid, visited, size, 1);
            }
        }
        if (j != grid[i].length - 1) {
            if ((grid[i][j + 1] == valueO || grid[i][j + 1] == valueT) && !visited[i][j + 1]) {
                size = twoRegion(i, j + 1, valueO, valueT, grid, visited, size, 3);
            }
        }
        return size;
    }
}
