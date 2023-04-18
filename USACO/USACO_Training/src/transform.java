/*
ID: jerryya2
LANG: JAVA
PROG: transform
*/

import java.io.*;

public class transform{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("transform.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
        int n = Integer.parseInt(br.readLine());

        char[][] before = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                before[i][j] = line.charAt(j);
            }
        }
        char[][] after = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                after[i][j] = line.charAt(j);
            }
        }
        br.close();

        boolean good = false;
        char[][] temp = before.clone();
        temp = one(temp);
        if (same(temp, after)) {
            pw.println(1);
            good = true;
        }
        if (!good){
            temp = before.clone();
            temp = two(temp);
            if (same(temp, after)) {
                pw.println(2);
                good = true;
            }
        }
        if (!good){
            temp = before.clone();
            temp = three(temp);
            if (same(temp, after)) {
                pw.println(3);
                good = true;
            }
        }
        if (!good){
            temp = before.clone();
            temp = four(temp);
            if (same(temp, after)) {
                pw.println(4);
                good = true;
            }
            else {
                temp = one(temp);
                if (same(temp, after)) {
                    pw.println(5);
                    good = true;
                }
                if (!good){
                    temp = before.clone();
                    temp = four(temp);
                    temp = two(temp);
                    if (same(temp, after)) {
                        pw.println(5);
                        good = true;
                    }
                }
                if (!good){
                    temp = before.clone();
                    temp = four(temp);
                    temp = three(temp);
                    if (same(temp, after)) {
                        pw.println(5);
                        good = true;
                    }
                }
            }
        }
        if (!good) {
            if (same(before, after)) {
                pw.println(6);
                good = true;
            }
        }
        if (!good) {
            pw.println(7);
        }

        pw.close();
    }

    private static boolean same (char[][] a, char[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static char[][] one (char[][] a) {
        char[][] res = new char[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                res[j][res[0].length - i - 1] = a[i][j];
            }
        }
        return res;
    }

    private static char[][] two (char[][] a) {
        char[][] res = new char[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                res[res.length - i - 1][res[0].length - j - 1] = a[i][j];
            }
        }
        return res;
    }

    private static char[][] three (char[][] a) {
        char[][] res = new char[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                res[res.length - j - 1][i] = a[i][j];
            }
        }
        return res;
    }

    private static char[][] four (char[][] a) {
        char[][] res = new char[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                res[i][res[0].length - j - 1] = a[i][j];
            }
        }
        return res;
    }
}