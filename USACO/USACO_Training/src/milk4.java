/*
ID: jerryya2
LANG: JAVA
TASK: milk4
*/

import java.io.*;
import java.util.Arrays;

public class milk4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));

        int q = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        int[] pails = new int[p];
        for (int i = 0; i < p; i++) {
            pails[i] = Integer.parseInt(br.readLine());
        }

        int[] arr = new int[0];
        for (int i = 1; i <= p; i++) {
            arr = new int[i];
            boolean res = helper(pails, arr, i, q, 0);
            if (res) {
                break;
            }
        }

        Arrays.sort(arr);
        pw.print(arr.length);
        for (int pail : arr) {
            pw.print(" " + pail);
        }
//        pw.print(arr.length);
//        for (int i = arr.length - 1; i >= 0; i--) {
//            pw.print(" " + arr[i]);
//        }
        pw.println();
        pw.close();
    }

    private static boolean helper(int[] pails, int[] arr, int rem, int q, int start) {
        if (rem == 0) {
            return isPossible(q, arr);
        } else {
            int rem2 = rem - 1;
            int end = pails.length - rem;
            for (int i = start; i <= end; i++) {
                arr[rem2] = pails[i];
                boolean res = helper(pails, arr, rem2, q, i + 1);
                if (res) {
                    return true;
                }
            }
            return false;
        }
    }

    private static boolean isPossible(int q, int[] arr) {
//        int Q = q + 1;
//        boolean[] pos = new boolean[Q];
//        boolean[] visited = new boolean[Q];
//        ArrayList<Integer> toVisit = new ArrayList<>();

        boolean[] pos = new boolean[q + 1];
        pos[0] = true;
        for (int i = 0; i < q; i++) {
            if (pos[i]) {
                for (int j = arr.length - 1; j >= 0; j--) {
                    int next = i + arr[j];
                    if (next <= q) {
                        pos[next] = true;
                    } else {
                        break;
                    }
                }
            }
        }
        return pos[q];
    }
}
