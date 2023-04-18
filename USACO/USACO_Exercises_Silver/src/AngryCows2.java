import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AngryCows2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] hays = new int[n];
        for (int i = 0; i < n; i++) {
            hays[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(hays);
        int[] cur = new int[n];
        for (int i = 1; i < n; i++) {
            cur[i] = (hays[i] - hays[0] + 1) / 2;
        }
        int[] prev;
        for (int i = 2; i <= k; i++) {
            prev = cur.clone();
            cur = new int[n];
            for (int j = i; j < n; j++) {
                cur[j] = prev[j];
                int temp = binSearch(prev, hays, i - 1, j, j);
                cur[j] = Math.min(cur[j], Math.max(prev[temp], (hays[j] - hays[temp + 1] + 1) / 2));
                cur[j] = Math.min(cur[j], Math.max(prev[temp - 1], (hays[j] - hays[temp] + 1) / 2));
            }
        }

        pw.println(cur[n - 1]);
        pw.close();
    }

    private static int binSearch(int[] arr, int[] data, int low, int high, int high2) {
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            int key = (data[high2] - data[mid + 1] + 1) / 2;
            if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else if (arr[mid] == key) {
                index = mid;
                break;
            }
        }
        if (index == Integer.MAX_VALUE) {
            return low;
        }
        else {
            return index;
        }
    }
}
