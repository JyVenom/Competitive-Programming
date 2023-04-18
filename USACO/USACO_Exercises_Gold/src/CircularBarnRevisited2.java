import java.io.*;
import java.util.StringTokenizer;

public class CircularBarnRevisited2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn2.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = Integer.parseInt(br.readLine());
        }

        long min = helper(new int[k], r, 0, 0, n, k, Long.MAX_VALUE);

        pw.println(min);
        pw.close();
    }

    private static long helper(int[] open, int[] r, int at, int start, int n, int k, long min) {
        if (at == k) {
            min = Math.min(min, findSum(open, r, n, k));
            return min;
        }

        for (int i = start; i < n; i++) {
            open[at] = i;
            min = helper(open, r, at + 1, i + 1, n, k, min);
        }
        return min;
    }

    private static long findSum(int[] open, int[] r, int n, int k) {
        long sum = 0;
        for (int i = 0; i < k; i++) {
            int cur = open[i];
            int temp = 0;
            while (cur != open[(i + 1) % k]) {
                sum += r[cur] * temp;
                cur = (cur + 1) % n;
                temp++;
            }
        }
        return sum;
    }
}
