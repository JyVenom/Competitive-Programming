import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WeirdAlgorithm {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        long n = Long.parseLong(br.readLine());

        ArrayList<Long> ans = new ArrayList<>();
        long cur = n;
        while (cur != 1) {
            if (cur % 2 == 0) {
                cur /= 2;
            } else {
                cur = cur * 3 + 1;
            }
            ans.add(cur);
        }

        pw.print(n);
        for (Long an : ans) {
            pw.print(" " + an);
        }
        pw.close();
    }
}
