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

public class milk45 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("milk4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));

        int q = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        int[] pails = new int[p];
        for (int i = 0; i < p; i++) {
            pails[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(pails);
        ArrayList<ArrayList<HashSet<Integer>>> data = new ArrayList<>();
        for (int i = 0; i <= pails.length; i++) {
            ArrayList<HashSet<Integer>> temp = new ArrayList<>();
            for (int j = 0; j <= q; j++) {
                temp.add(new HashSet<>());
            }
            data.add(temp);
        }
        int[][] data2 = new int[pails.length + 1][q + 1];
        Arrays.fill(data2[0], -1);
        data2[0][0] = 0;
        for (int i = 0; i < pails.length / 2; i++) {
            int temp = pails[i];
            pails[i] = pails[pails.length - i - 1];
            pails[pails.length - i - 1] = temp;
        }
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
                    }
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>(data.get(pails.length).get(q));
        Collections.sort(ans);

        pw.print(ans.size());
        for (int pail : ans) {
            pw.print(" " + pail);
        }
        pw.println();
        pw.close();
    }
}
