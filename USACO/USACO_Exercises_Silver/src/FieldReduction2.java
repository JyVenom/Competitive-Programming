import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class FieldReduction2 {
    private static int x1, x2, x3, x4;
    private static int y1, y2, y3, y4;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));

        int n = Integer.parseInt(br.readLine());

        int[][] cows = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(cows, Comparator.comparingInt(arr -> arr[0]));

        int ans = -1;
        int[] used = new int[3];
        for (int r = 0; r < 3; r++) {
            x1 = Integer.MAX_VALUE; //smallest x value 1
            x2 = Integer.MAX_VALUE; //smallest x value 2
            x3 = 0; //largest x value 1
            x4 = 0; //largest x value 2
            y1 = Integer.MAX_VALUE; //smallest y value 1
            y2 = Integer.MAX_VALUE; //smallest y value 2
            y3 = 0; //largest y value 1
            y4 = 0; //largest y value 2
            for (int i = 0; i < n; i++) {
                boolean good = true;
                for (int j = 0; j < r; j++) {
                    if (i == used[j]) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    update(cows[i][0], cows[i][1]);
                }
            }
            int best = -1;
            ans = (x4 - x1) * (y4 - y1); //original area
            for (int i = 0; i < n; i++) {
                boolean good = true;
                for (int j = 0; j < r; j++) {
                    if (i == used[j]) {
                        good = false;
                        break;
                    }
                }
                if (!good) {
                    continue;
                }
                // check for each point if we use the smallest/largest coordinate or the second-smallest/second-largest coordinate
                good = false;
                int xMin = x1;
                if (cows[i][0] == xMin) {
                    good = true;
                    xMin = x2;
                }
                int xMax = x4;
                if (cows[i][0] == xMax) {
                    good = true;
                    xMax = x3;
                }
                int yMin = y1;
                if (cows[i][1] == yMin) {
                    good = true;
                    yMin = y2;
                }
                int yMax = y4;
                if (cows[i][1] == yMax) {
                    good = true;
                    yMax = y3;
                }
                if (good) {
                    int newAns = (xMax - xMin) * (yMax - yMin);
                    if (newAns < ans) { // check if the new area is smaller
                        ans = newAns;
                        best = i;
                    }
                }
            }
            used[r] = best;
        }

        pw.println(ans);
        pw.close();
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
