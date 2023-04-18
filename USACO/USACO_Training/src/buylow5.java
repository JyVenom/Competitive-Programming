/*
ID: jerryya2
LANG: JAVA
TASK: buylow
*/

//Big-O: n^2 / 2
//1 <= n <= 5000
//Worse case: 12,500,000

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class buylow5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("buylow.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));

        int n = Integer.parseInt(br.readLine());
        int[] prices = new int[n];
        int at = n - 1;
        while (at >= 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                prices[at] = Integer.parseInt(st.nextToken());
                at--;
            }
        }

        int[] best = new int[n];
        ArrayList<ArrayList<Integer>> next = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            next.add(new ArrayList<>());
        }
        best[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (prices[j] < prices[i]) {
                    max = Math.max(max, best[j]);
                    next.get(j).add(i);
                }
            }
            best[i] = max + 1;
        }
        int max = 0;
        for (int val : best) {
            if (val > max) {
                max = val;
            }
        }
        int num = 0;
        ArrayList<int[]> sequences = new ArrayList<>();
        for (int i = 0; i <= n - max; i++) {
            int[] cur = new int[max];
            cur[0] = prices[i];
            num += findNum(next, sequences, cur, prices, max, 1, 0, i, 1);
        }
        for (int i = 0; i < sequences.size(); i++) {
            for (int j = max - 1; j >= 0; j--) {
                int finalJ = j;
                sequences.sort(Comparator.comparingInt(a -> a[finalJ]));
            }
        }
        for (int i = 1; i < sequences.size(); i++) {
            if (isSame(sequences.get(i), sequences.get(i - 1))) {
                sequences.remove(i);
                i--;
            }
        }

        pw.println(max + " " + sequences.size());
        pw.close();
    }

    private static int findNum(ArrayList<ArrayList<Integer>> next, ArrayList<int[]> sequences, int[] cur, int[] prices, int max, int sum, int count, int at, int at2) {
        if (sum == max) {
            count++;
            sequences.add(cur);
        } else {
            for (int temp : next.get(at)) {
                cur[at2] = prices[temp];
                count = findNum(next, sequences, cur, prices, max, sum + 1, count, temp, at2 + 1);
            }
        }
        return count;
    }

    private static boolean isSame(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
}
