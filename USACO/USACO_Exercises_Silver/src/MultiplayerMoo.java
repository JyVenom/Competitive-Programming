import java.io.*;

public class MultiplayerMoo {
    private static int count = 0; //jerry

    public static void main(String[] args) throws IOException {
        long ps = System.currentTimeMillis(); //jerry
        long total = 0; //jerry
        int count2 = 0; //jerry

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

        boolean[][] empty = new boolean[n][n];
        visited = new boolean[n][n];
        int twoMax = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                int size = 0;
//                long start = System.nanoTime(); //jerry
                if (grid[i][j] != grid[i][j + 1]) {
                    size = twoRegion(i, j, grid[i][j], grid[i][j + 1], grid, visited, size);
                    twoMax = Math.max(twoMax, size);

                    for (int k = 0; k < n; k++) {
                        System.arraycopy(empty[k], 0, visited[k], 0, n);
                    }
                    size = 0;
                }
                if (grid[i + 1][j] != grid[i][j]) {
                    size = twoRegion(i, j, grid[i + 1][j], grid[i][j], grid, visited, size);
                    twoMax = Math.max(twoMax, size);
                    for (int k = 0; k < n; k++) {
                        System.arraycopy(empty[k], 0, visited[k], 0, n);
                    }
                }
//                total += System.nanoTime() - start; //jerry
//                count2++; //jerry
            }
        }

        out.println(max);
        out.println(twoMax);
        out.close();
        System.out.println(System.currentTimeMillis() - ps); //jerry
        System.out.println(total / count2); //jerry
        System.out.println(count); //jerry
        System.out.println(count2); //jerry
    }

    private static int region(int i, int j, int value, int[][] grid, boolean[][] visited, int size) {
        count++; //jerry
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

    private static int twoRegion(int i, int j, int valueO, int valueT, int[][] grid, boolean[][] visited, int size) {
        count++; //jerry
        visited[i][j] = true;
        size++;
        if (i != 0) {
            if ((grid[i - 1][j] == valueO || grid[i - 1][j] == valueT) && !visited[i - 1][j]) {
                size = twoRegion(i - 1, j, valueO, valueT, grid, visited, size);
            }
        }
        if (j != 0) {
            if ((grid[i][j - 1] == valueO || grid[i][j - 1] == valueT) && !visited[i][j - 1]) {
                size = twoRegion(i, j - 1, valueO, valueT, grid, visited, size);
            }
        }
        if (i != grid.length - 1 && !visited[i + 1][j]) {
            if ((grid[i + 1][j] == valueO || grid[i + 1][j] == valueT) && !visited[i + 1][j]) {
                size = twoRegion(i + 1, j, valueO, valueT, grid, visited, size);
            }
        }
        if (j != grid[i].length - 1) {
            if ((grid[i][j + 1] == valueO || grid[i][j + 1] == valueT) && !visited[i][j + 1]) {
                size = twoRegion(i, j + 1, valueO, valueT, grid, visited, size);
            }
        }
        return size;
    }
}
