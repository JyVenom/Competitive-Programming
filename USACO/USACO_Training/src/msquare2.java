/*
ID: jerryya2
LANG: JAVA
TASK: msquare
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class msquare2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("msquare.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));

        int[] target = new int[8];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 8; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        int[] cur = new int[8];
        for (int i = 0; i < 8; i++) {
            cur[i] = i + 1;
        }
        String seq = "";
        ArrayList<int[]> all = new ArrayList<>();
        all.add(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        int ans = findAns(cur, target, seq, 0, all);

        pw.println(ans);
        pw.println(seq);
        pw.close();
    }

    private static int findAns (int[] cur, int[] target, String seq, int count, ArrayList<int[]> all) {
        if (Arrays.equals(cur, target)) {
            return count;
        }

        int min = Integer.MAX_VALUE;
        int[] copy = cur.clone();
        A(copy);
        boolean good = true;
        for (int[] ints : all) {
            if (Arrays.equals(copy, ints)) {
                good = false;
                break;
            }
        }
        if (good) {
            all.add(copy);
            int a = findAns(copy, target, seq + "A", count + 1, all);
            if (a < min) {
                min = a;
            }
        }

        copy = cur.clone();
        B(copy);
        good = true;
        for (int i = 0; i < all.size() - 1; i++) {
            if (Arrays.equals(cur, all.get(i))) {
                good = false;
                break;
            }
        }
        if (good) {
            all.add(copy);
            int b = findAns(copy, target, seq + "B", count + 1, all);
            if (b < min) {
                min = b;
            }
        }

        good = true;
        for (int i = 0; i < all.size() - 1; i++) {
            if (Arrays.equals(cur, all.get(i))) {
                good = false;
                break;
            }
        }
        if (good) {
            copy = cur.clone();
            C(copy);
            all.add(copy);
            int c = findAns(copy, target, seq + "C", count + 1, all);
            if (c < min) {
                min = c;
            }
        }

        return min;
    }

    private static void A (int[] cur) {
        for (int i = 0; i < 4; i++) {
            int temp = cur[i];
            cur[i] = cur[7 - i];
            cur[7 - i] = temp;
        }
    }

    @SuppressWarnings("SuspiciousSystemArraycopy")
    private static void B (int[] cur) {
        int temp = cur[3];
        System.arraycopy(cur, 0, cur, 1, 3);
        cur[0] = temp;
        temp = cur[4];
        System.arraycopy(cur, 5, cur, 4, 3);
        cur[7] = temp;
    }

    private static void C (int[] cur) {
        int temp = cur[1];
        cur[1] = cur[6];
        cur[6] = cur[5];
        cur[5] = cur[2];
        cur[2] = temp;
    }
}
