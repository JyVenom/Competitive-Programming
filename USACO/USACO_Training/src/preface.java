/*
ID: jerryya2
LANG: JAVA
TASK: preface
*/

import java.io.*;

public class preface {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("preface.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));

        int n = Integer.parseInt(br.readLine());

        int[] count = new int[7];
        for (int i = 1; i <= n; i++) {
            String num = toRoman(i);
            for (int j = 0; j < num.length(); j++) {
                char c = num.charAt(j);
                if (c == 'I') {
                    count[0]++;
                } else if (c == 'V') {
                    count[1]++;
                } else if (c == 'X') {
                    count[2]++;
                } else if (c == 'L') {
                    count[3]++;
                } else if (c == 'C') {
                    count[4]++;
                } else if (c == 'D') {
                    count[5]++;
                } else if (c == 'M') {
                    count[6]++;
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            int num = count[i];
            if (num > 0) {
                if (i == 0) {
                    pw.print("I ");
                } else if (i == 1) {
                    pw.print("V ");
                } else if (i == 2) {
                    pw.print("X ");
                } else if (i == 3) {
                    pw.print("L ");
                } else if (i == 4) {
                    pw.print("C ");
                } else if (i == 5) {
                    pw.print("D ");
                } else {
                    pw.print("M ");
                }
                pw.println(num);
            }
        }
        pw.close();
    }

//    public static String toRoman(int n) {
//        StringBuilder roman = new StringBuilder();
//        int repeat;
//        int[] magnitude = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
//        String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
//
//        for (int x = 0; x < magnitude.length; x++) {
//            repeat = n / magnitude[x];
//            roman.append(symbol[x].repeat(Math.max(0, repeat)));
//            n = n % magnitude[x];
//        }
//        return roman.toString();
//    }

    public static String toRoman(int n) {
        StringBuilder roman = new StringBuilder();
        int repeat;
        int[] magnitude = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int x = 0; x < magnitude.length; x++) {
            repeat = n / magnitude[x];
            for (int i = 1; i <= repeat; i++) {
                roman.append(symbol[x]);
            }
            n = n % magnitude[x];
        }
        return roman.toString();
    }
}
