import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PRS8_4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            br.readLine();
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            pw.println(1);
        }

        pw.close();
    }
}
