/*
ID: jerryya2
LANG: JAVA
TASK: milk4
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class milk47 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));

        int q = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        int[] pails = new int[p];
        for (int i = 0; i < p; i++) {
            pails[i] = Integer.parseInt(br.readLine());
        }

        int[] num = new int[q + 1];
        Arrays.fill(num, -1);
        num[0] = 0;
        ArrayList<HashSet<Integer>> used = new ArrayList<>();
        for (int i = 0; i <= q; i++) {
            used.add(new HashSet<>());
        }

        long start = System.currentTimeMillis(); //jerry
        for (int i = p - 1; i >= 0; i--) {
            System.out.println(System.currentTimeMillis() - start); //jerry
            start = System.currentTimeMillis(); //jerry
            for (int j = pails[i]; j <= q; j++) {
                if (num[j - pails[i]] != -1) {
                    int temp = num[j - pails[i]] + 1;
                    if (temp > num[j]) {
                        num[j] = temp;
                        HashSet<Integer> copy2 = new HashSet<>(used.get(j - pails[i]));
                        copy2.add(pails[i]);
                        used.set(j, copy2);
                    } else if (temp == num[j]) {
                        HashSet<Integer> copy2 = new HashSet<>(used.get(j - pails[i]));
                        copy2.add(pails[i]);
                        if (better(copy2, used.get(j))) {
                            num[j] = temp;
                            used.set(j, copy2);
                        }
                    }
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>(used.get(q));
        Collections.sort(ans);
        for (int i = ans.size() - 1; i >= 0; i--) {
            boolean[] pos = new boolean[q + 1];
            pos[0] = true;
            for (int j = 0; j <= q; j++) {
                if (pos[j]) {
                    for (int k = 0; k < ans.size(); k++) {
                        if (k == i) {
                            continue;
                        }

                        int next = j + ans.get(k);
                        if (next <= q) {
                            pos[next] = true;
                        }
                    }
                }
            }
            if (pos[q]) {
                ans.remove(i);
            }
        }

        pw.print(ans.size());
        for (int pail : ans) {
            pw.print(" " + pail);
        }
        pw.println();
        pw.close();
    }

    private static boolean better(HashSet<Integer> New, HashSet<Integer> Old) {
        ArrayList<Integer> a = new ArrayList<>(New);
        ArrayList<Integer> b = new ArrayList<>(Old);
        Collections.sort(a);
        Collections.sort(b);

        if (a.size() < b.size()) {
            return true;
        } else if (a.size() > b.size()) {
            return false;
        } else {
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i) < b.get(i)) {
                    return true;
                }
            }
            return false;
        }
    }
}
