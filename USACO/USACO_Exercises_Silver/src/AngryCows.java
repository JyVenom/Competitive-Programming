import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AngryCows {
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
                for (int l = j - 1, L = j; l > 0; l--, L--) {
                    int temp = (hays[j] - hays[L] + 1) / 2;
                    cur[j] = Math.min(cur[j], Math.max(temp, prev[l]));
                    if (temp >= prev[l]) {
                        break;
                    }
                }
            }
        }

        pw.println(cur[n - 1]);
        pw.close();
    }
}
