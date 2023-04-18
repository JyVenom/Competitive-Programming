import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FindingBorders2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        byte[] bytes = br.readLine().getBytes();

        int n = bytes.length;
        int[] max = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; i++) {
            if (max[i - l] < r - i) {
                max[i] = max[i - l];
            } else {
                l = i;
                if (r < l) {
                    r = l;
                }
                while (r < n && bytes[r] == bytes[r - l]) {
                    r++;
                }
                max[i] = r - l;
            }
        }

        for (int i = n - 1; i > 0; i--) {
            if (max[i] == n - i) {
                pw.print(max[i] + " ");
            }
        }
        pw.close();
    }
}
