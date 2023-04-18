import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Prob04 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String num = st.nextToken();
            String type = st.nextToken();
            if (type.equals("PARENTHESES")) {
                pw.println("(" + num.substring(0, 3) + ") " + num.substring(3, 6) + "-" + num.substring(6));
            } else if (type.equals("DASHES")) {
                pw.println(num.substring(0, 3) + "-" + num.substring(3, 6) + "-" + num.substring(6));
            } else {
                pw.println(num.substring(0, 3) + "." + num.substring(3, 6) + "." + num.substring(6));
            }
        }

        pw.close();
    }
}
