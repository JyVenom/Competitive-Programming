/*
ID: jerryya2
LANG: JAVA
TASK: stall4
*/

import java.io.*;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class stall45 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("stall4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<cow> cows = new ArrayList<>();
        int[] uses = new int[m];
//        ArrayList<ArrayList<Integer>> uses2 = new ArrayList<>();
//        for (int i = 0; i < m; i++) {
//            uses2.add(new ArrayList<>());
//        }
        for (int i = 0; i < n; i++) {
            cow cur = new cow();
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            for (int j = 0; j < s; j++) {
                int p = Integer.parseInt(st.nextToken()) - 1;
                cur.addStall(p);
                uses[p]++;
//                uses2.get(p).add(i);
            }
            cows.add(cur);
        }

        int used = 0;
        boolean[] taken = new boolean[m];
//        ArrayList<Integer> delete = new ArrayList<>();
//        for (int i = 0; i < m; i++) {
//            if (uses[i] == 1) {
//                taken[i] = true;
//                delete.add(uses2.get(i).get(0));
//            }
//        }
//        Collections.sort(delete);
//        for (int i = delete.size() - 1; i >= 0; i--) {
//            used++;
//            cows.remove((int)(delete.get(i)));
//        }
        cows.sort(Comparator.comparingInt(o -> o.numLike));
        for (cow cow : cows) {
            ArrayList<Integer> stalls = cow.stalls;
            stalls.sort(Comparator.comparingInt(o -> uses[o]));
        }
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


