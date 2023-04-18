import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ForestQueries2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        char[][] chars = new char[n][n];
        for (int i = 0; i < n; i++) {
            br.readLine().getChars(0, n, chars[i], 0);
        }
        int N = n + 1;
        int[][] forest = new int[N][N];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (chars[i][j] == '*') {
                    forest[i + 1][j + 1]++;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                forest[i][j] += forest[i][j - 1];
            }
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= n; i++) {
                forest[i][j] += forest[i - 1][j];
            }
        }

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int il = Integer.parseInt(st.nextToken());
            int jl = Integer.parseInt(st.nextToken());
            int ir = Integer.parseInt(st.nextToken());
            int jr = Integer.parseInt(st.nextToken());
            pw.println(forest[ir][jr] - forest[il - 1][jr] - forest[ir][jl - 1] + forest[il - 1][jl - 1]);
        }
        pw.close();
    }
}
