import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MaximizeMin3 {
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
        int at = 0;
        for (int i = 1; i < m; i++) {
            int tmp = binSearch(arr, arr[at] + dist, at);
            if (tmp == arr.length) {
                return false;
            }
            at = tmp;
        }
        return true;
    }

    private static int binSearch(int[] arr, int key, int low) {
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else if (arr[mid] == key) {
                return mid;
            }
        }
        return low;
    }
}
