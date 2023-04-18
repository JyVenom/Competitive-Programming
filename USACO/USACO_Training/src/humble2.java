/*
ID: jerryya2
LANG: JAVA
TASK: humble
*/

import java.io.*;
import java.util.Arrays;

public class humble2 {
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
        Arrays.sort(primes);

        if (k == 1) {
            pw.println((int) Math.pow(primes[0], n));
        } else {
            int[] all = new int[n];
            int at = 0;
            int last = n - 1;
            int cur = 2;
            while (all[last] == 0) {
                int cur2 = cur;
                boolean good = true;
                int i = 2;
                while (cur2 > 1) {
                    if (cur2 % i == 0) {
                        if (Arrays.binarySearch(primes, i) < 0) {
                            good = false;
                            break;
                        }
                        cur2 = cur2 / i;
                    } else if (i == 2) {
                        i++;
                    } else {
                        i += 2;
                    }
                }
                if (good) {
                    all[at] = cur;
                    at++;
                }
                cur++;
            }

            pw.println(all[last]);
        }

        pw.close();
    }
}