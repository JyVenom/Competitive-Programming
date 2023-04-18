/*
ID: jerryya2
LANG: JAVA
TASK: fence9
*/

import java.io.*;
import java.util.StringTokenizer;

public class fence9 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fence9.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        //equation of left side is y = (m/n) * x or yn/m = x
        //equation of right side is y = -m/(p-n) * x + (pm/(p-n)) or (yn-yp)/m + p = x
        //at each layer, cows can be between x of left side + 1 floored and x of right side - 1 ceiled
        //since it is inclusive, rx - lx + 1 cows can be placed in each range
        long count = 0;
        for (int y = 1; y < m; y++) { //y is the height/y/layer
            int lMax = (y * n / m) + 1;
            int rMax = (int) (Math.ceil((double) ((y * n) - (y * p)) / m)) + p - 1;
            count += rMax - lMax + 1;
        }

        pw.println(count);
        pw.close();
    }
}
