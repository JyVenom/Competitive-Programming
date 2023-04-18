import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CoinPiles {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            pw.println(findAns(a, b) ? "YES" : "NO");
        }

        pw.close();
    }

    private static boolean findAns(int a, int b) {
        int dif = Math.abs(a - b);
        if (dif > Math.min(a, b)) {
            return false;
        }
        if (a > b) {
            b -= dif;
            b = b % 3;
            return b == 0;

        } else if (a == b) {
            a = a % 3;
            return a == 0;
        } else {
            a -= dif;
            a = a % 3;
            return a == 0;
        }
    }
}
