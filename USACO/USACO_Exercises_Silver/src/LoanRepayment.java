import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class LoanRepayment {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("loan.in"));
        PrintWriter out = new PrintWriter(new File("loan.out"));

        String[] line = br.readLine().split(" ");
        long n = Long.parseLong(line[0]);
        long k = Long.parseLong(line[1]);
        long m = Long.parseLong(line[2]);

        long working = 0;
        long min = 0;
        long max = k;
        long x = k;
        do {
            long g = 0;
            for (int j = 0; j < k; j++) {
                long y = (n - g) / x;
                if (y < m)
                    y = m;
                g += y;
                if (g >= n) {
                    working = Math.max(working, x);
                    break;
                }
            }
            if (g < n) {
                max = x;
            } else {
                min = x;
            }
            x = (max + min) / 2;
        } while (max - min > 1);

        out.println(working);
        out.close();
    }
}
