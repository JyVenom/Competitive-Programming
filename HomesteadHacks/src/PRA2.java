import java.util.Scanner;

public class PRA2 {
    static int count = 0;
    static int x;
    static int y;
    static boolean r = false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < cases; i++) {
            String[] line2 = sc.nextLine().split(" ");
            int row = Integer.parseInt(line2[0]);
            int col = Integer.parseInt(line2[1]);
            char[][] map = new char[row][col];
            for (int j = 0; j < row; j++){
                String line = sc.nextLine();
                for (int k = 0; k < col; k++){
                    map[j][k] = line.charAt(k);
                }
            }
            for (int j = 0; j < row; j++){
                for (int k = 0; k < col; k++){
                    boolean[][] checked = new boolean[row][col];
                    x = j;
                    y = k;
                    count = 0;
                    r = false;
                    spread(j, k, map[j][k], map, checked);

                    if (r){
                        System.out.println("Yes");
                        break;
                    }
                }
                if (r){
                    break;
                }
            }
            if (!r){
                System.out.println("No");
            }
        }
    }

    private static void spread(int i, int j, char color, char[][] grid, boolean[][] visited) {
        count++;
//        System.out.println(i + " " + j + " " + count);
        if (i == x + 1 && j == y && count >= 4){
            r = true;
            return;
        }
        if (i == x - 1 && j == y && count >= 4){
            r = true;
            return;
        }
        if (i == x && j == y + 1 && count >= 4){
            r = true;
            return;
        }
        if (i == x && j == y - 1 && count >= 4){
            r = true;
            return;
        }
        visited[i][j] = true;
        if (i != 0) {
            if (grid[i - 1][j] == color && !visited[i - 1][j]) {
                spread(i - 1, j, color, grid, visited);
                count--;
            }
        }
        if (j != 0) {
            if (grid[i][j - 1] == color && !visited[i][j - 1]) {
                spread(i, j - 1, color, grid, visited);
                count--;
            }
        }
        if (i != grid.length - 1) {
            if (grid[i + 1][j] == color && !visited[i + 1][j]) {
                spread(i + 1, j, color, grid, visited);
                count--;
            }
        }
        if (j != grid[i].length - 1) {
            if (grid[i][j + 1] == color && !visited[i][j + 1]) {
                spread(i, j + 1, color, grid, visited);
                count--;
            }
        }
    }

//    private static void spread (int row, int col, char color, char[][] map, boolean[][] map2, boolean[][] checked){
//        if (row == x + 1 && col == y){
//            r = true;
//        }
//        map2[row][col] = true;
//        checked[row][col] = true;
//        if (col != cols - 1) {
//            if (map[row][col + 1] == color) {
//                if (!checked[row][col + 1]) {
//                    spread(row, col + 1, map[row][col + 1], map, map2, checked);
//                }
//            }
//        }if (col != 0) {
//            if (map[row][col - 1] == color) {
//                if (!checked[row][col - 1]) {
//                    spread(row, col - 1, map[row][col - 1], map, map2, checked);
//                }
//            }
//        }if (row != rows - 1) {
//            if (map[row + 1][col] == color) {
//                if (!checked[row + 1][col]) {
//                    spread(row + 1, col, map[row + 1][col], map, map2, checked);
//                }
//            }
//        }if (row != 0) {
//            if (map[row - 1][col] == color) {
//                if (!checked[row - 1][col]) {
//                    spread(row - 1, col, map[row - 1][col], map, map2, checked);
//                }
//            }
//        }
//    }
}
