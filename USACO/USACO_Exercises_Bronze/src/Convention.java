import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Convention {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("convention.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("convention.out")); //or what it calls for ("promote.out)

        int cows = sc.nextInt();
        int buses = sc.nextInt();
        int size = sc.nextInt();
        int spare = buses * size - cows;
        int[][] times = new int[buses][size];

        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < cows; i++){
            data.add(sc.nextInt());
        }
        Collections.sort(data);

        for (int i = 0; i < buses; i++){
            for (int j = 0; j < size; j++){
                if (i * size + j >= cows){
                    i = buses - 1;
                    break;
                }

                times[i][j] = data.get(i * size + j);
            }
        }

        int max = 0;
        for (int i = 0; i < buses; i++){
            max = Math.max(max, times[i][size - 1] - times[i][0]);
        }

        out.println(max);
        out.close();
    }

    private static int[][] shift (int[][] in, int row, int col){
        int[][] out = new int[in.length][in[0].length];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < in[0].length; j++){
                if (i == row - 1 && j == col){
                    break;
                }
                out[i][j] = in[i][j];
            }
        }


        return out;
    }
}
