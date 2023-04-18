import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SerpentineMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());

        int[][] ans = new int[n][n];
        int row = 0, col = 0, dir = 0, count = 1, n2 = n * n, N = n - 1; //dir 0 = up, 1 = down
        while (count <= n2) {
            ans[row][col] = count++;

            if (dir == 0) {
                if (col == N) {
                    dir = 1;
                    row++;
                } else if (row == 0) {
                    dir = 1;
                    col++;
                } else {
                    row--;
                    col++;
                }
            } else {
                if (row == N) {
                    dir = 0;
                    col++;
                }
                else if (col == 0) {
                    dir = 0;
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pw.print(ans[i][j] + " ");
            }
            pw.println();
        }
        pw.close();
    }
}
