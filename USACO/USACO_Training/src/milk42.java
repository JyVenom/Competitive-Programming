/*
ID: jerryya2
LANG: JAVA
TASK: milk4
*/

import java.io.*;
import java.util.*;

public class milk42 {
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
            LinkedList<Integer> queue = new LinkedList<>();
            ArrayList<HashSet<Integer>> used = new ArrayList<>();
            for (int i = 0; i <= q; i++) {
                used.add(new HashSet<>());
            }
            for (int i = 0; i < p; i++) {
                queue.add(pails[i]);
                used.get(pails[i]).add(pails[i]);
            }
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                HashSet<Integer> curUsed = used.get(cur);
                for (int i = 0; i < p; i++) {
                    int next = cur + pails[i];
                    if (next <= q) {
                        if (Collections.binarySearch(queue, next) < 0) {
                            queue.offer(next);
                        }
                        HashSet<Integer> curUsedCopy = new HashSet<>(curUsed);
                        curUsedCopy.add(pails[i]);
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
                                boolean better = false;
                                for (int j = 0; j < curUsedCopy2.size(); j++) {
                                    if (curUsedCopy2.get(j) < nextUsed2.get(j)) {
                                        better = true;
                                        break;
                                    } else if (curUsedCopy2.get(j) > nextUsed2.get(j)) {
                                        break;
                                    }
                                }
                                if (better) {
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
