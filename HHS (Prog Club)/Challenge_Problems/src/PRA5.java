import java.io.*;
import java.util.StringTokenizer;

public class PRA5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (order[i] > order[i + 1]) {
                ans = i + 1;
                break;
            }
        }

        pw.println(ans);
        pw.close();
    }
}
