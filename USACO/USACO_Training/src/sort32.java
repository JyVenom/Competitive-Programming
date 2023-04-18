/*
ID: jerryya2
LANG: JAVA
TASK: sort3
*/

import java.io.*;
import java.util.Arrays;

public class sort32 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

        int n = Integer.parseInt(br.readLine());

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }
        br.readLine();

        int exchanges = 0;
        int min = 0;
        for (int i = 0; i < n - 1; i++) {
            if (values[i] != 1) {
                for (int j = n - 1; j > i; j--) {
                    if (values[i] > values[j]) {
                        if (values[j] == 1) {
                            min = j;
                            break;
                        }
                        else if (j > min) {
                            min = j;
                        }
                    }
                }
                if (min > 0) {
                    int temp = values[i];
                    values[i] = values[min];
                    values[min] = temp;
                    exchanges++;
                    min = 0;
                }
            }
        }

        pw.println(exchanges);
        pw.close();
    }
}
