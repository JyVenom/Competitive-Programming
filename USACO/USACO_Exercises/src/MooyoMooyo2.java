import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MooyoMooyo2 {
    private static int rows;
    private static int[][] region;
    private static int[][] board;
    private static int[] regSizes = new int[1001];
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("mooyomooyo.in"));
        PrintWriter out = new PrintWriter(new File("mooyomooyo.out"));

        String line = sc.nextLine();
        rows = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int minSize = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        board = new int[rows][10];
        region = new int[rows][10];

        for (int i = 0; i < rows; i++){
            line = sc.nextLine();
            for (int j = 0; j < 10; j++){
                board[i][j] = Integer.parseInt(line.charAt(j) + "");
            }
        }

        boolean progress = true;
        while (progress){
            int r = 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 10; j++) {
                    region[i][j] = 0;
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board[i][j] != 0 && region[i][j] == 0) {
                        findRegion(i, j, r++, board[i][j]);
                    }
                }
            }
            progress = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board[i][j] != 0 && regSizes[region[i][j]] >= minSize) {
                        board[i][j] = 0;
                        progress = true;
                    }
                }
            }
            fix(board);
            while (r != 0) {
                regSizes[r--] = 0;
            }
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < 10; j++){
                out.print(board[i][j]);
            }
            out.println();
        }
        out.close();
    }
    
    private static void fix (int[][] board){
        for (int i = board.length - 2; i >= 0; i--){ // -2 instead of -1 because bottom row cant be moved lower
            for (int j = 0; j < 10; j++){
                if (board[i][j] != 0){
                    int tempRow = i;
                    while (board[tempRow + 1][j] == 0){
                        board[tempRow + 1][j] = board[tempRow][j];
                        board[tempRow][j] = 0;
                        tempRow++;
                        if (tempRow + 1 == board.length){
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void findRegion(int i, int j, int r, int c) {
        if (i < 0 || i >= rows || j < 0 || j>9 || board[i][j] != c || region[i][j] != 0) {
            return;
        }
        region[i][j] = r;
        regSizes[r]++;
        findRegion(i - 1, j, r, c);
        findRegion(i + 1, j, r, c);
        findRegion(i, j - 1, r, c);
        findRegion(i, j + 1, r, c);
    }
}
