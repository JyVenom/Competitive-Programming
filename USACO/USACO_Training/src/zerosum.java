/*
ID: jerryya2
LANG: JAVA
TASK: zerosum
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class zerosum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("zerosum.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));

        int n = Integer.parseInt(br.readLine());
        br.close();

        ArrayList<String> all = new ArrayList<>();
        generateAll(2, n, "1", all);
        for (int i = 0; i < all.size(); i++) {
            String seq = all.get(i);
            String copy = seq.replaceAll("\\s+", "");
            String[] values = copy.split("\\+");
            int val = 0;
            for (String num : values) {
                if (num.contains("-")) {
                    String[] temp = num.split("-");
                    val += Integer.parseInt(temp[0]);
                    for (int k = 1; k < temp.length; k++) {
                        val -= Integer.parseInt(temp[k]);
                    }
                } else {
                    val += Integer.parseInt(num);
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
        copy1 += cur;
        copy2 += cur;
        copy3 += cur;
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
