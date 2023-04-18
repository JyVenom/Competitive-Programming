/*
ID: jerryya2
LANG: JAVA
TASK: buylow
*/

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class buylow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("buylow.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));

        int n = Integer.parseInt(br.readLine());
        int[] prices = new int[n + 1];
        prices[0] = Integer.MAX_VALUE;
        int at = 1;
        while (at <= n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                prices[at] = Integer.parseInt(st.nextToken());
                at++;
            }
        }
        
        BigInteger[] counts = new BigInteger[n + 1];
        int[] lens = new int[n + 1];
        int[] maxes = new int[n + 1];
        for (int i = n; i >= 0; i--) {
            int maxLen = 0;
            BigInteger maxCount = BigInteger.ZERO;
            int maxIndex = 0;
            for (int j = i + 1; j <= n; j++) {
                if (prices[j] < prices[i]) {
                    int len = lens[j];
                    if (len == maxLen) {
                        boolean repeat = false;
                        for (int k = 0; k < maxIndex; k++) {
                            if (maxes[k] == prices[j]) {
                                repeat = true;
                                break;
                            }
                        }
                        if (!repeat) {
                            maxCount = maxCount.add(counts[j]);
                            maxes[maxIndex++] = prices[j];
                        }
                    } else if (len > maxLen) {
                        maxLen = len;
                        maxCount = counts[j];
                        maxes[maxIndex++] = prices[j];
                    }
                }
            }
            if (maxLen == 0) {
                maxLen = 1;
                maxCount = BigInteger.ONE;
            } else {
                maxLen++;
            }
            lens[i] = maxLen;
            counts[i] = maxCount;
        }
        
        pw.println((lens[0] - 1) + " " + counts[0]);
        pw.close();
    }
}
