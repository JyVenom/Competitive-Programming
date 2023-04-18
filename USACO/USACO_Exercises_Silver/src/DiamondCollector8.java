import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class DiamondCollector8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(d);

        ArrayList<int[]> all = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < n; i++) {
            if (d[i] - d[start] > k) {
                all.add(new int[]{i - start, start, i - 1});
                for (int j = start + 1; j < n; j++) {
                    if (d[i] - d[j] <= k) {
                        start = j;
                        break;
                    }
                }
            }
            else if (i == n - 1) {
                all.add(new int[]{n - start, start, i});
                break;
            }
        }
        all.sort(Comparator.comparingInt(a -> a[0]));
//        Collections.reverse(all);
        int size = all.size();
        int max = 0;
        for (int j = size - 1; j >= 0; j--) {
            int ans;
            int[] cur = all.get(j);
            ans = cur[0];
            int sta = cur[1];
            int end = cur[2];
            for (int i = j - 1; i >= 0; i--) {
                cur = all.get(i);

                if (cur[2] < sta || cur[1] > end) {
                    max = Math.max(max, ans + cur[0]);
//                    good = true;
                    break;
                }
                if (cur[1] < sta && cur[2] > sta) {
                    max = Math.max(max, ans + sta - cur[1]);
                }
                if (cur[2] > end && cur[1] < end) {
                    max = Math.max(max, ans + cur[2] - end);
                }
            }
        }

        pw.println(max);
        pw.close();
    }
}
