import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class P550A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String s = br.readLine();
        int first = -1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == 'B' && s.charAt(i) == 'A') {
                first = i + 1;
                break;
            }
        }
        if (first == -1) {
            pw.println("NO");
            pw.close();
            return;
        }
        int last = -1;
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i - 1) == 'B' && s.charAt(i) == 'A') {
                last = i - 1;
                break;
            }
        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == 'A' && s.charAt(i) == 'B') {
                if (first < i || last > i) {
                    pw.println("YES");
                    pw.close();
                    return;
                }
            }
        }
        pw.println("NO");
        pw.close();
    }
}
