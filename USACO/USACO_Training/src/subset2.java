/*
ID: jerryya2
LANG: JAVA
TASK: subset
*/

import java.io.*;

public class subset2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("subset.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

        int n = Integer.parseInt(br.readLine());

        int half = 0;
        for (int i = 1; i <= n; i++) {
            half += i;
        }

        if (half % 2 == 1) {
            pw.println(0);
        } else {
            half /= 2;
            int count = 0;
            for (int i = 1; i <= n / 2; i++) {
                int max = 0;
                for (int j = 0; j < i; j++) {
                    max += n - j;
                }
                if (max < half) {
                    continue;
                }
                count += create(i, n, 1, 0, half, 0);
            }
            pw.println(count);
        }
        pw.close();
    }

    private static int create(int size, int n, int start, int sum, int half, int count) {
        for (int i = start; i <= n; i++) {
            sum += i;
            if (size == 1) {
                if (sum == half) {
                    count++;
                    return count;
                }
            } else {
                count = create(size - 1, n, i + 1, sum, half, count);
            }
            sum -= i;
        }
        return count;
    }
}