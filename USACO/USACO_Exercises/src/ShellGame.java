import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ShellGame {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("shell.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("shell.out")); //or what it calls for ("promote.out")

        int lines = Integer.parseInt(sc.nextLine());
        int[] currentPositions = {1,0,0}; //1 signifies pebble, 0 signifies empty shell
        int[][] data = new int[lines][3];

        String line;
        for (int i =0; i < lines; i++){
            line = sc.nextLine();
            data[i][0] = Integer.parseInt(line.substring(0, 1));
            data[i][1] = Integer.parseInt(line.substring(2, 3));
            data[i][2] = Integer.parseInt(line.substring(4));
        }
        int[] counts = {0,0,0};
        for (int i =0; i < lines; i++) {
            int temp = currentPositions[data[i][0] - 1];
            currentPositions[data[i][0] - 1] = currentPositions[data[i][1] - 1];
            currentPositions[data[i][1] - 1] = temp;
            if (currentPositions[data[i][2] - 1] == 1){
                counts[0]++;
            }
        }

        currentPositions[0] = 0;
        currentPositions[1] = 1;
        currentPositions[2] = 0;
        for (int i =0; i < lines; i++) {
            int temp = currentPositions[data[i][0] - 1];
            currentPositions[data[i][0] - 1] = currentPositions[data[i][1] - 1];
            currentPositions[data[i][1] - 1] = temp;
            if (currentPositions[data[i][2] - 1] == 1){
                counts[1]++;
            }
        }

        currentPositions[0] = 0;
        currentPositions[1] = 0;
        currentPositions[2] = 1;
        for (int i =0; i < lines; i++) {
            int temp = currentPositions[data[i][0] - 1];
            currentPositions[data[i][0] - 1] = currentPositions[data[i][1] - 1];
            currentPositions[data[i][1] - 1] = temp;
            if (currentPositions[data[i][2] - 1] == 1){
                counts[2]++;
            }
        }

        out.println(Math.max(counts[0], Math.max(counts[1], counts[2])));
        out.close();
    }
    }
