/*
ID: jerryya2
LANG: JAVA
PROG: palsquare
*/

import java.io.*;
import java.util.ArrayList;

public class palsquare {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));

        int b = Integer.parseInt(br.readLine());
        ArrayList<String[]> ns = new ArrayList<>();
        for (int i = 1; i <= 300; i++) {
            int squared = i * i;
            String inBaseB = toBase(squared, b);
            if (isPalindromic(inBaseB)) {
                String[] pair = new String[2];
                pair[0] = toBase(i, b);
                pair[1] = inBaseB;
                ns.add(pair);
            }
        }

        for (String[] pair : ns) {
            pw.println(pair[0] + " " + pair[1]);
        }
        pw.close();
    }

    private static boolean isPalindromic (String v) {
        int len = v.length();
        for (int i = 0; i < len / 2; i++) {
            if (v.charAt(i) != v.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static String toBase(int num, int base) {
        StringBuilder s = new StringBuilder();
        while (num > 0) {
            s.append(reVal(num % base));
            num /= base;
        }
        StringBuilder ix = new StringBuilder();
        ix.append(s);
        return ix.reverse().toString();
    }

    private static char reVal(int num) {
        if (num >= 0 && num <= 9)
            return (char)(num + 48);
        else
            return (char)(num - 10 + 65);
    }
}