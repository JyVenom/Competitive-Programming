import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class LoanRepayment2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("loan.in"));
        PrintWriter out = new PrintWriter(new File("loan.out"));

        String[] line = br.readLine().split(" ");
        long n = Long.parseLong(line[0]);
        long k = Long.parseLong(line[1]);
        long m = Long.parseLong(line[2]);

        long lhs = 1; //left hand side
        long rhs = k; //right hand side
        while(lhs < rhs) {
            long mid = (lhs + rhs + 1) / 2;
            if(valid(n, k, m, mid)) {
                lhs = mid;
            }
            else {
                rhs = mid - 1;
            }
        }
        out.println(lhs);
        out.close();
    }

    private static boolean valid(long n, long k, long m, long x) {
        long g = 0;
        while(k > 0 && g < n) {
            long y = (n - g) / x;
            if(y < m) {
                long leftover = (n-g + m-1) / m;
                return leftover <= k;
            }
            long maxmatch = n - x*y;
            long numdays = (maxmatch - g) / y + 1;
            if(numdays > k) numdays = k;
            g += y * numdays;
            k -= numdays;
        }
        return g >= n;
    }
}
