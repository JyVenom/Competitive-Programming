import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class FieldReduction4 {
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
        int t = 0;
        int b = 0;
        int l = 0;
        int r = 0;
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                l++;
            }
            else if (i == 1) {
                r++;
            }
            else if (i == 2) {
                t++;
            }
            else {
                b++;
            }
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    l++;
                }
                else if (j == 1) {
                    r++;
                }
                else if (j == 2) {
                    t++;
                }
                else {
                    b++;
                }
                for (int k = 0; k < 4; k++) {
                    if (k == 0) {
                        l++;
                    }
                    else if (k == 1) {
                        r++;
                    }
                    else if (k == 2) {
                        t++;
                    }
                    else {
                        b++;
                    }

                    int[] used = new int[8];
                    if (l > 0) {
                        for (int m = 0; m < l; m++) {
                            if (cows[m][1] == y[0]) {
                                used[0] = 1;
                            } else if (cows[m][1] == y[1]) {
                                used[1] = 1;
                            } else if (cows[m][1] == y[2]) {
                                used[2] = 1;
                            } else if (cows[m][1] == y[3]) {
                                used[3] = 1;
                            } else if (cows[m][1] == y[4]) {
                                used[4] = 1;
                            } else if (cows[m][1] == y[5]) {
                                used[5] = 1;
                            } else if (cows[m][1] == y[6]) {
                                used[6] = 1;
                            } else if (cows[m][1] == y[7]) {
                                used[7] = 1;
                            }
                        }
                    }
                    if (r > 0) {
                        for (int m = 0; m < r; m++) {
                            if (cows[n - m - 1][1] == y[0]) {
                                used[0] = 1;
                            } else if (cows[n - m - 1][1] == y[1]) {
                                used[1] = 1;
                            } else if (cows[n - m - 1][1] == y[2]) {
                                used[2] = 1;
                            } else if (cows[n - m - 1][1] == y[3]) {
                                used[3] = 1;
                            } else if (cows[n - m - 1][1] == y[4]) {
                                used[4] = 1;
                            } else if (cows[n - m - 1][1] == y[5]) {
                                used[5] = 1;
                            } else if (cows[n - m - 1][1] == y[6]) {
                                used[6] = 1;
                            } else if (cows[n - m - 1][1] == y[7]) {
                                used[7] = 1;
                            }
                        }
                    }

                    for (int m = 0; m < 4; m++) {
                        if (used[m] == 0) {
                            b += m;
                            break;
                        }
                    }
                    for (int m = 4; m < 8; m++) {
                        if (used[m] == 0) {
                            t += (m - 4);
                            break;
                        }
                    }
                    ans = Math.min(ans, (x[4 + r] - x[l]) * (y[4 + t] - y[b]));
                    for (int m = 0; m < 4; m++) {
                        if (used[m] == 0) {
                            b -= m;
                            break;
                        }
                    }
                    for (int m = 4; m < 8; m++) {
                        if (used[m] == 0) {
                            t -= (m - 4);
                            break;
                        }
                    }

                    if (k == 0) {
                        l--;
                    }
                    else if (k == 1) {
                        r--;
                    }
                    else if (k == 2) {
                        t--;
                    }
                    else {
                        b--;
                    }
                }
                if (j == 0) {
                    l--;
                }
                else if (j == 1) {
                    r--;
                }
                else if (j == 2) {
                    t--;
                }
                else {
                    b--;
                }
            }
            if (i == 0) {
                l--;
            }
            else if (i == 1) {
                r--;
            }
            else if (i == 2) {
                t--;
            }
            else {
                b--;
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
