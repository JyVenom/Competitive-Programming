import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Convention2 {
    private static int lastRow = 0;
    private static ArrayList<Integer> extra = new ArrayList<>(); //ArrayList of all the buses that have empty spots
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

        if (spare > 0) {
            boolean finished = true;
            int prev = data.get(0);
            int last = 0;
            for (int i = 0; i < cows; i++){
                last = i;
                if (lastRow < times.length) {
                    int cur = data.get(i);
                    if (cur == prev) {
                        add(times, cur);
                    } else {
                        prev = cur;
                        addNext(times, cur);
                    }
                } else {
                    finished = false;
                    break;
                }
            }

            while (!finished){
                int row = findSmallest(times);
                times = remove(times, row);
                finished = true;
                for (int i = last; i < cows; i++){
                    last = i;
                    if (lastRow < times.length) {
                        int cur = data.get(i);
                        if (cur == prev) {
                            add(times, cur);
                        } else {
                            prev = cur;
                            addNext(times, cur);
                        }
                    } else {
                        finished = false;
                        break;
                    }
                }
            }
        }
        else {
            for (int i = 0; i < buses; i++) {
                for (int j = 0; j < size; j++) {
                    times[i][j] = data.get(i * size + j);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < buses; i++){
            max = Math.max(max, times[i][size - 1] - times[i][0]);
        }

        out.println(max);
        out.close();
    }

    private static void add (int[][] in, int elem){
        for (int i = 0; i < in.length; i++){
            for (int j = 0; j < in[0].length; j++){
                if (in[i][j] == 0){
                    in[i][j] = elem;
                    lastRow = i;
                    i = in.length - 1;
                    break;
                }
            }
        }
    }

    private static void addNext (int[][] in, int elem){
        lastRow = in.length;
        for (int i = 0; i < in.length; i++){
            for (int j = 0; j < in[0].length; j++){
                if (in[i][j] == 0){
                    if (j == 0){
                        in[i][j] = elem;
                        lastRow = i;
                        i = in.length - 1;
                        break;
                    }
                    else {
                        if (in[i + 1][0] == 0) {
                            in[i + 1][0] = elem;
                            extra.add(i);
                            lastRow = i + 1;
                            i = in.length - 1;
                            break;
                        }
                        else {
                            i++;
                            j = 0;
                        }
                    }
                }
            }
        }
    }

    private static int findSmallest (int[][] in){
        int min = Integer.MAX_VALUE;
        int minRow = extra.get(0);
        for (int i = 0; i < extra.size(); i++){
            int row = extra.get(i);
            int dist = in[row + 1][0] - in[row][0];
            if (dist < min){
                min = dist;
                minRow = i;
            }
        }
        return minRow; //row where in[minRow + 1][0] - in[minRow][0] is the smallest
    }

    private static int[][] remove (int[][] in, int row){
        int[][] out = new int[in.length][in[0].length];
        int prevRow = 0;
        int prevCol = 0;
        for (int i = 0; i <= row; i++){
            for (int j = 0; j < in[0].length; j++){
                prevRow = i;
                prevCol = j;
                if (i == row && in[i][j] == 0){
                    break;
                }
                out[i][j] = in[prevRow][prevCol];
            }
        }
        for (int i = row + 1; i < in.length; i++){
            for (int j = 0; j < in[0].length; j++){
                out[prevRow][prevCol] = in[i][j];
                prevCol++;
                if (prevCol == in[0].length){
                    prevCol = 0;
                    prevRow++;
                }
                if (i == in.length - 1 && j == in[0].length - 1){
                    lastRow = i;
                }
            }
        }
        return out;
    }
}
