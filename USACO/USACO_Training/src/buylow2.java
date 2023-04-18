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

public class buylow2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("buylow.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));

        int n = Integer.parseInt(br.readLine());
        int[] prices = new int[n];
        int at = 0;
        while (at < n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                prices[at] = Integer.parseInt(st.nextToken());
                at++;
            }
        }

        ArrayList<int[]> sequences = new ArrayList<>();
        int[] init = new int[2]; //each array has 2 values, lowest so far/last in sequence so far and sequence length so far
        init[0] = prices[0];
        init[1] = 1;
        sequences.add(init);
        for (int i = 1; i < n; i++) {
            int cur = prices[i];
            for (int j = 0; j < sequences.size(); j++) {
                int[] seq = sequences.get(j);
                if (cur < seq[0]) {
                    seq[0] = cur;
                    seq[1]++;
                } else if (cur > seq[0]) {
//                    if (binarySearch(sequences, cur, i, sequences.size() - 1) == -1) {
                    if (sequences.get(sequences.size() - 1)[0] < cur) {
                        int[] temp = new int[2];
                        temp[0] = cur;
                        temp[1] = 1;
                        sequences.add(temp);

                    }
                }
            }
//            sequences.sort(Comparator.comparingInt(a -> a[0]));
        }
        sequences.sort((o1, o2) -> Integer.compare(o2[1], o1[1]));
        int[] ans = new int[2];
        ans[0] = sequences.get(0)[1];
        ans[1] = 1;
        for (int i = 1; i < sequences.size(); i++) {
            if (sequences.get(i)[1] == sequences.get(i - 1)[1]) {
                ans[1]++;
            }
            else {
                break;
            }
        }

        pw.println(ans[0] + " " + ans[1]);
    }
//
//    private static int binarySearch(ArrayList<int[]> arr, int key, int low, int high) {
//        int index = -1;
//        while (low <= high) {
//            int mid = (low + high) / 2;
//            if (arr.get(mid)[0] < key) {
//                low = mid + 1;
////            } else if (arr.get(mid)[0] >= key) {
//            } else {

//                index = mid;
//                break;
//            }
//        }
//        return index;
//    }
}
