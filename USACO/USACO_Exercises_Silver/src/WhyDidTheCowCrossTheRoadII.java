import java.io.*;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoadII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] all = new int[n];
        for (int i = 0; i < b; i++) {
            int cur = Integer.parseInt(br.readLine()) - 1;
            all[cur] = 1;
        }

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += all[i];
        }
        int min = sum;
        for (int i = 0; i < n - k; i++) {
            sum -= all[i];
            sum += all[i + k];
            if (sum < min) {
                min = sum;
            }
        }

        pw.println(min);
        pw.close();
    }
}
