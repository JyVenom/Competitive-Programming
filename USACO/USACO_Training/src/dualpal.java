/*
ID: jerryya2
LANG: JAVA
PROG: dualpal
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dualpal {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int i = s + 1;
        ArrayList<Integer> ns = new ArrayList<>();
        while (ns.size() < n) {
            int count = 0;
            for (int b = 2; b <= 10; b++) {
                String inBaseB = toBase(i, b);
                if (isPalindromic(inBaseB)) {
                    count++;
                }
                if (count == 2) {
                    ns.add(i);
                    break;
                }
            }
            i++;
        }

        for (int pos : ns) {
            pw.println(pos);
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