import java.io.*;
import java.util.StringTokenizer;

public class TheBovineShuffle2 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] moves = new int[n];
        for (int i = 0; i < n; i++) {
            moves[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        System.out.println(System.currentTimeMillis() - start);

        int count = 0;
        int[] group = new int[n];
        int curGroup = 0;
        int[] loc = new int[n];
        for (int i = 0; i < n; i++) {
            if (group[i] > 0) {
                continue;
            }

            curGroup++;
            int cur = i;
            int temp = 0;
            while (group[cur] == 0) {
                group[cur] = curGroup;
                loc[cur] = temp++;
                cur = moves[cur];
            }
            if (group[cur] == curGroup) {
                count += temp - loc[cur];
            }
        }

        pw.println(count);
        pw.close();
    }
}
