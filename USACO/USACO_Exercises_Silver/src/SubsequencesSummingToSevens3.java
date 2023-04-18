import java.io.*;
import java.util.Arrays;

public class SubsequencesSummingToSevens3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("div7.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));

        int n = Integer.parseInt(br.readLine());
        int[] first = new int[7];
        int[] last = new int[7];
        Arrays.fill(first, 50001);
        first[0] = 0;
        int cur = 0;
        for (int i = 1; i <= n; i++) {
            cur += Integer.parseInt(br.readLine());
            cur %= 7;
            first[cur] = Math.min(first[cur], i);
            last[cur] = i;
        }
        int max = 0;
        for (int i = 0; i < 7; i++) {
            if (first[i] <= n) {
                max = Math.max(max, last[i] - first[i]);
            }
        }
        pw.println(max);
        pw.close();
    }
}