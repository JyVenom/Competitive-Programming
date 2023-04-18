import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Repetitions {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String s = br.readLine();
        s += "x";
        int n = s.length();
        int start = 0;
        int type = s.charAt(0) - 'A';
        int max = -1;
        for (int i = 1; i < n; i++) {
            int cur = s.charAt(i) - 'A';
            if (cur != type) {
                max = Math.max(max, i - start);
                start = i;
                type = cur;
            }
        }
        if (max == -1) {
            max = n;
        }

        pw.println(max);
        pw.close();
    }
}
