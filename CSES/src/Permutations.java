import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Permutations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            pw.println(1);
        } else if (n < 4) {
            pw.println("NO SOLUTION");
        } else if (n == 4) {
            pw.println("2 4 1 3");
        } else {
            pw.print("1");
            for (int i = 3; i <= n; i += 2) {
                pw.print(" " + i);
            }
            for (int i = 2; i <= n; i += 2) {
                pw.print(" " + i);
            }
        }

        pw.close();
    }
}
