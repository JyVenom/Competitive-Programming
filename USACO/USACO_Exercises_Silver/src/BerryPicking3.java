import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BerryPicking3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
        PrintWriter out = new PrintWriter(new File("berries.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Integer[] a = new Integer[n];
        st = new StringTokenizer(br.readLine());
        int m = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            m = Math.max(m, a[i]);
        }
        int best = 0;
        Integer[] aTemp = a.clone();
        for (int b = 1; b <= m; b++){
            int full = 0;
            for (int i = 0;i < n;i++) {
                full += aTemp[i] / b;
            }
            if (full < k / 2) {
                break;
            }
            if (full >= k) {
                best = Math.max(best, b * (k / 2));
                continue;
            }
            for (int i = 0; i < aTemp.length; i++){
                aTemp[i] -= (aTemp[i]/b) * b;
            }
            Arrays.sort(aTemp, Collections.reverseOrder());
            int cur = b * (full - k / 2);
            for (int i = 0;i < n && i + full < k;i++)
            {
                cur += aTemp[i];
            }
            best = Math.max(best,cur);
            aTemp = a.clone();
        }
        out.println(best);
        out.close();
    }
}
