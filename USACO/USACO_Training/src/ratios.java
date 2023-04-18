/*
ID: jerryya2
LANG: JAVA
TASK: ratios
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ratios {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ratios.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));

        int[] goal = new int[3];
        int[][] mix = new int[3][3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            goal[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                mix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = 298;
        int[] ans = new int[4];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 100; k++) {
                    int sum = i + j + k;
                    if (sum > min || sum == 0) {
                        continue;
                    }
                    int[] cur = new int[3];
                    for (int l = 0; l < 3; l++) {
                        cur[l] += (mix[0][l] * i);
                        cur[l] += (mix[1][l] * j);
                        cur[l] += (mix[2][l] * k);
                    }
                    if (i == 3 && j == 5 && k == 1) {
                        System.out.println();
                    }
                    if (isMultiple(cur, goal)) {
                        min = sum;
                        ans[0] = i;
                        ans[1] = j;
                        ans[2] = k;
                        for (int l = 0; l < 3; l++) {
                            if (goal[l] != 0) {
                                ans[3] = cur[l] / goal[l];
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (min == 298 || Arrays.equals(ans, new int[]{0, 0, 0, 0})) {
            pw.println("NONE");
        }
        else {
            pw.println(ans[0] + " " + ans[1] + " " + ans[2] + " " + ans[3]);
        }
        pw.close();
    }

    private static boolean isMultiple (int[] cur, int[] goal) {
        if (goal[0] == 0) {
            if (cur[0] > 0) {
                return false;
            }
        }
        if (goal[1] == 0) {
            if (cur[1] > 0) {
                return false;
            }

        }
        if (goal[2] == 0) {
            if (cur[2] > 0) {
                return false;
            }

        }
        if ((goal[0] == 0 || cur[0] % goal[0] == 0) && (goal[1] == 0 || cur[1] % goal[1] == 0) && (goal[2] == 0 || cur[2] % goal[2] == 0)) {
            return (goal[0] == 0 || goal[1] == 0 || cur[0] / goal[0] == cur[1] / goal[1]) && (goal[1] == 0 || goal[2] == 0 || cur[1] / goal[1] == cur[2] / goal[2]);
        }
        return false;
    }
}
