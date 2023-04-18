/*
ID: jerryya2
LANG: JAVA
TASK: contact
*/

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class contact2 {
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

        int[][] all2 = new int[all.size()][2];
        String[] keys = new String[all.size()];
        int tmp = 0;
        for (String i : all.keySet()) {
            keys[tmp] = i;
            all2[tmp][0] = all.get(i);
            all2[tmp][1] = tmp;
            tmp++;
        }
        Arrays.sort(all2, (o1, o2) -> Integer.compare(o2[0], o1[0]));
        tmp = 1;
        System.out.println(all2[0][0]);
        System.out.print(keys[all2[0][1]]);
        int count = 1;
        for (int i = 1; i < all2.length && i <= n; i++) {
            if (all2[tmp][0] == all2[tmp - 1][0]) {
                i--;
                if (count < 6) {
                    System.out.print(" " + keys[all2[tmp][1]]);
                    count++;
                }
                else {
                    count = 1;
                    System.out.println();
                    System.out.print(keys[all2[tmp][1]]);
                }
            }
            else {
                if (i == n) {
                    break;
                }
                count = 1;
                System.out.println();
                System.out.println(all2[tmp][0]);
                System.out.print(keys[all2[tmp][1]]);
            }
            tmp++;
        }

        pw.close();
    }
}
