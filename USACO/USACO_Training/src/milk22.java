/*
ID: jerryya2
LANG: JAVA
PROG: milk2
*/

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class milk22 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

        int n = Integer.parseInt(br.readLine());

        int[][] times = new int[n][2];
        for (int i = 0; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i][0] = Integer.parseInt(st.nextToken());
            times[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(times, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(times, Comparator.comparingInt(arr -> arr[0]));

        int longestIdle = 0;
        int longestCont = times[0][1] - times[0][0];
        int start;
        int end;
        for (int i = 0; i < n; i++) {
            start = times[i][0];
            end = times[i][1];
            for (int j = i + 1; j < n; j++) {
                if (times[j][0] <= end) {
                    end = Math.max(end, times[j][1]);
                }
                else {
                    break;
                }
                i = j - 1;
            }
            longestCont = Math.max(longestCont, end - start);
            if (i < n - 1) {
                longestIdle = Math.max(longestIdle, times[i + 1][0] - end);
            }
        }
        pw.println(longestCont + " " + longestIdle);
        pw.close();
    }
}