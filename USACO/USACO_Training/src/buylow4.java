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
import java.util.StringTokenizer;

public class buylow4 {
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
        int[] prev = new int[n];
        ArrayList<ArrayList<Integer>> next = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            next.add(new ArrayList<>());
        }
        best[0] = 1;
        prev[0] = -1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (prices[j] < prices[i]) {
                    max = Math.max(max, best[j]);
                    prev[i] = j;
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
        for (int i = 0; i <= n - max; i++) {
            num += findNum(next, max, 1, 0, i);
        }

        pw.println(max + " " + num);
        pw.close();
    }

    private static int findNum(ArrayList<ArrayList<Integer>> next, int max, int sum, int count, int at) {
        if (sum == max) {
            count++;
        } else {
            for (int temp : next.get(at)) {
                count = findNum(next, max, sum + 1, count, temp);
            }
        }
        return count;
    }
}
