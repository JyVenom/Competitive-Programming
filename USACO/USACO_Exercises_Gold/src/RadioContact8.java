import java.io.*;
import java.util.StringTokenizer;

public class RadioContact8 {
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
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i] + (posF[i + 1][0] - b[0]) * (posF[i + 1][0] - b[0]) + (posF[i + 1][1] - b[1]) * (posF[i + 1][1] - b[1]);
        }
        int[] prev = dp.clone();
        for (int i = 1; i <= m; i++) {
            dp[0] = prev[0] + (f[0] - posB[i][0]) * (f[0] - posB[i][0]) + (f[1] - posB[i][1]) * (f[1] - posB[i][1]);
            for (int j = 1; j <= n; j++) {
                int cost = (posF[j][0] - posB[i][0]) * (posF[j][0] - posB[i][0]) + (posF[j][1] - posB[i][1]) * (posF[j][1] - posB[i][1]);
                int up = prev[j];
                int left = dp[j - 1];
                int dia = prev[j - 1];

                int min = Math.min(up, Math.min(left, dia)) + cost;
                dp[j] = min;
            }
            prev = dp.clone();
        }
        

        pw.println(dp[n]);
        pw.close();
    }
}
