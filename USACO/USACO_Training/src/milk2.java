/*
ID: jerryya2
LANG: JAVA
PROG: milk2
*/

import java.io.*;
import java.util.*;

public class milk2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        int n = Integer.parseInt(br.readLine());

        int[][] times = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i][0] = Integer.parseInt(st.nextToken());
            times[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();
        Arrays.sort(times, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(times, Comparator.comparingInt(arr -> arr[0]));

        int longestCont = 0;
        int longestIdle = 0;

        int start = times[0][0];
        int end = times[0][1];
        for (int[] time : times) {
            if (time[0] > end) {
                longestIdle = Math.max(longestIdle, time[0] - end);
                start = time[0];
                end = time[1];
            }
            else {
                end = Math.max(end, time[1]);
            }

            longestCont = Math.max(longestCont, end - start);
        }

        pw.println(longestCont + " " + longestIdle);
        pw.close();
    }
}