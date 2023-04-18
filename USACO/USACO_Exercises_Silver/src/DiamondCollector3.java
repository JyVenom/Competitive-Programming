import java.io.*;
import java.util.*;

public class DiamondCollector3 {
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
        for (int i = 0; i < n; i++) {
            boolean end = false;
            for (int j = i + 1; j < n; j++) {
                if (d[j] - d[i] > k) {
                    all.add(new int[]{j - i, i, j - 1});
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
//        all.sort(Comparator.comparingInt(a -> a[2]));
//        all.sort(Comparator.comparingInt(a -> a[1]));
        all.sort(Comparator.comparingInt(a -> a[0]));
        Collections.reverse(all);
        int[] cur = all.get(0);
        int ans = cur[0];
        int start = cur[1];
        int end = cur[2];
        int size = all.size();
        for (int i = 1; i < size; i++) {
            cur = all.get(i);

            if (cur[2] < start || cur[1] > end) {
                ans += cur[0];
                break;

            }

//            if (!((cur[1] >= start && cur[1] <= end) || (cur[2] >= start && cur[2] <= end))) {
//                ans += cur[0];
//                break;
//            }

//            if ((cur[1] >= start && cur[1] <= end) || (cur[2] >= start && cur[2] <= end)) {
//                continue;
//            }
//            ans += cur[0];
//            break;
        }

        pw.println(ans);
        pw.close();
    }
}
