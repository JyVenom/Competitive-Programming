import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class DiamondCollector2 {
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

        ArrayList<Integer> all = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int start = -1;
            for (int j = i + 1; j < n; j++) {
                if (d[j] - d[i] > k) {
                    sum += (j - i);
                    start = j;
                    break;
                }
                if (j == n - 1) {
                    sum += (n - i);
                    break;
                }
            }
            if (start != -1) {
                int max = 0;
                for (int j = start; j < n; j++) {
                    for (int l = j + 1; l < n; l++) {
                        if (d[l] - d[j] > k) {
                            max = Math.max(max, j - i);
                            break;
                        }
                        if (l == n - 1) {
                            max = Math.max(max, n - j);
                            break;
                        }
                    }
                }
                all.add(sum + max);
            }
        }
        Collections.sort(all);

        pw.println(all.get(all.size() - 1));
        pw.close();
    }
}
