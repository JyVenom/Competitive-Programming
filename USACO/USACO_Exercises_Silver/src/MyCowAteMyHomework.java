import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MyCowAteMyHomework {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("homework.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));

        int n = Integer.parseInt(br.readLine());
        int[] scores = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> ks = new ArrayList<>();
        int N = n - 1;
        int sum = scores[N];
        int min = scores[N];
        double max = 0;
        for (int i = N - 1, num = 1; i > 0; i--, num++) {
            sum += scores[i];
            min = Math.min(min, scores[i]);

            double avg = (double) (sum - min) / (double) num;
            if (avg > max) {
                max = avg;
                ks.clear();
            }
            if (avg == max) {
                ks.add(i);
            }
        }
        Collections.sort(ks);

        for (int k : ks) {
            pw.println(k);
        }
        pw.close();
    }
}
