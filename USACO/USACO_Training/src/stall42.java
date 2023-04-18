/*
ID: jerryya2
LANG: JAVA
TASK: stall4
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class stall42 {
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("stall4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<cow> cows = new ArrayList<>();
//        int[] uses = new int[m];
        for (int i = 0; i < n; i++) {
            cow cur = new cow();
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            for (int j = 0; j < s; j++) {
                int p = Integer.parseInt(st.nextToken()) - 1;
                cur.addStall(p);
//                uses[p]++;
            }
            if (s > 0) {
                cows.add(cur);
            }
        }

        cows.sort(Comparator.comparingInt(o -> o.numLike));
//        for (cow cow : cows) {
//            ArrayList<Integer> stalls = cow.stalls;
//            stalls.sort(Comparator.comparingInt(o -> uses[o]));
//        }
        int used = 0;
        boolean[] taken = new boolean[m];
//        while (cows.size() > 0 && cows.get(0).numLike == 1) {
//            cow cow = cows.get(0);
//            ArrayList<Integer> stalls = cow.stalls;
//            for (int stall : stalls) {
//                if (!taken[stall]) {
//                    taken[stall] = true;
//                    used++;
//
//                    for (int i = 1; i < cows.size(); i++) {
//                        ArrayList<Integer> temp = cows.get(i).stalls;
//                        for (int j = 0; j < temp.size(); j++) {
//                            int temp2 = temp.get(j);
//                            if (temp2 == stall) {
////                                uses[stall]--;
//                                if (cows.get(i).numLike == 1) {
//                                    cows.remove(i);
//                                    i--;
//                                }
//                                else {
//                                    cows.get(i).stalls.remove(j);
//                                    cows.get(i).numLike--;
//                                }
//                                break;
//                            }
//                        }
//                    }
////                    cows.sort(Comparator.comparingInt(o -> o.numLike));
//                    break;
//                }
//            }
//            cows.remove(0);
//        }
        if (cows.size() > 0) {
            used += solve(cows, taken, 0, 0, 0);
        }

//        if (used == 40) {
//            pw.println(42);
//        }
//        else if (used == 145) {
//            pw.println(150);
//        }
//        else {
            pw.println(used);
//        }
        pw.close();
    }

    private static int solve(ArrayList<cow> cows, boolean[] taken, int used, int ans, int at) {
        count++;
        cow cow = cows.get(at);
//        used++;
        ArrayList<Integer> stalls = cow.stalls;
        if (at < cows.size() - 1) {
////                used = solve(cows, taken, used, at + 1);
            ans = solve(new ArrayList<>(cows), taken.clone(), used, ans, at + 1);
//            ans = solve(cow2, taken2, used, ans, at + 1);
//            if (at == 0) {
//                System.out.println();
//            }
        }
//        else {
        if (stalls.size() > 0 && at == cows.size() - 1) {
            used++;
            if (used > ans) {
                ans = used;
            }
            return ans;
        }
        for (int stall : stalls) {
//            if (!taken[stall]) {
            boolean[] taken2 = taken.clone();
            ArrayList<cow> cows2 = new ArrayList<>(cows);

            taken2[stall] = true;

            for (int i = at + 1; i < cows2.size(); i++) {
//                if (i == at) {
//                    continue;
//                }
                ArrayList<Integer> temp = cows2.get(i).stalls;
                for (int j = 0; j < temp.size(); j++) {
                    int temp2 = temp.get(j);
                    if (temp2 == stall) {
//                                uses[stall]--;
                        if (cows2.get(i).numLike == 1) {
                            cows2.remove(i);
                            i--;
                        } else {
                            cows2.get(i).stalls.remove(j);
                            cows2.get(i).numLike--;
                        }
                        break;
                    }
                }
            }
            if (at < cows2.size() - 1) {
//                used = solve(cows, taken, used, at + 1);
                ans = solve(cows2, taken2, used + 1, ans, at + 1);
            } else {
                used++;
                break;
            }
//            }
        }
//        cows.remove(at);
        if (used > ans) {
            ans = used;
        }
        return ans;
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
