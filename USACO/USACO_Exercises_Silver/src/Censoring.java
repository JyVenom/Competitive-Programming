import java.io.*;

public class Censoring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("censor.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));

        String s = br.readLine();
        String t = br.readLine();
        br.close();

        int at;
        int length = t.length();
        int start = 0;
        for (int i = 0; i < s.length() - length; i++) {
            if (s.substring(i, i + length).equals(t)) {
                start = i;
                break;
            }
        }
        at = Math.max(0, start - length + 1);
        while (start != -1) {
            s = s.substring(0, start) + s.substring(start + length);

            start = s.indexOf(t, at);
            at = Math.max(0, start - length + 1);
        }

        pw.println(s);
        pw.close();
    }
}
