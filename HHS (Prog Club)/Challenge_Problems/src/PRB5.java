import java.io.*;
import java.util.StringTokenizer;

public class PRB5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int km = k * m;
        int kn = k * n;
        char[][] out = new char[km][kn];
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char s = line.charAt(j);
                int ki = k * i;
                int kj = k * j;
                for (int l = 0; l < k; l++) {
                    for (int o = 0; o < k; o++) {
                        out[ki + l][kj + o] = s;
                    }
                }
            }
        }

        for (int i = 0; i < km; i++) {
            for (int j = 0; j < kn; j++) {
                pw.print(out[i][j]);
            }
            pw.println();
        }
        pw.close();
    }
}
