import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Prob28 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            char[][] data = new char[y][x];
            for (int i = 0; i < y; i++) {
                String s = br.readLine();
                for (int j = 0; j < x; j++) {
                    data[i][j] = s.charAt(j);
                }
            }


            int tRow = -1, tCol = -1;
            loop:
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    if (data[i][j] == 'T') {
                        tRow = i;
                        tCol = j;
                        break loop;
                    }
                }
            }
            int mRow = -1, mCol = -1;
            loop:
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    if (data[i][j] == 'T') {
                        mRow = i;
                        mCol = j;
                        break loop;
                    }
                }
            }
        }

        pw.close();
    }

    private static double calcDist(int row1, int col1, int row2, int col2) {
        return Math.sqrt(Math.pow((row1 - row2), 2) + Math.pow((col1 - col2), 2));
    }
}
