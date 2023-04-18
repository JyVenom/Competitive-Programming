import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class FieldReduction {
    private static int[] x, y;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));

        int n = Integer.parseInt(br.readLine());

        x = new int[8];
        y = new int[8];
        x[0] = Integer.MAX_VALUE; //smallest x value 1
        x[1] = Integer.MAX_VALUE; //smallest x value 2
        x[2] = Integer.MAX_VALUE; //smallest x value 3
        x[3] = Integer.MAX_VALUE; //smallest x value 4
        y[0] = Integer.MAX_VALUE; //smallest y value 1
        y[1] = Integer.MAX_VALUE; //smallest y value 2
        y[2] = Integer.MAX_VALUE; //smallest y value 3
        y[3] = Integer.MAX_VALUE; //smallest y value 4
        int[][] cows = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
            update(cows[i][0], cows[i][1]);
        }
        Arrays.sort(cows, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(cows, Comparator.comparingInt(arr -> arr[0]));

        int ans = (x[4] - x[0]) * (y[4] - y[0]); //original area
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // check for each point if we use the smallest/largest coordinate or the second-smallest/second-largest coordinate
                    boolean good = false;
                    int xMin = x[0];
                    if (cows[i][0] == xMin) {
                        xMin = x[1];
                        if (cows[j][0] == xMin) {
                            xMin = x[2];
                            if (cows[k][0] == xMin) {
                                xMin = x[3];
                            }
                        }
                        else if (cows[k][0] == xMin) {
                            xMin = x[2];
                            if (cows[j][0] == xMin) {
                                xMin = x[3];
                            }
                        }
                        good = true;
                    }
                    else if (cows[j][0] == xMin) {
                        xMin = x[1];
                        if (cows[i][0] == xMin) {
                            xMin = x[2];
                            if (cows[k][0] == xMin) {
                                xMin = x[3];
                            }
                        }
                        else if (cows[k][0] == xMin) {
                            xMin = x[2];
                            if (cows[i][0] == xMin) {
                                xMin = x[3];
                            }
                        }
                        good = true;
                    }
                    else if (cows[k][0] == xMin) {
                        xMin = x[1];
                        if (cows[i][0] == xMin) {
                            xMin = x[2];
                            if (cows[j][0] == xMin) {
                                xMin = x[3];
                            }
                        }
                        else if (cows[j][0] == xMin) {
                            xMin = x[2];
                            if (cows[i][0] == xMin) {
                                xMin = x[3];
                            }
                        }
                        good = true;
                    }
                    int xMax = x[4];
                    if (cows[i][0] == xMax) {
                        xMax = x[5];
                        if (cows[j][0] == xMax) {
                            xMax = x[6];
                            if (cows[k][0] == xMax) {
                                xMax = x[7];
                            }
                        }
                        else if (cows[k][0] == xMax) {
                            xMax = x[6];
                            if (cows[j][0] == xMax) {
                                xMax = x[7];
                            }
                        }
                        good = true;
                    }
                    else if (cows[j][0] == xMax) {
                        xMax = x[5];
                        if (cows[i][0] == xMax) {
                            xMax = x[6];
                            if (cows[k][0] == xMax) {
                                xMax = x[7];
                            }
                        }
                        else if (cows[k][0] == xMax) {
                            xMax = x[6];
                            if (cows[i][0] == xMax) {
                                xMax = x[7];
                            }
                        }
                        good = true;
                    }
                    else if (cows[k][0] == xMax) {
                        xMax = x[5];
                        if (cows[i][0] == xMax) {
                            xMax = x[6];
                            if (cows[j][0] == xMax) {
                                xMax = x[7];
                            }
                        }
                        else if (cows[j][0] == xMax) {
                            xMax = x[6];
                            if (cows[i][0] == xMax) {
                                xMax = x[7];
                            }
                        }
                        good = true;
                    }




                    int yMin = y[0];
                    if (cows[i][1] == yMin) {
                        yMin = y[1];
                        if (cows[j][1] == yMin) {
                            yMin = y[2];
                            if (cows[k][1] == yMin) {
                                yMin = y[3];
                            }
                        }
                        else if (cows[k][1] == yMin) {
                            yMin = y[2];
                            if (cows[j][1] == yMin) {
                                yMin = y[3];
                            }
                        }
                        good = true;
                    }
                    else if (cows[j][1] == yMin) {
                        yMin = y[1];
                        if (cows[i][1] == yMin) {
                            yMin = y[2];
                            if (cows[k][1] == yMin) {
                                yMin = y[3];
                            }
                        }
                        else if (cows[k][1] == yMin) {
                            yMin = y[2];
                            if (cows[i][1] == yMin) {
                                yMin = y[3];
                            }
                        }
                        good = true;
                    }
                    else if (cows[k][1] == yMin) {
                        yMin = y[1];
                        if (cows[i][1] == yMin) {
                            yMin = y[2];
                            if (cows[j][1] == yMin) {
                                yMin = y[3];
                            }
                        }
                        else if (cows[j][1] == yMin) {
                            yMin = y[2];
                            if (cows[i][1] == yMin) {
                                yMin = y[3];
                            }
                        }
                        good = true;
                    }
                    int yMax = y[4];
                    if (cows[i][1] == yMax) {
                        yMax = y[5];
                        if (cows[j][1] == yMax) {
                            yMax = y[6];
                            if (cows[k][1] == yMax) {
                                yMax = y[7];
                            }
                        }
                        else if (cows[k][1] == yMax) {
                            yMax = y[6];
                            if (cows[j][1] == yMax) {
                                yMax = y[7];
                            }
                        }
                        good = true;
                    }
                    else if (cows[j][1] == yMax) {
                        yMax = y[5];
                        if (cows[i][1] == yMax) {
                            yMax = y[6];
                            if (cows[k][1] == yMax) {
                                yMax = y[7];
                            }
                        }
                        else if (cows[k][1] == yMax) {
                            yMax = y[6];
                            if (cows[i][1] == yMax) {
                                yMax = y[7];
                            }
                        }
                        good = true;
                    }
                    else if (cows[k][1] == yMax) {
                        yMax = y[5];
                        if (cows[i][1] == yMax) {
                            yMax = y[6];
                            if (cows[j][1] == yMax) {
                                yMax = y[7];
                            }
                        }
                        else if (cows[j][1] == yMax) {
                            yMax = y[6];
                            if (cows[i][1] == yMax) {
                                yMax = y[7];
                            }
                        }
                        good = true;
                    }
                    if (good) {
                        int newAns = (xMax - xMin) * (yMax - yMin);
                        if (newAns < ans) { // check if the new area is smaller
                            ans = newAns;
                        }
                    }
                }
            }
        }

        pw.println(ans);
        pw.close();
    }

    // This function takes in a point and updates the two smallest and two largest x and y coordinates.
    public static void update(int a, int b) {
        if(a < x[0]) {
            x[3] = x[2];
            x[2] = x[1];
            x[1] = x[0];
            x[0] = a;
        }
        else if(a < x[1]) {
            x[3] = x[2];
            x[2] = x[1];
            x[1] = a;
        }
        else if(a < x[2]) {
            x[3] = x[2];
            x[2] = a;
        }
        else if(a < x[3]) {
            x[3] = a;
        }
        if(a > x[4]) {
            x[7] = x[6];
            x[6] = x[5];
            x[5] = x[4];
            x[4] = a;
        }
        else if(a > x[5]) {
            x[7] = x[6];
            x[6] = x[5];
            x[5] = a;
        }
        else if(a > x[6]) {
            x[7] = x[6];
            x[6] = a;
        }
        else if(a > x[7]) {
            x[7] = a;
        }

        if(b < y[0]) {
            y[3] = y[2];
            y[2] = y[1];
            y[1] = y[0];
            y[0] = b;
        }
        else if(b < y[1]) {
            y[3] = y[2];
            y[2] = y[1];
            y[1] = b;
        }
        else if(b < y[2]) {
            y[3] = y[2];
            y[2] = b;
        }
        else if(b < y[3]) {
            y[3] = b;
        }
        if(b > y[4]) {
            y[7] = y[6];
            y[6] = y[5];
            y[5] = y[4];
            y[4] = b;
        }
        else if(b > y[5]) {
            y[7] = y[6];
            y[6] = y[5];
            y[5] = b;
        }
        else if(b > y[6]) {
            y[7] = y[6];
            y[6] = b;
        }
        else if(b > y[7]) {
            y[7] = b;
        }
    }
}
