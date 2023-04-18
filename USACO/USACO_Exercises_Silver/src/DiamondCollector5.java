import java.io.*;
import java.util.*;

public class DiamondCollector5 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
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

        int last = 1;
        ArrayList<int[]> all = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean end = false;
            for (int j = last; j < n; j++) {
                if (d[j] - d[i] > k) {
                    all.add(new int[]{j - i, i, j - 1});
                    last = j;
                    break;
                }
                if (j == n - 1) {
                    all.add(new int[]{n - i, i, j});
                    end = true;
                    break;
                }
            }
            if (end) {
                break;
            }
        }
        all.sort(Comparator.comparingInt(a -> a[0]));
//        Collections.reverse(all);
        int size = all.size();
        int max = 0;
        for (int j = 0; j < size; j++) {
            int ans;
            int[] cur = all.get(j);
            ans = cur[0];
            int sta = cur[1];
            int end = cur[2];
            for (int i = j + 1; i < size; i++) {
                cur = all.get(i);

                if (cur[2] < sta || cur[1] > end) {
                    max = Math.max(max, ans + cur[0]);
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
        System.out.println(System.currentTimeMillis() - start);
    }
}
