/*
ID: jerryya2
LANG: JAVA
TASK: job
*/

//Big-O: couple (2 - 6) * n
//1 <= n <= 1000

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class job4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("job.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int[][] a = new int[m1][2];
        int[][] b = new int[m2][2];
        //each line is a machine, 3 values are processing time and time left to finish current item
        int prev = 0;
        while (prev < m1) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            for (int i = prev; i < m1; i++) {
                if (st.hasMoreTokens()) {
                    a[i][0] = Integer.parseInt(st.nextToken());
                    a[i][1] = a[i][0];
                    prev++;
                }
            }
        }
        prev = 0;
        while (prev < m2) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            for (int i = 0; i < m2; i++) {
                if (st.hasMoreTokens()) {
                    b[i][0] = Integer.parseInt(st.nextToken());
                    prev++;
                }
            }
        }

        //Calculate the time at which items from machine A are finished processing and are transferred to the intermediate bucket
        Arrays.sort(a, Comparator.comparingInt(arr -> arr[0]));
        Arrays.sort(a, Comparator.comparingInt(arr -> arr[1]));
        int time = 0;
        int start = n - m1;
        ArrayList<Integer> mid = new ArrayList<>();
        while (start > 0) {
            int min = a[0][1];
            time += min;
            for (int[] machine : a) {
                machine[1] -= min;
                if (machine[1] == 0) {
                    if (start > 0) {
                        machine[1] = machine[0];
                        start--;
                    }
                    mid.add(time);
                }
            }
            Arrays.sort(a, Comparator.comparingInt(arr -> arr[0]));
            Arrays.sort(a, Comparator.comparingInt(arr -> arr[1]));
        }
        int max = m1 - 1;
        if (a[max][1] > 0) {
            while (a[max][1] > 0) {
                int lhs = 0;
                int rhs = max;
                for (; rhs >= 0 && a[rhs][1] > 0; rhs--) {
                    if (a[lhs][1] > 0) {
                        break;
                    }
                    if (a[lhs][0] < a[rhs][1]) {
                        a[lhs][1] = a[lhs][0];
                        a[rhs][1] = 0;
                        lhs++;
                    }
                }
                Arrays.sort(a, Comparator.comparingInt(arr -> arr[0]));
                Arrays.sort(a, Comparator.comparingInt(arr -> arr[1]));
                for (int i = 0; i <= max; i++) {
                    if (a[i][1] > 0) {
                        int min = a[i][1];
                        time += min;
                        mid.add(time);
                        for (int j = i; j <= max; j++) {
                            a[j][1] -= min;
                        }
                        break;
                    }
                }
                Arrays.sort(a, Comparator.comparingInt(arr -> arr[0]));
                Arrays.sort(a, Comparator.comparingInt(arr -> arr[1]));
            }
        }
        int[] ans = new int[2];
        ans[0] = time;
        //Calculate the times at which items from machine B are finished processing and are transferred to the end bucket (given the times at which they enter the intermediate bucket and are ready for processing by machine B)
        Arrays.sort(b, Comparator.comparingInt(arr -> arr[0]));
        Arrays.sort(b, Comparator.comparingInt(arr -> arr[1]));
        max = m2 - 1;
        time = 0;
        int min = mid.get(0);
        while (true) {
            time += min;
            for (int i = 0; i < mid.size(); i++) {
                mid.set(i, Math.max(mid.get(i) - min, 0));
            }
            for (int i = 0; i < m2; i++) {
                if (b[i][1] > 0) {
                    b[i][1] = Math.max(b[i][1] - min, 0);
                }
            }
            Arrays.sort(b, Comparator.comparingInt(arr -> arr[0]));
            Arrays.sort(b, Comparator.comparingInt(arr -> arr[1]));
            int next = 0;
            if (mid.get(0) == 0) {
                for (int i = 0; i < mid.size(); i++) {
                    if (mid.get(i) == 0) {
                        if (b[next][1] > 0) {
                            break;
                        }
                        b[next][1] = b[next][0];
                        next++;
                        mid.remove(i);
                        i--;
                    } else {
                        break;
                    }
                }
                if (mid.size() == 0) {
                    break;
                }
                Arrays.sort(b, Comparator.comparingInt(arr -> arr[0]));
                Arrays.sort(b, Comparator.comparingInt(arr -> arr[1]));
            } else {
                //if one of the processing items can be moved to another machine to process and it can finish before the next one come, do/move it
                while (b[max][1] > 0) {
                    int lhs = 0;
                    int rhs = max;
                    boolean done = false;
                    for (; rhs >= 0 && b[rhs][1] > 0; rhs--) {
                        if (b[lhs][0] < b[rhs][1] && (b[lhs][0] <= mid.get(next) || done)) {
                            b[lhs][1] = b[lhs][0];
                            b[rhs][1] = 0;
                            lhs++;
                            next++;
                            if (next == mid.size()) {
                                done = true;
                            }
                        }
                    }
                    Arrays.sort(b, Comparator.comparingInt(arr -> arr[0]));
                    Arrays.sort(b, Comparator.comparingInt(arr -> arr[1]));
                }
            }
            for (int i = 0; i < m2; i++) {
                if (b[i][1] > 0) {
                    min = b[i][1];
                    break;
                }
            }
            min = Math.min(min, mid.get(0));
        }
        if (b[max][1] > 0) {
            while (b[max][1] > 0) {
                int lhs = 0;
                int rhs = max;
                for (; rhs >= 0 && b[rhs][1] > 0; rhs--) {
                    if (b[lhs][1] > 0) {
                        break;
                    }
                    if (b[lhs][0] < b[rhs][1]) {
                        b[lhs][1] = b[lhs][0];
                        b[rhs][1] = 0;
                        lhs++;
                    }
                }
                Arrays.sort(b, Comparator.comparingInt(arr -> arr[0]));
                Arrays.sort(b, Comparator.comparingInt(arr -> arr[1]));
                for (int i = 0; i <= max; i++) {
                    if (b[i][1] > 0) {
                        min = b[i][1];
                        time += min;
                        for (int j = i; j <= max; j++) {
                            b[j][1] -= min;
                        }
                        break;
                    }
                }
                Arrays.sort(b, Comparator.comparingInt(arr -> arr[0]));
                Arrays.sort(b, Comparator.comparingInt(arr -> arr[1]));
            }
        }
        ans[1] = time;

        pw.println(ans[0] + " " + ans[1]);
        pw.close();
    }
}
