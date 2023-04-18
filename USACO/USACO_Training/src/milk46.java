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

public class milk46 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis(); //jerry
        BufferedReader br = new BufferedReader(new FileReader("milk4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));

        int q = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        int[] pails = new int[p];
        for (int i = 0; i < p; i++) {
            pails[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(System.currentTimeMillis() - start); //jerry
        start = System.currentTimeMillis(); //jerry
//        clear

        Arrays.sort(pails);
        ArrayList<ArrayList<HashSet<Integer>>> data = new ArrayList<>();
        for (int i = 0; i <= pails.length; i++) {
            ArrayList<HashSet<Integer>> temp = new ArrayList<>();
            for (int j = 0; j <= q; j++) {
                temp.add(new HashSet<>());
            }
            data.add(temp);
        }
        System.out.println(System.currentTimeMillis() - start); //jerry
        start = System.currentTimeMillis(); //jerry
        int[][] data2 = new int[pails.length + 1][q + 1];
        Arrays.fill(data2[0], -1);
        data2[0][0] = 0;
        for (int i = 0; i < pails.length / 2; i++) {
            int temp = pails[i];
            pails[i] = pails[pails.length - i - 1];
            pails[pails.length - i - 1] = temp;
        }
        System.out.println(System.currentTimeMillis() - start); //jerry
        start = System.currentTimeMillis(); //jerry
//        clear
        for (int i = 1; i <= pails.length; i++) {
            int I = i - 1;
            data2[i] = data2[I].clone();
            ArrayList<HashSet<Integer>> copy = new ArrayList<>(data.get(I));
            data.set(i, copy);
            for (int j = pails[I]; j <= q; j++) {
                if (data2[i][j - pails[I]] != -1) {
                    int temp = data2[i][j - pails[I]] + 1;
                    if (temp > data2[i][j]) {
                        data2[i][j] = temp;
                        HashSet<Integer> copy2 = new HashSet<>(data.get(i).get(j - pails[I]));
                        data.get(i).set(j, copy2);
                        data.get(i).get(j).add(pails[I]);
                    } else if (temp == data2[i][j]) {
                        HashSet<Integer> copy2 = new HashSet<>(data.get(i).get(j - pails[I]));
                        copy2.add(pails[I]);
                        if (better(copy2, data.get(i).get(j))) {
                            data2[i][j] = temp;
                            data.get(i).set(j, copy2);
                        }
                    }
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start); //jerry
        start = System.currentTimeMillis(); //jerry
        ArrayList<Integer> ans = new ArrayList<>(data.get(pails.length).get(q));
        Collections.sort(ans);
        for (int i = ans.size() - 1; i >= 0; i--) {
            boolean[] pos = new boolean[q + 1];
//            for (int j = 0; j < ans.size(); j++) {
//                if (j == i) {
//                    continue;
//                }
//
//                pos[ans.get(j)] = true;
//            }
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
//            for (int j = ans.get(i); j <= q; j += ans.get(i)) {
//                if (pos[j]) {
//                    ans.remove(i);
//                    break;
//                }
//            }
            if (pos[q]) {
                ans.remove(i);
            }
        }
        System.out.println(System.currentTimeMillis() - start); //jerry
        start = System.currentTimeMillis(); //jerry
//        clear

        pw.print(ans.size());
        for (int pail : ans) {
            pw.print(" " + pail);
        }
        pw.println();
        pw.close();
        System.out.println(System.currentTimeMillis() - start); //jerry
//        clear pos
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
