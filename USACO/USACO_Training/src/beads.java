/*
ID: jerryya2
LANG: JAVA
PROG: beads
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class beads {
    public static void main(String[] args) throws Exception {
        BufferedReader bin = new BufferedReader(new FileReader("beads.in"));
        PrintWriter pout = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

        bin.readLine();
        String beads = bin.readLine();
        int ans = compute(beads);

        pout.println(ans);
        pout.close();
    }

    private static int compute(String beads) {
        int length = beads.length();
        int maxbeads = 0;
        for (int i = 0; i < length; i++) {
            int left;
            int right;
            left = computeLeft(beads, i);
            if (left == length) {
                maxbeads = left;
                break;
            }
            right = computeRight(beads, i);
            if (right == length) {
                maxbeads = right;
                break;
            }
            if ((left + right) > maxbeads) maxbeads = left + right;
            if (maxbeads == length) break;


        }
        return maxbeads;
    }

    private static int computeLeft(String beads, int i) {
        int l = beads.length();

        int j = i;
        int count = 0;
        char ch = beads.charAt(i);
        for (; j >= 0; j--) {
            if (ch == 'w') {//Didnt notice this kind of case before
                ch = beads.charAt(j);
                count++;
            } else if (beads.charAt(j) == ch || beads.charAt(j) == 'w') {
                count++;
            } else break;
        }
        if (j < 0) j = l - 1;
        for (; j > i; j--) {
            if (beads.charAt(j) == ch || beads.charAt(j) == 'w') {
                count++;
            } else break;
        }

        return count;
    }

    private static int computeRight(String beads, int i) {
        int l = beads.length();
        int j;
        if (i == l - 1) j = 0;
        else j = i + 1;
        int k = j;
        int count = 0;
        int ch = beads.charAt(j);
        for (; j < l; j++) {
            if (ch == 'w') {
                ch = beads.charAt(j);
                count++;
            } else if (beads.charAt(j) == ch || beads.charAt(j) == 'w') {
                count++;
            } else break;
        }
        if (j == l) j = 0;
        for (; j < k - 1; j++) {
            if (beads.charAt(j) == ch || beads.charAt(j) == 'w') {
                count++;
            } else break;
        }


        return count;
    }
}