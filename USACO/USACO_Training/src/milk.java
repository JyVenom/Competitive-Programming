/*
ID: jerryya2
LANG: JAVA
PROG: milk
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class milk {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> farmers = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> farmer = new ArrayList<>(2);
            farmer.add(Integer.parseInt(st.nextToken()));
            farmer.add(Integer.parseInt(st.nextToken()));
            farmers.add(farmer);
        }
        farmers.sort(Comparator.comparing(a -> a.get(0)));

        int cost = 0;
        while (n > 0) {
            n--;
            cost += farmers.get(0).get(0);
            farmers.get(0).set(1, farmers.get(0).get(1) - 1);
            if (farmers.get(0).get(1) == 0) {
                farmers.remove(0);
            }
        }

        pw.println(cost);
        pw.close();
    }
}
