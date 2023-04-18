import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MaximizeMin4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(x);
        int lhs = 0, rhs = x[n - 1] - x[0];
        while (lhs <= rhs) {
            int mid = (lhs + rhs) / 2;

            if (isPos(x, mid, m)) {
                lhs = mid + 1;
            } else
                rhs = mid - 1;
        }

        pw.println(rhs);
        pw.close();
    }

    private static boolean isPos(int[] arr, int dist, int m) {
        int count = 1;
        int at = arr[0] + dist;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= at) {
                count++;
                if (count == m) {
                    return true;
                }
                at = arr[i] + dist;
            }
        }
        return false;
    }
}
