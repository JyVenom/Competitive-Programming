import java.io.*;
import java.util.StringTokenizer;

public class HoofPaperScissors {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] data = new int[n][3];
        String line = br.readLine();
        if (line.equals("H")) {
            data[0][0] = 1;
        } else if (line.equals("P")) {
            data[0][1] = 1;
        } else {
            data[0][2] = 1;
        }
        for (int i = 1; i < n; i++) {
            data[i] = data[i - 1].clone();
            line = br.readLine();

            if (line.equals("H")) {
                data[i][0]++;
            } else if (line.equals("P")) {
                data[i][1]++;
            } else {
                data[i][2]++;
            }
        }

        int[][] dp = new int[k + 1][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = Math.max(data[i][0], Math.max(data[i][1], data[i][2]));
        }
        for (int i = 1; i <= k; i++) {
            int I = i - 1;
            for (int j = i + 1; j < n; j++) {
                int J = j - 1;
                dp[i][j] = dp[I][J] + 1;


                for (int l = J; l >= i; l--) {
                    int L = l - 1;
                    dp[i][j] = Math.max(dp[i][j], dp[I][L] + Math.max(data[j][0] - data[L][0], Math.max(data[j][1] - data[L][1], data[j][2] - data[L][2])));
                }
            }
        }

        pw.println(dp[k][n - 1]);
        pw.close();
    }
}
