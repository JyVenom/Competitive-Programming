/*
ID: jerryya2
LANG: JAVA
PROG: skidesign
*/

import java.io.*;

public class skidesign {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

        int n = Integer.parseInt(br.readLine());

        int[] hills = new int[n];
        for (int i = 0; i < n; i++) {
            hills[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        int min = Integer.MAX_VALUE;

        for(int i = 0; i <= 83; i++) {
            int cost = 0;
            for(int j = 0; j < n; j++) {
                int x; //Minimum height of all hills
                if(hills[j] < i) { //If find hill lower than min height, add mass
                    x = i - hills[j];
                }
                else if(hills[j] > i + 17) { //If find hill more than 17 taller than min height shorten it
                    x = hills[j] - (i + 17);
                }
                else {
                    x = 0;
                }
                cost += x * x;
            }

            min = Math.min(min, cost);
        }

        pw.println(min);
        pw.close();
    }
}
