import java.io.*;
import java.util.ArrayList;

public class Censoring2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("censor.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));

        String s = br.readLine();
        String t = br.readLine();
        br.close();

        int tHash = t.hashCode();
        int sLength = s.length();
        int tLength = t.length();
        StringBuilder ans = new StringBuilder(s.substring(0, tLength - 1));
        for (int i = tLength - 1; i < sLength; i++) {
            ans.append(s, i, i + 1);
            int end = ans.length() - tLength;
            if (end >= 0) {
                String last = ans.substring(end);
                if (last.hashCode() == tHash) {
                    if (last.equals(t)) {
                        ans.delete(end, ans.length());
                    }
                }
            }
        }

        pw.println(ans);
        pw.close();
    }
}