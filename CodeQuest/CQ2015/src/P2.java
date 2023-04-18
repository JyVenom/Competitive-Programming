import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class P2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            BigInteger sum = BigInteger.ZERO;
            while (n-- > 0) {
                sum = sum.add(new BigInteger(br.readLine()));
            }

            pw.println(sum);
        }

        pw.close();
    }
}
