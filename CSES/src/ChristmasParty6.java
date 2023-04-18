import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChristmasParty6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.print(0);
        } else if (n == 2) {
            System.out.print(1);
        } else {
            long[] facts = new long[n];
            findFacts(facts, n);
            long[] inv = new long[n];
            findFactInvs(inv, facts, n);
            int N = n + 1;
            long[] num = new long[N];
            num[2] = 1;
            long nfact = facts[n - 1];
            for (int i = 3; i < N; i++) {
                num[i] = facts[i - 1];
                int end = i - 2;
                for (int j = 2; j <= end; j++) {
                    num[i] += (((((nfact * inv[j - 1]) % 1000000007) * inv[n - j]) % 1000000007) * num[i - j]) % 1000000007;
                }
            }
            System.out.print(num[n]);
        }
    }

    private static long binPow(long a, long b) {
        assert (b >= 0);
        a %= 1000000007; //note: m*m must be less than 2^63 to avoid ll overflow
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1) //if n is odd
                res = res * a % 1000000007;
            a = a * a % 1000000007;
            b /= 2; //divide by two
        }
        return res;
    }

    private static void findFacts(long[] fact, int n) {
        long ans = 1;
        for (int i = 1; i < n; i++) {
            ans *= i;
            ans %= 1000000007;
            fact[i] = ans;
        }
    }

    private static void findFactInvs(long[] inv, long[] fact, int n) {
        for (int i = 1; i < n; i++) {
            inv[i] = binPow(fact[i], 1000000005);
        }
    }
}
