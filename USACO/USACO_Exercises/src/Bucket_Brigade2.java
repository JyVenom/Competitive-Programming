import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bucket_Brigade2 {
    public static void main(String[] args)  throws IOException {
        Scanner sc = new Scanner(new File("buckets.in"));
        PrintWriter out = new PrintWriter(new File("buckets.out"));

        char[][] map = new char[10][10];
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        for (int i = 0; i < 10; i++){
            String line = sc.nextLine();
            for (int j = 0; j < 10; j++){
                map[i][j] = line.charAt(j);
                if (line.charAt(j) == 'L'){
                    startX = j;
                    startY = i;
                }
                else if (line.charAt(j) == 'B'){
                    endX = j;
                    endY = i;
                }
            }
        }

        int spaces = 0;
        int currentSpaceX = startX;
        int currentSpaceY = startY;
        while (currentSpaceX < endX){
            while (currentSpaceY < endY){
                if (currentSpaceX < endX) {
                    if (map[currentSpaceY][currentSpaceX + 1] != 'R') {
                        currentSpaceX++;
                        spaces++;
                    }
                }
                if (map[currentSpaceY + 1][currentSpaceX] != 'R'){
                    currentSpaceY++;
                    spaces++;
                }
            }
            while (currentSpaceY > endY){
                if (currentSpaceX < endX) {
                    if (map[currentSpaceY][currentSpaceX + 1] != 'R') {
                        currentSpaceX++;
                        spaces++;
                    }
                }
                if (map[currentSpaceY - 1][currentSpaceX] != 'R'){
                    currentSpaceY--;
                    spaces++;
                }
            }
        }
        while (currentSpaceX > endX){
            while (currentSpaceY < endY){
                if (currentSpaceX > endX) {
                    if (map[currentSpaceY][currentSpaceX - 1] != 'R') {
                        currentSpaceX--;
                        spaces++;
                    }
                }
                if (map[currentSpaceY + 1][currentSpaceX] != 'R'){
                    currentSpaceY++;
                    spaces++;
                }
            }
            while (currentSpaceY > endY){
                if (currentSpaceX > endX) {
                    if (map[currentSpaceY][currentSpaceX - 1] != 'R') {
                        currentSpaceX--;
                        spaces++;
                    }
                }
                if (map[currentSpaceY - 1][currentSpaceX] != 'R'){
                    currentSpaceY--;
                    spaces++;
                }
            }
        }

        out.println(spaces - 1);
        out.close();
    }
}
