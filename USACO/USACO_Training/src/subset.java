/*
ID: jerryya2
LANG: JAVA
TASK: subset
*/

import java.io.*;

public class subset {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("subset.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

        int N = Integer.parseInt(br.readLine());
        br.close();

        int sum = (N * N + N) / 2;
        if (sum % 2 == 1) {
            pw.println(0);
        }
        else {
            int half = sum / 2;
            long[][] dp = new long[N + 1][N * (N + 1) / 2];
            for (int i = 0; i <= N; i++) {
                dp[i][0] = 1;
            }
            int quarter = (N * N + N) / 4;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= quarter; j++) {
                    dp[i][j] += dp[i - 1][j]; // += number of ways to make sum j with i - 1 elements
                    if (i <= j) {
                        dp[i][j] += dp[i - 1][j - i]; // += number of ways to make sum j - i with i - 1 elements
                    }
                }
            }

            pw.println(dp[N][half] / 2);
        }

        pw.close();
    }
}
