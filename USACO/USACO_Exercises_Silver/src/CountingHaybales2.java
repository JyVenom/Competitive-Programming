import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CountingHaybales2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long start = System.currentTimeMillis();
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] bales = new int[n];
        for (int i = 0; i < n; i++) {
            bales[i] = Integer.parseInt(st.nextToken());
        }
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i][0] = Integer.parseInt(st.nextToken());
            queries[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        Arrays.sort(bales);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        int N = n - 1;
        int[] firsts = new int[q];
        int[] lasts = new int[q];
        for (int i = 0; i < q; i++) {
            firsts[i] = binSearchFirst(bales, queries[i][0], N);
            lasts[i] = binSearchLast(bales, queries[i][1], N);
        }
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            ans[i] = lasts[i] - firsts[i];
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        for (int an : ans) {
            pw.println(an);
        }
        System.out.println(System.currentTimeMillis() - start);
        pw.close();
    }

    private static int binSearchFirst(int[] arr, int key, int high) {
        int low = 0;
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
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

    private static int binSearchLast(int[] arr, int key, int high) {
        int low = 0;
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
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
            return index + 1;
        }
    }
}
