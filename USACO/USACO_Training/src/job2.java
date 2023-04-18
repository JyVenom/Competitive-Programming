/*
ID: jerryya2
LANG: JAVA
TASK: job
*/

//Big-O: couple n
//1 <= n <= 1000

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class job2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("job.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[][] a = new int[m1][3];
        int[][] b = new int[m2][3];
        //each line is a machine, 3 values are processing time, time left to finish current item, and in-use 0/1 for no/yes
        for (int i = 0; i < m1; i++) {
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = a[i][0];
            a[i][2] = 1;
        }
        for (int i = 0; i < m2; i++) {
            b[i][0] = Integer.parseInt(st.nextToken());
            b[i][1] = b[i][0];
            b[i][2] = 1;
        }

        Arrays.sort(a, Comparator.comparingInt(arr -> arr[0]));
        Arrays.sort(b, Comparator.comparingInt(arr -> arr[0]));

        int time = 0;
        int start = n - m1;
        int mid = 0;
        int end = 0;
        int nextAvailable = 0;
        int min = a[0][0];
        time += min;
        for (int[] machine : a) {
            machine[1] -= min;
            if (machine[1] == 0) {
                machine[2] = 0;
                mid++;
            }
        }



        pw.close();
    }
}
