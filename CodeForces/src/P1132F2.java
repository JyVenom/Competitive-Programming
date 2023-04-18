import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class P1132F2 {
    private static final int N = 505;
    private static final int[][] dp = new int[N][N];
    private static String s;

    private static int calc(int l, int r) {
        int res = dp[l][r];
        if (res != -1) return res;

        if (l > r) return 0;
        if (l == r) return 1;

        res = 1 + calc(l + 1, r);
        for (int i = l + 1; i <= r; ++i)
            if (s.charAt(l) == s.charAt(i))
                res = Math.min(res, calc(l + 1, i - 1) + calc(i, r));
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        s = br.readLine();

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        pw.println(calc(0, n - 1));
        pw.close();
    }
}
