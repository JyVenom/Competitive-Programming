/*
ID: jerryya2
LANG: JAVA
TASK: stamps
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class stamps {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("stamps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int at = 0;
        int[] values = new int[n];
        int last = n - 1;
        while (values[last] == 0) {
            st = new StringTokenizer(br.readLine());
            int tok = st.countTokens();
            for (int i = 0; i < tok; i++) {
                values[i + at] = Integer.parseInt(st.nextToken());
            }
            at += tok;
        }
        br.close();
        Arrays.sort(values);
        int vMax = values[last];

        int[] dp = new int[k * vMax + 2];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 1;

        for (int i = 1; i <= k * vMax + 1; i++) {
            for (int j = 0; j < n; j++) {
                if (values[j] <= i && dp[i - values[j]] > 0) {
                    dp[i] = Math.min(dp[i], dp[i - values[j]] + 1);
                }
            }

            if (dp[i] > k + 1) {
                pw.println(i - 1);
                break;
            }
        }

        pw.close();
    }
}
