/*
ID: jerryya2
LANG: JAVA
TASK: kimbits
*/

import java.io.*;
import java.util.StringTokenizer;

public class kimbits3 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("kimbits.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int i = Integer.parseInt(st.nextToken());

        int orig = (int) Math.pow(2, l);
        if (i <= orig) {
            StringBuilder b = new StringBuilder(Integer.toBinaryString(i - 1));
            while (b.length() < n) {
                b.insert(0, "0");
            }
            pw.println(b);
        }
        else {
            int prev = orig;
            int N = l + 1;
            int cur = 0;
            for (int r = 0; r <= l; r++) {
                cur += c(N, r);
            }
            while (cur < i) {
                prev = cur;
                N++;
                cur = 0;
                for (int r = 0; r <= l; r++) {
                    cur += c(N, r);
                }
            }

            System.out.println(System.currentTimeMillis() - start);

            int max = (int) Math.pow(2, n);
            int count = prev;
            for (int j = (int) Math.pow(2, N - 1); j < max && count < i; j++) {
                StringBuilder b = new StringBuilder(Integer.toBinaryString(j));
                int num = 0;
                for (int k = 0; k < b.length(); k++) {
                    if (b.charAt(k) == '1') {
                        num++;
                        if (num > l) {
                            break;
                        }
                    }
                }
                if (num <= l) {
                    count++;
                    if (count == i) {
                        while (b.length() < n) {
                            b.insert(0, "0");
                        }
                        pw.println(b);
                        break;
                    }
                }
            }
        }

        pw.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int c (int n, int r) {
        if (r == 0 || r == n) {
            return 1;
        }
        else if (r == 1 || r == n - 1) {
            return n;
        }

        long ans = 1;
        if (r > n - r) {
            for (int i = r + 1; i <= n; i++) {
                ans *= i;
            }
            int other = n - r;
            for (int i = 2; i <= other; i++) {
                ans /= i;
            }
        }
        else {
            for (int i = n - r + 1; i <= n; i++) {
                ans *= i;
            }
            for (int i = 2; i <= r; i++) {
                ans /= i;
            }
        }
        return (int) ans;
    }
}
