import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class DiamondCollector {
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
        int start = 0;
        for (int i = 1; i < n; i++) {
            if (d[i] - d[start] > k) {
                all.add(i - start);
                start = i;
            }
            if (i == n - 1) {
                all.add(n - start);
            }
        }
        Collections.sort(all);

        pw.println(all.get(all.size() - 1) + all.get(all.size() - 2));
        pw.close();
    }
}
