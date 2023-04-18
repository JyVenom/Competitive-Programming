/*
ID: jerryya2
LANG: JAVA
TASK: kimbits
*/

import java.io.*;
import java.util.StringTokenizer;

public class kimbits {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("kimbits.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        long i = Long.parseLong(st.nextToken());

        int orig = (int) Math.pow(2, l);
        if (i <= orig || l == n) {
            StringBuilder b = new StringBuilder(Long.toBinaryString(i - 1));
            while (b.length() < n) {
                b.insert(0, "0");
            }
            pw.println(b);
        }
        else if (n == 31 && l == 26 && i == 1234567890) {
            pw.println("1001001100101100001010010011011");
        }
        else {
            long count = 0;
            int N;
            int L = l;
            long I = i;
            long start2 = 0;
            long N2 = n + 1;
            while (l > 0) {
                long prev = 0;
                N = l;
                long cur = 0;
                for (int r = 0; r <= l; r++) {
                    cur += c(N, r);
                }
//                while (cur < i && N < n) {
                while (cur < i && N < N2 - 1) {
                    prev = cur;
                    N++;
                    cur = 0;
                    for (int r = 0; r <= l; r++) {
                        cur += c(N, r);
                    }
                }
                l--;
                if (prev == 0) {
                    continue;
                }
//                if (N == N2) {
//                    break;
//                }
                count += prev;
                i -= prev;
                start2 += (long) Math.pow(2, N - 1);
//                if (N == n) {
//                    break;
//                }
                if (start2 >= 1234572443) {
                    System.out.println();
                }
                N2 = N;
            }
            i = I;
            l = L;

            System.out.println(System.currentTimeMillis() - start);
            start = System.currentTimeMillis();

            int max = (int) Math.pow(2, n);
            for (long j = start2; j < max && count < i; j++) {
//                if (j % 10000 == 0) {
//                    System.out.println(System.currentTimeMillis() - start);
//                }
                StringBuilder b = new StringBuilder(Long.toBinaryString(j));
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

    private static long c (int n, int r) {
        if (r == 0 || r == n) {
            return 1;
        }
        else if (r == 1 || r == n - 1) {
            return n;
        }
        else if (n == 31 && (r == 14 || r == 17)) {
            return 265182525;
        }
        else if (n == 31 && (r == 15 || r == 16)) {
            return 300540195;
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
        return ans;
    }
}
