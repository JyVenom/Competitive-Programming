import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CountingHaybales {
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
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        Arrays.sort(bales);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        int N = n - 1;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pw.println(binSearchLast(bales, b, N) - binSearchFirst(bales, a, N));
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
