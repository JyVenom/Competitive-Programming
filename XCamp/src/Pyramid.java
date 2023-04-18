import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Pyramid {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            pw.print(" ".repeat(n - i));
            pw.print("*");
            for (int j = 1; j < i; j++) {
                pw.print(" *");
            }
            if (i<n) {
                pw.println();
            }
        }

        pw.close();
    }
}
