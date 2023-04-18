import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Prob14 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] shift = new int[st.countTokens()];
            for (int i = 0; i < shift.length; i++) {
                shift[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            int[] dir = new int[st.countTokens()];
            for (int i = 0; i < dir.length; i++) {
                dir[i] = Integer.parseInt(st.nextToken());
            }
            int c1 = 0;
            int c2 = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                    if (dir[c2] == 1) {
                        pw.print((char) (((s.charAt(i) - 'A' - shift[c1] + 26) % 26) + 'a'));
                    } else {
                        pw.print((char) (((s.charAt(i) - 'A' + shift[c1]) % 26) + 'a'));
                    }
                    c1++;
                    c2++;
                    c1 %= shift.length;
                    c2 %= dir.length;
                } else pw.print(s.charAt(i));
            }
            pw.println();
        }

        pw.close();
    }
}
