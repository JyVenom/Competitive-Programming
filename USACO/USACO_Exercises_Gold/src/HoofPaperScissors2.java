import java.io.*;
import java.util.StringTokenizer;

public class HoofPaperScissors2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] data = new int[n][3];
        int[] data2 = new int[n];
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
                data2[i] = 1;
            } else {
                data[i][2]++;
                data2[i] = 2;
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
                int h, p, s;
                if (data2[j] == 0) {
                    h = 1;
                    p = 0;
                    s = 0;
                } else if (data2[j] == 1) {
                    h = 0;
                    p = 1;
                    s = 0;
                } else {
                    h = 0;
                    p = 0;
                    s = 1;
                }

                for (int l = J; l >= i; l--) {
                    int L = l - 1;
                    if (data2[l] == 0) {
                        h++;
                    } else if (data2[l] == 1) {
                        p++;
                    } else {
                        s++;
                    }

                    dp[i][j] = Math.max(dp[i][j], dp[I][L] + Math.max(h, Math.max(p, s)));
                }
            }
        }

        pw.println(dp[k][n - 1]);
        pw.close();
    }
}
