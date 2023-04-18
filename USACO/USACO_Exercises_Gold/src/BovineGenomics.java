import java.io.*;
import java.util.StringTokenizer;

public class BovineGenomics {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] spotty = new int[n][m];
        int[][] plain = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);

                if (c == 'A') {
                    spotty[i][j] = 0;
                }
                else if (c == 'C') {
                    spotty[i][j] = 1;
                }
                else if (c == 'G') {
                    spotty[i][j] = 2;
                }
                else if (c == 'T') {
                    spotty[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);

                if (c == 'A') {
                    plain[i][j] = 0;
                }
                else if (c == 'C') {
                    plain[i][j] = 1;
                }
                else if (c == 'G') {
                    plain[i][j] = 2;
                }
                else if (c == 'T') {
                    plain[i][j] = 3;
                }
            }
        }
        br.close();

        

        pw.close();
    }
}
