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

public class milk44 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        int r = 1;
        for (int k = 0; k < r; k++) {
            BufferedReader br = new BufferedReader(new FileReader("milk4.in"));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));

            int q = Integer.parseInt(br.readLine());
            int p = Integer.parseInt(br.readLine());
            int[] pails = new int[p];
            for (int i = 0; i < p; i++) {
                pails[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(pails);
            ArrayList<Integer> processed = new ArrayList<>();
            ArrayList<Integer> queue = new ArrayList<>();
            ArrayList<HashSet<Integer>> used = new ArrayList<>();
            for (int i = 0; i <= q; i++) {
                used.add(new HashSet<>());
            }
            for (int i = 0; i < p; i++) {
                queue.add(pails[i]);
                used.get(pails[i]).add(pails[i]);
            }
            while (!queue.isEmpty()) {
                int cur = queue.get(0);
                queue.remove(0);
                processed.add(cur);
                Collections.sort(processed);
                HashSet<Integer> curUsed = used.get(cur);
                for (int add : processed) {
                    int next = cur + add;
                    if (next <= q) {
//                        if (!queue.contains(next)) {
                        queue.add(next);
                        queue.sort((o1, o2) -> {
                            HashSet<Integer> a = new HashSet<>(used.get(o1));
                            HashSet<Integer> b = new HashSet<>(used.get(o2));
                            if (a.size() < b.size()) {
                                return 1;
                            } else if (a.size() == b.size()) {
                                ArrayList<Integer> A = new ArrayList<>(a);
                                ArrayList<Integer> B = new ArrayList<>(b);

                                Collections.sort(A);
                                Collections.sort(B);
                                boolean better = false;
                                boolean same = true;
                                for (int j = 0; j < A.size(); j++) {
                                    if (A.get(j) < B.get(j)) {
                                        better = true;
                                        same = false;
                                        break;
                                    } else if (A.get(j) > B.get(j)) {
                                        same = false;
                                        break;
                                    }
                                }
                                if (same) {
                                    return 0;
                                } else {
                                    return better ? 1 : -1;
                                }
                            } else {
                                return -1;
                            }
                        });
//                        }
                        HashSet<Integer> curUsedCopy = new HashSet<>(curUsed);
                        ArrayList<Integer> temp = new ArrayList<>(used.get(add));
                        curUsedCopy.addAll(temp);
                        HashSet<Integer> nextUsed = used.get(next);
                        if (nextUsed.size() == 0) {
                            used.set(next, curUsedCopy);
                        } else {
                            if (curUsedCopy.size() < nextUsed.size()) {
                                used.set(next, curUsedCopy);
                            } else if (curUsedCopy.size() == nextUsed.size()) {
                                ArrayList<Integer> curUsedCopy2 = new ArrayList<>(curUsedCopy);
                                ArrayList<Integer> nextUsed2 = new ArrayList<>(nextUsed);

                                Collections.sort(curUsedCopy2);
                                Collections.sort(nextUsed2);
                                StringBuilder a = new StringBuilder();
                                StringBuilder b = new StringBuilder();
                                for (int j = 0; j < curUsedCopy2.size(); j++) {
                                    a.append(curUsedCopy2.get(j));
                                    b.append(nextUsed2.get(j));
                                }
                                int A = Integer.parseInt(a.toString());
                                int B = Integer.parseInt(b.toString());
                                if (A < B) {
                                    used.set(next, curUsedCopy);
                                }
                            }
                        }
                    }
                }
            }

            ArrayList<Integer> list = new ArrayList<>(used.get(q));
            Collections.sort(list);
            pw.print(list.size());
            for (int bucket : list) {
                pw.print(" " + bucket);
            }
            pw.println();
            pw.close();
        }
        System.out.println((System.currentTimeMillis() - start) / r);
    }
}
