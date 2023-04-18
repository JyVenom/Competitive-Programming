import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MergeArr2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }


        int[] arr = new int[n + m];
        int i = 0, j = 0;
        int k = 0;
        while (i < n && j < m) {
            if (a[i] <= b[j]) {
                arr[k] = a[i];
                i++;
            } else {
                arr[k] = b[j];
                j++;
            }
            k++;
        }
        // Just in case
        while (i < n) {
            arr[k] = a[i];
            i++;
            k++;
        }
        while (j < m) {
            arr[k] = b[j];
            j++;
            k++;
        }

        pw.print(arr[0]);
        for (int l = 1; l < arr.length; l++) {
            pw.print(" " + arr[l]);
        }

        pw.close();
    }
}
