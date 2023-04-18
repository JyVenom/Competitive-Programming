/*
ID: jerryya2
LANG: JAVA
TASK: stall4
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class stall44 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("stall4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<cow> cows = new ArrayList<>();
        int[] uses = new int[m];
        for (int i = 0; i < n; i++) {
            cow cur = new cow();
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            for (int j = 0; j < s; j++) {
                int p = Integer.parseInt(st.nextToken()) - 1;
                cur.addStall(p);
                uses[p]++;
            }
            cows.add(cur);
        }

        cows.sort(Comparator.comparingInt(o -> o.numLike));
        for (cow cow : cows) {
            ArrayList<Integer> stalls = cow.stalls;
            stalls.sort(Comparator.comparingInt(o -> uses[o]));
        }
        int used = 0;
        boolean[] taken = new boolean[m];
        for (cow cow : cows) {
            ArrayList<Integer> stalls = cow.stalls;
            for (int stall : stalls) {
                if (!taken[stall]) {
                    taken[stall] = true;
                    used++;
                    break;
                }
            }
        }

        pw.println(used);
        pw.close();
    }

    private static class cow {
        int numLike;
        ArrayList<Integer> stalls = new ArrayList<>();

        private void addStall(int stall) {
            stalls.add(stall);
            numLike++;
        }
    }
}
