public class AMC1 {
    public static void main(String[] args) {
        long max = 0;
        for (long a = 9; a >= 0; a--) {
            for (long b = 9; b >= 0; b--) {
                for (long c = 9; c >= 0; c--) {
                    long count = 0;
                    for (long n = 1; n < 1000; n++) {
                        long A = a * (((long) Math.pow(10, n) - 1) / 9);
                        long B = a * (((long) Math.pow(10, n) - 1) / 9);
                        long C = a * (((long) Math.pow(10, 2 * n) - 1) / 9);

                        if (C - B == Math.pow(A, 2)) {
                            count++;
                            if (count == 2) {
                                max = Math.max(max, a + b + c);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(max);
    }
}
