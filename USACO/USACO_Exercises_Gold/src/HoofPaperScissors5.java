import java.io.*;
import java.util.StringTokenizer;

public class HoofPaperScissors5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] moves = new int[n];
        for (int i = 0; i < n; i++) {
            char c = br.readLine().charAt(0);

            if (c == 'P') {
                moves[i] = 1;
            } else if (c == 'S') {
                moves[i] = 2;
            }
        }
        br.close();

        int N = n + 1;
        int K = k + 1;
        int[][][] dp = new int[N][K][3];
        for (int i = 1, I = 0; i < N; I = i, i++) {
            int temp = moves[I];
            int move = 0;
            dp[i][0][0] = dp[I][0][0];
            dp[i][0][1] = dp[I][0][1];
            dp[i][0][2] = dp[I][0][2];
            if (temp == 1) {
                move = 1;
            } else if (temp == 2) {
                move = 2;
            }
            dp[i][0][move]++;

            for (int j = 1, J = 0; j < K; J = j, j++) {
                dp[i][j][0] = Math.max(dp[I][j][0], Math.max(dp[I][J][1], dp[I][J][2]));
                dp[i][j][1] = Math.max(dp[I][j][1], Math.max(dp[I][J][2], dp[I][J][0]));
                dp[i][j][2] = Math.max(dp[I][j][2], Math.max(dp[I][J][0], dp[I][J][1]));
                dp[i][j][move]++;
            }
        }

        pw.println(Math.max(dp[n][k][0], Math.max(dp[n][k][1], dp[n][k][2])));
        pw.close();
    }
}
