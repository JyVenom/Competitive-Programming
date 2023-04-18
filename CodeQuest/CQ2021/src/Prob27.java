import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Prob27 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String hex = br.readLine();
            String bin = new StringBuilder(new BigInteger(hex.substring(2), 16).toString(2)).reverse().toString();

            int m = Integer.parseInt(br.readLine());
            while (m-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String type = st.nextToken();
                int offset = Integer.parseInt(st.nextToken());
                int length = Integer.parseInt(st.nextToken());

                String s = new StringBuilder(bin.substring(offset, offset + length)).reverse().toString();
                BigInteger bi = new BigInteger(s, 2);
                if (type.equals("double")) {
                    pw.printf("%6.5e", Double.longBitsToDouble(bi.longValue()));
                    pw.println();
                } else if (type.equals("float")) {
                    float f = Float.intBitsToFloat(bi.intValue());
                    pw.println(f);
                } else if (type.equals("int")) {
                    pw.println(binToInt(s));
                } else {
                    pw.println(bi.toString(10));
                }
            }
        }

        pw.close();
    }

    private static int binToInt(String bin) {
        int sum = 0;
        int cur = 1;
        for (int i = bin.length() - 1; i > 0; i--) {
            if (bin.charAt(i) == '1') {
                sum += cur;
            }
            cur *= 2;
        }
        if (bin.charAt(0) == '1') {
            sum -= cur;
        }
        return sum;
    }
}
