/*
ID: jerryya2
LANG: JAVA
TASK: zerosum
*/

import javax.script.ScriptException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class zerosum2 {
    public static void main(String[] args) throws IOException, ScriptException {
        BufferedReader br = new BufferedReader(new FileReader("zerosum.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));

        int n = Integer.parseInt(br.readLine());
        br.close();

        ArrayList<String> all = new ArrayList<>();
        generateAll(2, n, "", all);
        for (int i = 0; i < all.size(); i++) {
            String seq = all.get(i);
            int val = 1;
            for (int j = 1; j < n; j++) {
                char op = seq.charAt(j - 1);
                if (op == '+') {
                    if (j < n - 1) {
                        if (seq.charAt(j) == ' ') {
                            j++;
                            val += (j * 10 + j + 1);
                        } else {
                            val += (j + 1);
                        }
                    } else {
                        val += (j + 1);
                    }
                } else if (op == '-') {
                    if (j < n - 1) {
                        if (seq.charAt(j) == ' ') {
                            j++;
                            val -= (j * 10 + j + 1);
                        } else {
                            val -= (j + 1);
                        }
                    } else {
                        val -= (j + 1);
                    }
                }
            }
            if (val != 0) {
                all.remove(i);
                i--;
            }
        }
        Collections.sort(all);
        for (String s : all) {
            pw.println(s);
        }
        pw.close();
    }

    private static void generateAll(int cur, int max, String seq, ArrayList<String> list) {
        String copy1 = seq;
        String copy2 = seq;
        String copy3 = seq;
        copy1 += "+";
        copy2 += "-";
        copy3 += " ";
        if (cur < max) {
            generateAll(cur + 1, max, copy1, list);
            generateAll(cur + 1, max, copy2, list);
            generateAll(cur + 1, max, copy3, list);
        } else {
            list.add(copy1);
            list.add(copy2);
            list.add(copy3);
        }
    }
}
