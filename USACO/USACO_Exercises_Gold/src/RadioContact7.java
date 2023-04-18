import java.io.*;
import java.util.StringTokenizer;

public class RadioContact7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("radio.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] f = new int[2];
        f[0] = Integer.parseInt(st.nextToken());
        f[1] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] b = new int[2];
        b[0] = Integer.parseInt(st.nextToken());
        b[1] = Integer.parseInt(st.nextToken());
        String line = br.readLine();
        int[] fPath = new int[n];
        for (int i = 0; i < n; i++) {
            fPath[i] = line.charAt(i);
        }
        line = br.readLine();
        int[] bPath = new int[m];
        for (int i = 0; i < m; i++) {
            bPath[i] = line.charAt(i);
        }
        
        int[][] posF = new int[n + 1][2];
        posF[0] = f.clone();
        for (int i = 0; i < n; i++) {
            int I = i + 1;
            posF[I] = posF[i].clone();
            if (fPath[i] == 78) {
                posF[I][1]++;
            } else if (fPath[i] == 69) {
                posF[I][0]++;
            } else if (fPath[i] == 83) {
                posF[I][1]--;
            } else if (fPath[i] == 87) {
                posF[I][0]--;
            }
        }
        int[][] posB = new int[m + 1][2];
        posB[0] = b.clone();
        for (int i = 0; i < m; i++) {
            int I = i + 1;
            posB[I] = posB[i].clone();
            if (bPath[i] == 78) {
                posB[I][1]++;
            } else if (bPath[i] == 69) {
                posB[I][0]++;
            } else if (bPath[i] == 83) {
                posB[I][1]--;
            } else if (bPath[i] == 87) {
                posB[I][0]--;
            }
        }
        int[][] dp = new int[m + 1][n + 1];
        int[] curF = f.clone();
        int[] curB = b.clone();
        for (int i = 0; i < n; i++) {
            if (fPath[i] == 78) {
                curF[1]++;
            } else if (fPath[i] == 69) {
                curF[0]++;
            } else if (fPath[i] == 83) {
                curF[1]--;
            } else if (fPath[i] == 87) {
                curF[0]--;
            }
            dp[0][i + 1] = dp[0][i] + (curF[0] - curB[0]) * (curF[0] - curB[0]) + (curF[1] - curB[1]) * (curF[1] - curB[1]);
        }
        curF = f.clone();
        for (int i = 0; i < m; i++) {
            if (bPath[i] == 78) {
                curB[1]++;
            } else if (bPath[i] == 69) {
                curB[0]++;
            } else if (bPath[i] == 83) {
                curB[1]--;
            } else if (bPath[i] == 87) {
                curB[0]--;
            }
            dp[i + 1][0] = dp[i][0] + (curF[0] - curB[0]) * (curF[0] - curB[0]) + (curF[1] - curB[1]) * (curF[1] - curB[1]);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = (posF[j][0] - posB[i][0]) * (posF[j][0] - posB[i][0]) + (posF[j][1] - posB[i][1]) * (posF[j][1] - posB[i][1]);
                int up = dp[i - 1][j];
                int left = dp[i][j - 1];
                int dia = dp[i - 1][j - 1];

                int min = Math.min(up, Math.min(left, dia)) + cost;
                dp[i][j] = min;
            }
        }
        

        pw.println(dp[m][n]);
        pw.close();
    }
}
