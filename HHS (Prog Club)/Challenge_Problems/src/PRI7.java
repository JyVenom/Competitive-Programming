import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class PRI7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        HashSet<Integer> colors = new HashSet<>();
        int[] fence = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int c = Integer.parseInt(st.nextToken());
            colors.add(c);
            fence[i] = c;
        }

        int K = k - 1;
        int min = (n + K) / k;
        ArrayList<Integer> colors2 = new ArrayList<>(colors);
        for (int col : colors2) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (fence[i] != col) {
                    count++;
                    i += K;
                }
            }
            min = Math.min(min, count);
        }

        pw.println(min);
        pw.close();
    }
}
