import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BullInAChinaShop2 {
    private static boolean[][] orig;
    private static boolean[][][] pieces;
    private static int size;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("bcs.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("bcs.out")); //or what it calls for ("promote.out

        String info = sc.nextLine();
        size = Integer.parseInt(info.substring(0, info.indexOf(' ')));
        int numPieces = Integer.parseInt(info.substring(info.indexOf(' ') + 1));
        orig = read(sc, size);
        pieces = new boolean[numPieces][size][size];

        for (int i = 0; i < numPieces; i++){
            pieces[i] = read(sc, size);
        }

        for(int i = 0; i < numPieces; i++){ //to match/combine every possible pair. i = piece 1 index
            for(int j = i + 1; j < numPieces; j++){ //to match/combine every possible pair. j = piece 2 index
                if(work(i, j)) {
                    out.println((i + 1) + " " + (j + 1));
                }
            }
        }
        out.close();
    }

    private static boolean get(boolean[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y];
    }

    private static boolean[][] read(Scanner sc, int size){
        boolean[][] grid = new boolean[size][size];
        for(int i = 0; i < size; i++) {
            String s = sc.nextLine();
            for(int j = 0; j < size; j++) {
                grid[i][j] = s.charAt(j) == '#';
            }
        }
        return grid;
    }

    private static boolean work (int i, int j){
        for(int ix = -size + 1; ix <= size-1; ix++){ //x position of i
            for(int iy = -size + 1; iy <= size - 1; iy++){ //y position of i
                for(int jx = -size + 1; jx <= size - 1; jx++){ //x position of j
                    for(int jy = -size + 1; jy <= size - 1; jy++){ //y position of j
                        boolean good = true;
                        for(int x = 0; good && x < size; x++) {
                            for(int y = 0; good && y < size; y++) {
                                boolean iValid = get(pieces[i], ix + x, iy + y);
                                boolean jValid = get(pieces[j], jx + x, jy + y);
                                if(iValid && jValid) {
                                    good = false;
                                }
                                if(orig[x][y] != (iValid || jValid)) {
                                    good = false;
                                }
                            }
                        }
                        if (good){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}