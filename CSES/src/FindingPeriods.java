import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FindingPeriods {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        byte[] raw = br.readLine().getBytes();
        int n = raw.length;
        int[] count = new int[n];
        StringBuilder sb = new StringBuilder();
        for (int i = 1, l = 0, r = 0; i < n; i++) {
            if (count[i - l] < r - i) {
                count[i] = count[i - l];
            } else {
                l = i;
                if (r < l) {
                    r = l;
                }
                while (r < n && raw[r] == raw[r - l]) {
                    r++;
                }
                count[i] = r - l;
                if (count[i] == n - i) {
                    sb.append(i).append(" ");
                }
            }
        }
        sb.append(n);

        pw.println(sb);
        pw.close();
    }
}
