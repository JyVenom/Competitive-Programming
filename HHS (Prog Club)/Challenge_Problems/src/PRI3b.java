import java.io.*;

public class PRI3b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int less = 0;
        for (int i = n; i >= 0; i--) {
            if (isPrime(i)) {
                less = i;
                break;
            }
        }
        int more = 0;
        for (int i = n + 1; i < Integer.MAX_VALUE; i++) {
            if (isPrime(i)) {
                more = i;
                break;
            }
        }

        pw.println((more - n) < (n - less) ? more : less);
        pw.close();
    }

    private static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        else if (num == 2) {
            return true;
        }
        else if (num % 2 == 0) {
            return false;
        }
        else {
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
