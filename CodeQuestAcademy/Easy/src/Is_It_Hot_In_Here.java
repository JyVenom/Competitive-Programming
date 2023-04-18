import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Is_It_Hot_In_Here {
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
                    pw.println(s + " = " + round(f) + " F");
                } else {
                    double c = (temp - 32.0) * (5.0 / 9.0);
                    pw.println(s + " = " + round(c) + " C");
                }
            }
        }

        pw.close();
    }

    private static String round(double num) {
        boolean isNegative = false;

        num *= 100;

        int temp = (int) num;

        if (temp < 0) {
            if (temp <= -5) {
                isNegative = true;
            }

            temp *= -1;
        }

        int rounded = temp / 10;

        if ((temp % 10) >= 5) {
            rounded++;
        }

        return (isNegative ? "-" : "") + (rounded / 10) + "." + (rounded % 10);
    }
}
