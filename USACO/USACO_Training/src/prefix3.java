/*
ID: jerryya2
LANG: JAVA
TASK: prefix
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class prefix3 {
    private static int min = 200000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("prefix.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));

        String line = br.readLine();
        StringTokenizer st;
        ArrayList<String> p = new ArrayList<>();
        while (!line.equals(".")) {
            st = new StringTokenizer(line);
            int size = st.countTokens();
            for (int i = 0; i < size; i++) {
                p.add(st.nextToken());
            }
            line = br.readLine();
        }
        line = br.readLine();
        StringBuilder s = new StringBuilder();
        while (line != null) {
            s.append(line);
            line = br.readLine();
        }

        int len = s.length();
        if (works(p, s)) {
            pw.println(len);
        }
        else {
            pw.println(len - min);
        }

        pw.close();
    }

    private static boolean works (ArrayList<String> p, StringBuilder s) {
        int sLen = s.length();
        min = Math.min(min, sLen);
        if (sLen == 0) {
            return true;
        }
        int size = p.size();
        for (int i = 0; i < size; i++) {
            String prim = p.get(i);
            int len = prim.length();
            if (len > sLen) {
                continue;
            }
            boolean same = true;
            for (int j = 0; j < len; j++) {
                if (s.charAt(j) != prim.charAt(j)) {
                    same = false;
                    break;
                }
            }
            if (same) {
                s.delete(0, len);
                if (works(p, s)) {
                    return true;
                }
                s.insert(0, prim);
            }
        }
        return false;
    }
}
