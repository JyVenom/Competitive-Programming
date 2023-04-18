/*
ID: jerryya2
LANG: JAVA
TASK: range
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class range {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("range.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));

        int n = Integer.parseInt(br.readLine());
        ArrayList<int[]> zeros = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == '0') {
                    int[] zero = new int[2];
                    zero[0] = i;
                    zero[1] = j;
                    zeros.add(zero);
                }
            }
        }
        br.close();
        zeros.sort(Comparator.comparingInt(a -> a[1]));
        zeros.sort(Comparator.comparingInt(a -> a[0]));

        HashMap<Integer, Integer> sizes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) { //define upper left corner location
                for (int k = 1; k + i < n && k + j < n; k++) { //define size/side length and thus lower right
                    if (notContains(zeros, i, j, i + k, j + k, zeros.size() - 1)) {
                        if (sizes.containsKey(k)) {
                            sizes.replace(k, sizes.get(k) + 1);
                        } else {
                            sizes.put(k, 1);
                        }
                    }
                    else {
                        break;
                    }
                }
            }
        }
        ArrayList<int[]> ans = new ArrayList<>();
        for (int i : sizes.keySet()) {
            int[] temp = new int[2];
            temp[0] = i;
            temp[1] = sizes.get(i);
            ans.add(temp);
        }
        zeros.sort(Comparator.comparingInt(a -> a[0]));

        for (int[] temp : ans) {
            pw.println((temp[0] + 1) + " " + temp[1]);
        }
        pw.close();
    }

    private static boolean notContains(ArrayList<int[]> arr, int x1, int y1, int x2, int y2, int high) {
        boolean contains = true;

        int low = 0;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr.get(mid)[0] < x1) {
                low = mid + 1;
            } else if (arr.get(mid)[0] > x2) {
                high = mid - 1;
            } else if (x1 <= arr.get(mid)[0] && arr.get(mid)[0] <= x2) {
                boolean good = false;
                for (int i = mid; i <= high; i++) {
                    if (arr.get(i)[0] > x2) {
                        break;
                    }
                    if (y1 <= arr.get(i)[1]  && arr.get(i)[1] <= y2) {
                        contains = false;
                        good = true;
                        break;
                    }
                }
                if (good) {
                    break;
                }
                for (int i = mid - 1; i >= low; i--) {
                    if (arr.get(i)[0] < x1) {
                        break;
                    }
                    if (y1 <= arr.get(i)[1]  && arr.get(i)[1] <= y2) {
                        contains = false;
                        break;
                    }
                }
                break;
            }
        }
        return contains;
    }
}
