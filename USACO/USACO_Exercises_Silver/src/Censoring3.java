import java.io.*;
import java.util.ArrayList;

public class Censoring3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("censor.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));

        String s = br.readLine();
        String t = br.readLine();
        br.close();

        int len = t.length();
        long tHash = 0;
        for (int i = 0; i < len; i++) {
            tHash = hash(tHash, t.charAt(i) - 'a');
        }

        StringBuilder r = new StringBuilder();
        ArrayList<Long> rHash = new ArrayList<>();
        ArrayList<Long> HAPW = new ArrayList<>();
        rHash.add((long) 0);
        HAPW.add((long) 1);
        int len2 = s.length();
        for (int i = 0; i < len2; i++) {
            r.append(s.charAt(i));

            if (rHash.size() > 0) {
                rHash.add(hash(rHash.get(rHash.size() - 1), s.charAt(i) - 'a'));
            }

            if (HAPW.size() > 0) {
                HAPW.add((HAPW.get(HAPW.size() - 1) * 100000007) % 1000000007);
            }

            if (r.length() >= len) {
                long hSub = (rHash.get(r.length() - t.length()) * HAPW.get(t.length())) % 1000000007;
                long hsh = (1000000007 + rHash.get(rHash.size() - 1) - hSub) % 1000000007;

                if (hsh == tHash && t.equals(r.substring(r.length() - t.length()))) {
                    r.delete(r.length() - len, r.length());
                    for (int j = 0; j < len; j++) {
                        rHash.remove(rHash.size() - 1);

                        HAPW.remove(HAPW.size() - 1);
                    }
                }
            }
        }

        pw.println(r);
        pw.close();
    }

    private static long hash(long h, int ch) {
        return (h * 100000007 + ch + 101) % 1000000007;
    }
}