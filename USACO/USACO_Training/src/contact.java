/*
ID: jerryya2
LANG: JAVA
TASK: contact
*/

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class contact {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("contact.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        StringBuilder dataTmp = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            dataTmp.append(line);
            line = br.readLine();
        }
        String data = dataTmp.toString();
        br.close();

        HashMap<String, Integer> all = new HashMap<>();
        int len = data.length();
        int len2 = len - a + 1;
        for (int i = 0; i < len2; i++) {
            for (int j = a; j <= b; j++) {
                if (i + j <= len) {
                    String key = data.substring(i, i + j);
                    if (all.containsKey(key)) {
                        all.replace(key, all.get(key) + 1);
                    }
                    else {
                        all.put(key, 1);
                    }
                }
                else {
                    break;
                }
            }
        }

        String[][] all2 = new String[all.size()][2];
        int tmp = 0;
        for (String i : all.keySet()) {
            all2[tmp][0] = i;
            all2[tmp][1] = "" + all.get(i);
            tmp++;
        }
        Arrays.sort(all2, Comparator.comparing(strings -> strings[0]));
        Arrays.sort(all2, Comparator.comparing(strings -> strings[0].length()));
        Arrays.sort(all2, (first, second) -> Double.valueOf(second[1]).compareTo(
                Double.valueOf(first[1])
        ));

        tmp = 1;
        pw.println(Integer.parseInt(all2[0][1]));
        pw.print(all2[0][0]);
        int count = 1;
        for (int i = 1; i < all2.length && i <= n; i++) {
            if (Integer.parseInt(all2[tmp][1]) == Integer.parseInt(all2[tmp - 1][1])) {
                i--;
                if (count < 6) {
                    pw.print(" " + all2[tmp][0]);
                    count++;
                }
                else {
                    count = 1;
                    pw.println();
                    pw.print(all2[tmp][0]);
                }
            }
            else {
                if (i == n) {
                    break;
                }
                count = 1;
                pw.println();
                pw.println(Integer.parseInt(all2[tmp][1]));
                pw.print(all2[tmp][0]);
            }
            tmp++;
            if (tmp == all2.length) {
                break;
            }
        }
        pw.println();

        pw.close();
    }
}
