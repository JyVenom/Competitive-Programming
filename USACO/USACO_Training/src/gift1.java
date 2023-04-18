/*
ID: jerryya2
LANG: JAVA
PROG: gift1
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class gift1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

        int np = Integer.parseInt(br.readLine());

        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < np; i++) {
            names.add(br.readLine());
        }

        int[] money = new int[np];
        for (int i = 0; i < np; i++) {
            int d = names.indexOf(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            if (p != 0) {
                int g = m / p;
                money[d] -= p * g;
                for (int j = 0; j < p; j++) {
                    int person = names.indexOf(br.readLine());
                    money[person] += g;
                }
            }
        }

        for (int i = 0; i < np; i++) {
            pw.print(names.get(i) + " " + money[i] + "\n");
        }
        pw.close();
    }
}