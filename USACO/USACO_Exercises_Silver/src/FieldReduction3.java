import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class FieldReduction3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));

        int n = Integer.parseInt(br.readLine());

        int[] x = new int[2];
        int[] y = new int[2];
        x[0] = Integer.MAX_VALUE;
        y[0] = Integer.MAX_VALUE;
        int[][] cows = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
            if (cows[i][0] > x[1]) {
                x[1] = cows[i][0];
            }
            if (cows[i][0] < x[0]) {
                x[0] = cows[i][0];
            }
            if (cows[i][1] > y[1]) {
                y[1] = cows[i][1];
            }
            if (cows[i][1] < y[0]) {
                y[0] = cows[i][1];
            }
        }
        Arrays.sort(cows, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(cows, Comparator.comparingInt(arr -> arr[0]));

        int ans = (x[1] - x[0]) * (y[1] - y[0]);


        pw.println(ans);
        pw.close();
    }
}
