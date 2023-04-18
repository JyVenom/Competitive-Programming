import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PRA2_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int B = b - 1;
        int C = c - 1;
        int washes = 0;
        int remB = b;
        int remC = c;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());

            if (cur == 0) {
                if (remB == 0 ){
                    remB = B;
                    remC = c;
                    washes++;
                }
                else {
                    remB--;
                }
            }
            else if (cur == 1) {
                if (remC == 0 ){
                    remC = C;
                    remB = b;
                    washes++;
                }
                else {
                    remC--;
                }
            }
        }

        pw.println(washes);
        pw.close();
    }
}
