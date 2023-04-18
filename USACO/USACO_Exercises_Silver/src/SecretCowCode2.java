import java.io.*;
import java.util.StringTokenizer;

public class SecretCowCode2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String code = st.nextToken();
        long n = Long.parseLong(st.nextToken()) - 1;

        long lhs = code.length();
        while (lhs <= n) {
            lhs *= 2;
        }
        lhs /= 2;
        long rem = n - lhs;
        long rhs = lhs - 1;
        lhs /= 2;

        int len = code.length();
//        while (rem > len) {
        while (rem > len) {
            rem--;
            if (rem == 0) {
                if (rhs > len) {
                    rem = lhs - 1;
                }
            }
            else if (rem <= len) {
                break;
            }
            rhs = lhs - 1;
            lhs /= 2;
            if (rem >= rhs) {
                rem -= rhs;
                if (rem == 0) {
                    if (rhs > len) {
                        rem = lhs - 1;
                    }
                }
            }
        }

        pw.println(code.charAt((int) (rem - 1)));
        pw.close();
    }
}
