/*
ID: jerryya2
LANG: JAVA
TASK: humble
*/

import java.io.*;
import java.util.Arrays;

public class humble3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("humble.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));

        String[] line = br.readLine().split(" ");
        int k = Integer.parseInt(line[0]);
        int n = Integer.parseInt(line[1]);

        line = br.readLine().split(" ");
        int[] primes = new int[k];
        for (int i = 0; i < k; i++) {
            primes[i] = Integer.parseInt(line[i]);
        }
        br.close();
        Arrays.sort(primes);

        long[] humble = new long[n + 1];
        int[] nxt = new int[k];
        humble[0] = 1;
        for (int i = 1; i <= n; i++) {
            long best = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                while (primes[j] * humble[nxt[j]] <= humble[i - 1]) {
                    nxt[j]++;
                }
                best = Math.min(best, primes[j] * humble[nxt[j]]);
            }
            humble[i] = best;
        }

        pw.println(humble[n]);
        pw.close();
    }
}