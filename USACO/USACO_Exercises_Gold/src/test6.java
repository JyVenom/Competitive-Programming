import java.io.IOException;
import java.util.Arrays;

public class test6 {
    public static void main(String[] args) throws IOException {
        int n = 10000;
        int[][] arr = new int[n][n];
        long start, end, sum = 0;
        int t = 100;
        for (int j = 0; j < t; j++) {
            start = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                Arrays.fill(arr[i], 100000000);
            }
            end = System.currentTimeMillis();
            sum += (end - start);
        }
        System.out.println(sum);
    }
}
