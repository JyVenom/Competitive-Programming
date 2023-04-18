/*
ID: jerryya2
LANG: JAVA
TASK: nocows
*/

import java.io.*;
import java.util.StringTokenizer;

public class nocows {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("nocows.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        br.close();
        
        long [][] dp = new long[n + 1][k + 1];
        for(int K = 1; K <= k; K++) { // looping through all possible heights
            dp[1][K] = 1;
            for(int N = 2; N <= n; N++) { // looping through all nodes
                for(int p = 1; p <= N - 2; p++) { // looping through previous nodes
                    dp[N][K] += dp[p][K - 1] * dp[N - p - 1][K - 1];
                    dp[N][K] %= 9901;
                }

            }
        }

        pw.println((dp[n][k] - dp[n][k - 1] + 9901) % 9901);
        pw.close();
    }
}
