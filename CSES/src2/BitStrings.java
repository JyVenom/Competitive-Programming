import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BitStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int mod = 1000000007;

        int ans = 2;
        for (int i = 1; i < n; i++) {
            ans = ans * 2;
            ans = ans % mod;
        }

        pw.println(ans);
        pw.close();
    }
}
