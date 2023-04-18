import java.io.*;
import java.util.StringTokenizer;

public class PRI5_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));

        int n = Integer.parseInt(br.readLine());
        int[][] times = new int[n][2];
        int rhs = 0;
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i][0] = Integer.parseInt(st.nextToken());
            times[i][1] = Integer.parseInt(st.nextToken());
            rhs = Math.max(rhs, Math.max(times[i][0], times[i][1]));
        }

        int sum = 0;
        int[] on = new int[rhs + 1];
        for (int[] time : times) {
            for (int j = time[0]; j < time[1]; j++) {
                if (on[j] == 0) {
                    sum++;
                }
                on[j]++;
            }
        }
        int max = 0;
        for (int[] time : times) {
            int cur = sum;
            for (int j = time[0]; j < time[1]; j++) {
                if (on[j] == 1) {
                    cur--;
                }
            }
            max = Math.max(max, cur);
        }

        pw.println(max);
        pw.close();
    }
}
