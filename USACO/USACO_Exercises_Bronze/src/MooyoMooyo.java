import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MooyoMooyo {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("mooyomooyo.in"));
        PrintWriter out = new PrintWriter(new File("mooyomooyo.out"));

        String line = sc.nextLine();
        int rows = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int minSize = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        int[][] board = new int[rows][10];

        for (int i = 0; i < rows; i++){
            line = sc.nextLine();
            for (int j = 0; j < 10; j++){
                board[i][j] = Integer.parseInt(line.charAt(j) + "");
//                board[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        ArrayList<int[]> remove = new ArrayList<>();
        int[] temp = new int[2];
        while (!good(board)){
            for (int i = 0; i < board.length; i++) {
                int[] currentRow = board[i]; //check for horizontal connected regions
                for (int j = 0; j < 9; j++) {
                    if (currentRow[j] == currentRow[j + 1]) {
                        temp[0] = i;
                        temp[1] = j;
                        remove.add(temp);
                    }
                }
            }
            for (int i = 0; i < 10; i++){ //check for vertical connected regions
                for (int j = 0; j < board.length - 1; j++){
                    if (board[j][i] == board[j + 1][i]){
                        temp[0] = j;
                        temp[1] = i;
                        remove.add(temp);
                    }
                }
            }
            for (int[] pair : remove) {
                temp = pair;
                board[temp[0]][temp[1]] = 0;
            }
            fix(board);
        }
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < 10; j++){
                out.print(board[i][j]);
            }
            out.println();
        }
        out.close();
    }

    private static boolean good (int[][] board){
        for (int[] currentRow : board) { //check for horizontal connected regions
            for (int j = 0; j < 9; j++) {
                if (currentRow[j] == currentRow[j + 1]) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 10; i++){ //check for vertical connected regions
            for (int j = 0; j < board.length - 1; j++){
                if (board[j][i] == board[j + 1][i]){
                    return false;
                }
            }
        }
        return true;
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
}
