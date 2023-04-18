import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditDistance {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int n = a.length;
        int m = b.length;
        int N = n + 1;
        int M = m + 1;
        int[] prev = new int[M];
        for (int i = 1; i < M; i++) {
            prev[i] = i;
        }
        int j, J;
        for (int i = 1, I = 0; i < N; i++, I++) {
            int[] cur = new int[M];
            cur[0] = i;
            for (j = 1, J = 0; j < M; j++, J++) {
                cur[j] = a[I] == b[J] ? prev[J] : (Math.min(prev[J], Math.min(prev[j], cur[J])) + 1);
            }
            prev = cur;
        }

        System.out.print(prev[m]);
    }
}
