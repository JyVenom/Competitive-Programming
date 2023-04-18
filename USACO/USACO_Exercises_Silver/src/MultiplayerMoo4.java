import java.io.*;
import java.util.ArrayList;

public class MultiplayerMoo4 {
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

        ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();
        ArrayList<ArrayList<Integer>> locations = new ArrayList<>();
        boolean[][] empty = new boolean[n][n];
        visited = new boolean[n][n];
        boolean contains;
        int twoMax = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                int size = 0;
                if (grid[i][j] != grid[i][j + 1]) {
                    long start = System.nanoTime(); //jerry
                    count2++; //jerry
                    contains = false;
                    int pairIndex = 0;
                    for (ArrayList<Integer> pair : pairs) {
                        if (pair.contains(grid[i][j]) && pair.contains(grid[i][j + 1])) {
                            pairIndex = pairs.indexOf(pair);
                            contains = true;
                            break;
                        }
                    }
                    if (contains) {
                        if (locations.get(pairIndex).indexOf(i) != -1 && locations.get(pairIndex).indexOf(j) != -1) {
                            if (!(locations.get(pairIndex).indexOf(i) % 2 == 0 && locations.get(pairIndex).indexOf(j) % 2 == 1)) {
                                total += System.nanoTime() - start; //jerry
                                size = twoRegion(i, j, grid[i][j], grid[i][j + 1], grid, visited, size, locations.get(pairIndex));
                                twoMax = Math.max(twoMax, size);
                                for (int k = 0; k < n; k++) {
                                    System.arraycopy(empty[k], 0, visited[k], 0, n);
                                }
                            }
                        }
                    } else {
                        total += System.nanoTime() - start; //jerry
                        pairs.add(new ArrayList<>());
                        pairs.get(pairs.size() - 1).add(grid[i][j]);
                        pairs.get(pairs.size() - 1).add(grid[i][j + 1]);
                        locations.add(new ArrayList<>());
                        size = twoRegion(i, j, grid[i][j], grid[i][j + 1], grid, visited, size, locations.get(locations.size() - 1));
                        twoMax = Math.max(twoMax, size);
                        for (int k = 0; k < n; k++) {
                            System.arraycopy(empty[k], 0, visited[k], 0, n);
                        }
                    }
                    size = 0;
                }

                if (grid[i + 1][j] != grid[i][j]) {
                    long start = System.nanoTime(); //jerry
                    count2++; //jerry
                    contains = false;
                    int pairIndex = 0;
                    for (ArrayList<Integer> pair : pairs) {
                        if (pair.contains(grid[i + 1][j]) && pair.contains(grid[i][j])) {
                            pairIndex = pairs.indexOf(pair);
                            contains = true;
                            break;
                        }
                    }
                    if (contains) {
                        if (locations.get(pairIndex).indexOf(i) != -1 && locations.get(pairIndex).indexOf(j) != -1) {
                            if (!(locations.get(pairIndex).indexOf(i) % 2 == 0 && locations.get(pairIndex).indexOf(j) % 2 == 1)) {
                                total += System.nanoTime() - start; //jerry
                                size = twoRegion(i, j, grid[i][j], grid[i][j + 1], grid, visited, size, locations.get(pairIndex));
                                twoMax = Math.max(twoMax, size);
                                for (int k = 0; k < n; k++) {
                                    System.arraycopy(empty[k], 0, visited[k], 0, n);
                                }
                            }
                        }
                    } else {
                        total += System.nanoTime() - start; //jerry
                        pairs.add(new ArrayList<>());
                        pairs.get(pairs.size() - 1).add(grid[i + 1][j]);
                        pairs.get(pairs.size() - 1).add(grid[i][j]);
                        locations.add(new ArrayList<>());
                        size = twoRegion(i, j, grid[i + 1][j], grid[i][j], grid, visited, size, locations.get(locations.size() - 1));
                        twoMax = Math.max(twoMax, size);
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

    private static int twoRegion(int i, int j, int valueO, int valueT, int[][] grid, boolean[][] visited, int size, ArrayList<Integer> locations) {
        count++; //jerry
        visited[i][j] = true;
        locations.add(i);
        locations.add(j);
        size++;
        if (i != 0) {
            if ((grid[i - 1][j] == valueO || grid[i - 1][j] == valueT) && !visited[i - 1][j]) {
                size = twoRegion(i - 1, j, valueO, valueT, grid, visited, size, locations);
            }
        }
        if (j != 0) {
            if ((grid[i][j - 1] == valueO || grid[i][j - 1] == valueT) && !visited[i][j - 1]) {
                size = twoRegion(i, j - 1, valueO, valueT, grid, visited, size, locations);
            }
        }
        if (i != grid.length - 1 && !visited[i + 1][j]) {
            if ((grid[i + 1][j] == valueO || grid[i + 1][j] == valueT) && !visited[i + 1][j]) {
                size = twoRegion(i + 1, j, valueO, valueT, grid, visited, size, locations);
            }
        }
        if (j != grid[i].length - 1) {
            if ((grid[i][j + 1] == valueO || grid[i][j + 1] == valueT) && !visited[i][j + 1]) {
                size = twoRegion(i, j + 1, valueO, valueT, grid, visited, size, locations);
            }
        }
        return size;
    }
}
