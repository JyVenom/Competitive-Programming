import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FieldReduction2 {
    static int x1, x2, x3, x4;
    static int y1, y2, y3, y4;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("reduce.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("reduce.out")); //or what it calls for ("promote.out
        int cows = sc.nextInt();
        x1 = Integer.MAX_VALUE; //smallest x value 1
        x2 = Integer.MAX_VALUE; //smallest x value 2
        x3 = 0; //largest x value 1
        x4 = 0; //largest x value 2
        y1 = Integer.MAX_VALUE; //smallest y value 1
        y2 = Integer.MAX_VALUE; //smallest y value 2
        y3 = 0; //largest y value 1
        y4 = 0; //largest y value 2
        int[] x = new int[cows];
        int[] y = new int[cows];
        // read in all the locations
        for (int i = 0; i < cows; i++){
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
            update(x[i], y[i]);
        }

        int ans = (x4-x1) * (y4-y1); //original area
        for(int i = 0; i < cows; i++) {
            // check for each point if we use the smallest/largest coordinate or the second-smallest/second-largest coordinate
            int xMin = x1;
            if(x[i] == xMin) {
                xMin = x2;
            }
            int xMax = x4;
            if(x[i] == xMax) {
                xMax = x3;
            }
            int yMin = y1;
            if(y[i] == yMin) {
                yMin = y2;
            }
            int yMax = y4;
            if(y[i] == yMax) {
                yMax = y3;
            }
            ans = Math.min(ans, (xMax - xMin) * (yMax - yMin)); // check if the new area is smaller
        }
        out.println(ans); // print the answer
        out.close();
    }

    // This function takes in a point and updates the two smallest and two largest x and y coordinates.
    public static void update(int x, int y) {
        if(x < x1) {
            x2 = x1;
            x1 = x;
        }
        else if(x < x2) {
            x2 = x;
        }
        if(x > x4) {
            x3 = x4;
            x4 = x;
        }
        else if(x > x3) {
            x3 = x;
        }

        if(y < y1) {
            y2 = y1;
            y1 = y;
        }
        else if(y < y2) {
            y2 = y;
        }
        if(y > y4) {
            y3 = y4;
            y4 = y;
        }
        else if(y > y3) {
            y3 = y;
        }
    }
}
