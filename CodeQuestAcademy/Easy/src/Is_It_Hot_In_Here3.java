import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Is_It_Hot_In_Here3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            while (n-- > 0) {
                String s = br.readLine();
                StringTokenizer st = new StringTokenizer(s);
                double temp = Double.parseDouble(st.nextToken());
                String type = st.nextToken();

                if (type.equals("C")) {
                    double f = (temp * (9.0 / 5.0)) + 32.0;
                    pw.println(s + " = " + Math.round(f) + " F");
                } else {
                    double c = (temp - 32.0) * (5.0 / 9.0);
                    pw.println(s + " = " + Math.round(c) + " C");
                }
            }
        }

        pw.close();
    }
}
