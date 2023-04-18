

public class CTFA3 {
    public static void main(String[] args) {
        long total = 0;
        for (int i = 1; i <= 30; i++){
            total += binomialCoeff(30, i) * i;
        }
        total += 1;
        System.out.println(total);
    }
    private static long binomialCoeff(int n, int k) {

        // Base Cases
        if (k == 0 || k == n)
            return 1L;

        // Recur
        return binomialCoeff(n - 1, k - 1) +
                binomialCoeff(n - 1, k);
    }
}
