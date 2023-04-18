import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SecretCowCode3 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String code = st.nextToken();
        long n = Long.parseLong(st.nextToken()) - 1;

        ArrayList<Long> all = new ArrayList<>();
        long lhs = code.length();
        all.add(lhs);
        while (lhs <= n) {
            lhs *= 2;
            all.add(lhs);
        }
        lhs /= 2;
        long ind = n - lhs;

        int len = code.length();
        while (ind > len) {
            ind--;
            long val = 0;
            for (Long start : all) {
                val = start;
                if (val * 2 > ind) {
                    break;
                }
            }
            ind -= val;
            if (ind == 0) {
                ind = val;
            }
        }

        pw.println(code.charAt((int) (ind - 1)));
        pw.close();
    }
}
