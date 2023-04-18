/*
ID: jerryya2
LANG: JAVA
PROG: milk3
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class milk32 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        br.close();
//        if (a > b) {
//            int temp = a;
//            a = b;
//            b = temp;
//        }

        int[] cur = new int[3];
        cur[2] = c;
        HashSet<Integer> pos = new HashSet<>();
        pos.add(c);
        while (b - cur[1] >= a && cur[2] >= a) {
            cur[1] += a;
            cur[2] -= a;
            pos.add(cur[2]);
        }
        int min = Math.min(b, c);
        cur[1] = min;
        cur[2] = c - min;
        pos.add(cur[2]);
        while (cur[1] - a >= 0) {
            cur[1] -= a;
            cur[2] += a;
            pos.add(cur[2]);
        }
        min = Math.min(b, c);
        cur[1] = min;
        cur[2] = c - min;
        min = Math.min(a, cur[2]);
        cur[0] = min;
        cur[2] -= min;
        cur[2] += cur[1];
        pos.add(cur[2]);
        min = Math.min(a, c);
        cur[0] = min;
        cur[2] = c - min;
        min = Math.min(b, cur[2]);
        cur[1] = min;
        cur[2] -= min;
        cur[2] += cur[0];
        pos.add(cur[2]);
        ArrayList<Integer> ans = new ArrayList<>(pos);
        Collections.sort(ans);

        for (int i = 0; i < ans.size() - 1; i++) {
            pw.print(ans.get(i) + " ");
        }
        pw.println(ans.get(ans.size() - 1));
        pw.close();
    }
}
