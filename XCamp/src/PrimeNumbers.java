import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PrimeNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            pw.println(isPrime(Integer.parseInt(br.readLine())) ? "Yes" : "No");
        }

        pw.close();
    }

    private static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        } else if (num == 2) {
            return true;
        } else if (num % 2 == 0) {
            return false;
        } else {
            int root = (int) Math.sqrt(num);
            for (int j = 3; j <= root; j += 2) {
                if (num % j == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
