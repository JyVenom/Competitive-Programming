import java.io.*;
import java.util.Arrays;

public class WhyDidTheCowCrossTheRoadII3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("nocross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(br.readLine());
        }

        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[a[i] - 1] = i + 1;
        }
        int N = n + 1;
        int[] dp = new int[N]; //could also be named min
        Arrays.fill(dp, N);
        dp[0] = -1;
        int max = 0;
        N = n - 1;
        for (int i = 0; i < n; i++) {
            int low = Math.max(b[i] - 5, 0);
            int high = Math.min(b[i] + 3, N);
            int[] temp = new int[high - low + 1];
            for (int j = low; j <= high; j++) {
                int res = binSearch(dp, A[j]) + 1;
                temp[j - low] = res;
                max = Math.max(max, res);
            }
            for (int j = low; j <= high; j++) {
                int temp2 = temp[j - low];
                dp[temp2] = Math.min(dp[temp2], A[j]);
            }
        }

        pw.println(max);
        pw.close();
    }

    public static int binSearch(int[] dp, int index) {
        int low = 0, high = dp.length - 1;

        while (low < high) {
            int mid = (low + high + 1) / 2;

            if (dp[mid] < index) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}