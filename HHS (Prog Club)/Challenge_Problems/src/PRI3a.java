import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PRI3a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] next = new int[n];
        int[] prev = new int[n];
        Arrays.fill(next, -1);
        Arrays.fill(prev, -1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken()) - 1;
            next[i] = num;
            prev[num] = i;
        }

        int N = n - 1;
        if (prev[N] == -1) {
            pw.println(-1);
        }
        else {
            boolean[] visited = new boolean[n];
            int count = 0;
            int at = 0;
            while (at != N) {
                if (visited[at]) {
                    count = -1;
                    break;
                }
                visited[at] = true;
                at = next[at];
                count++;
            }
            pw.println(count);
        }
        pw.close();
    }
}
