import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PRA2_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] used = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int min = helper(used, b, c, 0, Integer.MAX_VALUE, 0, n, b, c);

        pw.println(min);
        pw.close();
    }

    private static int helper(int[] used, int remB, int remC, int washes, int min, int at, int n, int b, int c) {
        if (at == n) {
            min = Math.min(min, washes);
            return min;
        }

        if (used[at] == 0) {
            if (remB == 0){
                remB = b - 1;
                remC = c;
                washes++;
            }
            else {
                remB--;
            }
            int temp = helper(used, remB, remC, washes, min, at + 1, n, b, c);
            if (temp < min) {
                min = temp;
            }
        }
        else if (used[at] == 1) {
            if (remC == 0){
                remC = c - 1;
                remB = b;
                washes++;
            }
            else {
                remC--;
            }
            int temp = helper(used, remB, remC, washes, min, at + 1, n, b, c);
            if (temp < min) {
                min = temp;
            }
        }
        else {
            int temp;

            if (remB == 0) {
                temp = helper(used, b - 1, c, washes + 1, min, at + 1, n, b, c);
            }
            else {
                temp = helper(used, remB - 1, remC, washes, min, at + 1, n, b, c);
            }
            if (temp < min) {
                min = temp;
            }

            if (remC == 0) {
                temp = helper(used, b, c - 1, washes + 1, min, at + 1, n, b, c);
            }
            else {
                temp = helper(used, remB, remC - 1, washes, min, at + 1, n, b, c);
            }
            if (temp < min) {
                min = temp;
            }
        }

        return min;
    }
}
