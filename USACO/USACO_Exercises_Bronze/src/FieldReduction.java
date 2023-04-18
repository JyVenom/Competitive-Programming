import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FieldReduction {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("reduce.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("reduce.out")); //or what it calls for ("promote.out

        int numCows = sc.nextInt();
        int[][] cows = new int[numCows][2];

        for (int i = 0; i < numCows; i++){
            cows[i][0] = sc.nextInt();
            cows[i][1] = sc.nextInt();
        }

        int minArea = Integer.MAX_VALUE;
        for (int i = 0; i < numCows; i++){ //which to remove
            int minX = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxY = Integer.MIN_VALUE;
            for (int j = 0; j < numCows; j++){
                if (j == i){
                    continue;
                }
                minX = Math.min(minX, cows[j][0]);
                maxX = Math.max(maxX, cows[j][0]);
                minY = Math.min(minY, cows[j][1]);
                maxY = Math.max(maxY, cows[j][1]);
            }
            int area = (maxX - minX) * (maxY - minY);
            minArea = Math.min(minArea, area);
        }

        out.println(minArea);
        out.close();
    }
}
