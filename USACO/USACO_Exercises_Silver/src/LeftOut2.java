import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class LeftOut2 {
    private static char[][] cows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("leftout.in"));
        PrintWriter out = new PrintWriter(new File("leftout.out"));

        int n = Integer.parseInt(br.readLine());
        cows = new char[n][n];

        String line;
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == 'L'){
                    cows[i][j] = 'T';
                }
                else {
                    cows[i][j] = 'F';
                }
            }
        }

        for (int i = 1; i < n; i++) {
            cows[i][0] = (char) (cows[i][0] ^ cows[0][0]);
            for (int j = 1; j < n; j++) {
                cows[i][j] = (char) (cows[i][j] ^ cows[0][j] ^ cows[i][0]);
            }
        }

        if (num(1, 1, n - 1, n - 1, 0) == 0) {
            out.println("1 1");
            out.close();
        }
        if (num(1, 1, n - 1, n - 1, 1) == n - 1) {
            for (int j = 1; j < n; j++) {
                if (num(1, j, n - 1, j, 1) == n - 1)
                {
                    out.println("1 " + (j + 1));
                    out.close();
                }
            }
            for (int i = 1; i < n; i++) {
                if (num(i, 1, i, n - 1, 1) == n - 1)
                {
                    out.println((i + 1) + " 1");
                    out.close(); 
                }
            }
            out.println(-1);
            out.close();
        }
        if (num(1, 1, n - 1, n - 1, 1) != 1) {
            out.println(-1);
            out.close();
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (cows[i][j] == 1) {
                    out.println((i + 1) + " " + (j + 1));
                }
            }
        }
    }

    private static int num(int i1, int j1, int i2, int j2, int b) {
        int total = 0;
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                if (cows[i][j] == b) {
                    total++;
                }
            }
        }
        return total;
    }
}
