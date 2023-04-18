import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class NoTimeToPaint4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] fence = new int[n];
        String line = br.readLine();
        for (int i = 0; i < n; i++) {
            fence[i] = line.charAt(i) - 'A';
        }

        HashSet<Integer> helper = new HashSet<>();
        int[] fow = new int[n];
        boolean[] cont = new boolean[26];
        helper.add(fence[0]);
        fow[0] = 1;
        cont[fence[0]] = true;
        for (int i = 1, I = 0; i < n; i++, I++) {
            fow[i] = fow[I];
            if (fence[i] > fence[I]) {
                fow[i]++;
                cont[fence[i]] = true;
            } else if (fence[i] < fence[I]) {
                for (int j = fence[i] + 1; j < 26; j++) {
                    cont[j] = false;
                }
                if (!helper.contains(fence[i]) || !cont[fence[i]]) {
                    fow[i]++;
                    cont[fence[i]] = true;
                }
            }
            helper.add(fence[i]);
        }
        int N = n - 1;
        helper = new HashSet<>();
        int[] rev = new int[n];
        cont = new boolean[26];
        helper.add(fence[N]);
        rev[N] = 1;
        cont[fence[N]] = true;
        for (int i = N - 1, I = N; i >= 0; i--, I--) {
            rev[i] = rev[I];
            if (fence[i] > fence[I]) {
                rev[i]++;
                cont[fence[i]] = true;
            } else if (fence[i] < fence[I]) {
                for (int j = fence[i] + 1; j < 26; j++) {
                    cont[j] = false;
                }
                if (!helper.contains(fence[i]) || !cont[fence[i]]) {
                    rev[i]++;
                    cont[fence[i]] = true;
                }
            }
            helper.add(fence[i]);
        }
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            if (a == 0) {
                if (b == N) {
                    pw.println(0);
                } else {
                    pw.println(rev[b + 1]);
                }
            } else if (b == N) {
                pw.println(fow[a - 1]);
            } else {
                pw.println(fow[a - 1] + rev[b + 1]);
            }
        }

        pw.close();
    }
}
