/*
ID: jerryya2
LANG: JAVA
PROG: ariprog
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class ariprog {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        br.close();

        HashSet<Integer> temp = new HashSet<>();
        boolean[] contains = new boolean[2 * m * m + 1];
        for (int p = 1; p <= m; p++) {
            for (int q = 1; q <= p; q++) {
                int val = p * p + q * q;
                temp.add(val);
                contains[val] = true;
            }
        }
        for (int i = 0; i <= m; i++) {
            int val = i * i;
            temp.add(val);
            contains[val] = true;
        }
        ArrayList<Integer> bisquares = new ArrayList<>(temp);
        Collections.sort(bisquares);

        ArrayList<int[]> pos = new ArrayList<>();
        for (int a : bisquares) {
            for (int b = 1; b <= (2 * m * m - a) / (n - 1); b++) {
                boolean good = true;
                for (int i = 0; i < n; i++) {
                    if (!contains[a + i * b]) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    int[] po = new int[2];
                    po[0] = a;
                    po[1] = b;
                    pos.add(po);
                }
            }
        }
        pos.sort(Comparator.comparingInt(a -> a[0]));
        pos.sort(Comparator.comparingInt(a -> a[1]));

        if (pos.size() == 0) {
            pw.println("NONE");
        } else {
            for (int[] po : pos) {
                pw.println(po[0] + " " + po[1]);
            }
        }
        pw.close();
    }
}
