/*
ID: jerryya2
LANG: JAVA
PROG: namenum
*/

import java.io.*;
import java.util.ArrayList;

public class namenum {
    public static void main(String[] args) throws Exception {
        long s = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("namenum.in"));
        BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

        ArrayList<String> d = new ArrayList<>();
        String line;
        while ((line = dict.readLine()) != null){
            d.add(line);
        }
        String num = br.readLine();
        ArrayList<String> res = new ArrayList<>();
        long val = Long.parseLong(num);
        int len = num.length();
        for (String name : d) {
            if (name.length() != len) {
                continue;
            }
            StringBuilder cur = new StringBuilder();
            for (int j = 0; j < len; j++) {
                if (name.charAt(j) == 'A' || name.charAt(j) == 'B' || name.charAt(j) == 'C') {
                    cur.append("2");
                } else if (name.charAt(j) == 'D' || name.charAt(j) == 'E' || name.charAt(j) == 'F') {
                    cur.append("3");
                } else if (name.charAt(j) == 'G' || name.charAt(j) == 'H' || name.charAt(j) == 'I') {
                    cur.append("4");
                } else if (name.charAt(j) == 'J' || name.charAt(j) == 'K' || name.charAt(j) == 'L') {
                    cur.append("5");
                } else if (name.charAt(j) == 'M' || name.charAt(j) == 'N' || name.charAt(j) == 'O') {
                    cur.append("6");
                } else if (name.charAt(j) == 'P' || name.charAt(j) == 'R' || name.charAt(j) == 'S') {
                    cur.append("7");
                } else if (name.charAt(j) == 'T' || name.charAt(j) == 'U' || name.charAt(j) == 'V') {
                    cur.append("8");
                } else if (name.charAt(j) == 'W' || name.charAt(j) == 'X' || name.charAt(j) == 'Y') {
                    cur.append("9");
                }
            }
            if (Long.parseLong(cur.toString()) == val) {
                res.add(name);
            }
        }
        for (String re : res) {
            pw.println(re);
        }
        if (res.size() == 0) {
            pw.println("NONE");
        }
        pw.close();
        long end = System.currentTimeMillis();
        System.out.println(end - s);
    }
}