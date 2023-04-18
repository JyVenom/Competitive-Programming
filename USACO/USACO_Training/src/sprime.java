/*
ID: jerryya2
LANG: JAVA
TASK: sprime
*/

import java.io.*;
import java.util.ArrayList;

public class sprime {
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

        n = Integer.parseInt(br.readLine());
        br.close();

        ArrayList<Integer> ans = new ArrayList<>();

        genSuperPrimes(2, ans);
        genSuperPrimes(3, ans);
        genSuperPrimes(5, ans);
        genSuperPrimes(7, ans);

        for (Integer an : ans) {
            pw.println(an);
        }
        pw.close();
    }

    public static void genSuperPrimes(int s, ArrayList<Integer> ans) {
        if (Integer.toString(s).length() == n) {
            ans.add(s);
        } else {
            if (isPrime(s * 10 + 1)) {
                genSuperPrimes(s * 10 + 1, ans);
            }
            if (isPrime(s * 10 + 3)) {
                genSuperPrimes(s * 10 + 3, ans);
            }
            if (isPrime(s * 10 + 7)) {
                genSuperPrimes(s * 10 + 7, ans);
            }
            if (isPrime(s * 10 + 9)) {
                genSuperPrimes(s * 10 + 9, ans);
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        } else if (num == 2) {
            return true;
        } else if (num % 2 == 0) {
            return false;
        } else {
            int root = (int) Math.sqrt(num);
            for (int j = 3; j <= root; j += 2) {
                if (num % j == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
