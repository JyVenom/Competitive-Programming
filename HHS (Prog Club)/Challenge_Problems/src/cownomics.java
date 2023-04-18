import java.io.*;
import java.util.StringTokenizer;

public class cownomics {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] dataS = new boolean[m][4];
        boolean[][] dataP = new boolean[m][4];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);

                if (c == 'A') {
                    dataS[j][0] = true;
                } else if (c == 'C') {
                    dataS[j][1] = true;
                } else if (c == 'G') {
                    dataS[j][2] = true;
                } else {
                    dataS[j][3] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);

                if (c == 'A') {
                    dataP[j][0] = true;
                } else if (c == 'C') {
                    dataP[j][1] = true;
                } else if (c == 'G') {
                    dataP[j][2] = true;
                } else {
                    dataP[j][3] = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            boolean good = true;
            for (int j = 0; j < 4; j++) {
                if (dataS[i][j] && dataP[i][j]) {
                    good = false;
                    break;
                }
            }
            if (good) {
                count++;
            }
        }

        pw.println(count);
        pw.close();
    }
}
