/*
ID: jerryya2
LANG: JAVA
TASK: hamming
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class hamming {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("hamming.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int max = (int) Math.pow(2, b);
        ArrayList<Integer> pos = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            pos.add(i);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (hammingDist(pos.get(i), pos.get(j)) < d) {
                    pos.remove(i);
                    i--;
                    break;
                }
            }
        }

        pw.print(pos.get(0));
        for (int i = 1; i < n; i++) {
            if (i % 10 == 0) {
                pw.println();
                pw.print(pos.get(i));
            }
            else {
                pw.print(" " + pos.get(i));
            }
        }
        pw.println();
        pw.close();
    }

    private static int hammingDist (int a, int b) {
        StringBuilder A = new StringBuilder(Integer.toBinaryString(a));
        StringBuilder B = new StringBuilder(Integer.toBinaryString(b));

        int max = Math.max(A.length(), B.length());
        int dif = max - A.length();
        for (int i = 0; i < dif; i++) {
            A.insert(0, "0");
        }
        dif = max - B.length();
        for (int i = 0; i < dif; i++) {
            B.insert(0, "0");
        }

        int count = 0;
        for (int i = 0; i < max; i++) {
            if (A.charAt(i) != B.charAt(i)) {
                count++;
            }
        }

        return count;
    }
}
