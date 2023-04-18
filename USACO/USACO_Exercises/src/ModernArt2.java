import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ModernArt2 {
    private static int size;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("art.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("art.out")); //or what it calls for ("promote.out")
        
        size = Integer.parseInt(sc.nextLine());
        int[][] data = new int[size][size];
        ArrayList<Integer> colors = new ArrayList<>(1);

        for (int i = 0; i < size; i++){
            String line = sc.nextLine();
            for (int j = 0; j < size; j++){
                int value = Integer.parseInt(line.substring(j, j + 1));
                data[i][j] = value;
                if (!colors.contains(value)){
                    colors.add(value);
                }
            }
        }
        Collections.sort(colors);
        if (colors.contains(0)){
            colors.remove((Integer) 0);
        }

        int count = 0;
        for (int i = 0; i < colors.size(); i++) {
            boolean first = true;
            int color1 = colors.get(i);
            for (int color2 : colors) {
                if (color2 != color1 && onTopOf(color1, color2, data)) {
                    first = false;
                }
            }
            if (first){
                count++;
            }
        }
        out.println(count);
        out.close();
    }

    private static boolean onTopOf(int c1, int c2, int[][] data){ //if c1 is on top of of c2
        int top = size, bottom = 0, left = size, right = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (data[i][j] == c2) {
                    top = Math.min(top, i);
                    bottom = Math.max(bottom, i);
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
        }

        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                if (data[i][j] == c1){
                    return true;
                }
            }
        }
        return false;
    }
}