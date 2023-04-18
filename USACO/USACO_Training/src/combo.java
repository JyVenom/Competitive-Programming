/*
ID: jerryya2
LANG: JAVA
PROG: combo
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class combo {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("combo.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

        int n = Integer.parseInt(br.readLine());
        int[] fj = new int[3];
        int[] master = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        fj[0] = Integer.parseInt(st.nextToken());
        fj[1] = Integer.parseInt(st.nextToken());
        fj[2] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        master[0] = Integer.parseInt(st.nextToken());
        master[1] = Integer.parseInt(st.nextToken());
        master[2] = Integer.parseInt(st.nextToken());
        br.close();

        ArrayList<int[]> pos = new ArrayList<>();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if ((Math.abs(i - fj[0]) <= 2 || Math.abs(Math.abs(i - fj[0]) - n) <= 2) && (Math.abs(j - fj[1]) <= 2 || Math.abs(Math.abs(j - fj[1]) - n) <= 2) && (Math.abs(k - fj[2]) <= 2 || Math.abs(Math.abs(k - fj[2]) - n) <= 2)) {
                        int[] po = new int[3];
                        po[0] = i;
                        po[1] = j;
                        po[2] = k;
                        pos.add(po);
                        count++;
                    }
                    else if ((Math.abs(i - master[0]) <= 2 || Math.abs(Math.abs(i - master[0]) - n) <= 2) && (Math.abs(j - master[1]) <= 2 || Math.abs(Math.abs(j - master[1]) - n) <= 2) && (Math.abs(k - master[2]) <= 2 || Math.abs(Math.abs(k - master[2]) - n) <= 2)) {
                        int[] po = new int[3];
                        po[0] = i;
                        po[1] = j;
                        po[2] = k;
                        pos.add(po);
                        count++;
                    }
                }
            }
        }

        pw.println(count);
        pw.close();
    }
}
