import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class WeirdAlgorithm2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        long n = Long.parseLong(br.readLine());

        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");
        long cur = n;
        while (cur != 1) {
            if (cur % 2 == 0) {
                cur /= 2;
            } else {
                cur = cur * 3 + 1;
            }
            sb.append(cur).append(" ");
        }

        pw.print(sb);
        pw.close();
    }
}
