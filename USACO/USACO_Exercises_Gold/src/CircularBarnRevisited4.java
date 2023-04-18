import java.io.*;
import java.util.StringTokenizer;

public class CircularBarnRevisited4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn2.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = Integer.parseInt(br.readLine());
        }

        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) { //start
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) { //shift by i
                arr[j] = r[(j + i) % n];
            }

            long[][] dp = new long[k][n]; //init dp and 1 door
            for (int j = 1; j < n; j++) {
                dp[0][j] = dp[0][j - 1] + ((long) j) * ((long) arr[j]);
            }

            for (int j = 1; j < k; j++) { //use j doors (starts at 1 because already processed 0)
                for (int l = j + 1; l < n; l++) { //go up to l doors (dp)
                    dp[j][l] = dp[j - 1][l - 1];
                    long cows = arr[l];
                    long sum = cows;
                    for (int m = l - 1; m > 0; m--) { //put the door at m instead of at l
                        dp[j][l] = Math.min(dp[j][l], dp[j - 1][m - 1] + sum);
                        cows += arr[m];
                        sum += cows;
                    }
                }
            }

            min = Math.min(min, dp[k - 1][n - 1]);
        }

        pw.println(min);
        pw.close();
    }
}