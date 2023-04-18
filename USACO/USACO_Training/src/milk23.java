/*
ID: jerryya2
LANG: JAVA
PROG: milk2
*/

import java.io.*;
import java.util.*;

public class milk23 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> times = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ArrayList<Integer> time = new ArrayList<>();
            time.add(start);
            time.add(end);
            times.add(time);
        }
        times.sort(Comparator.comparing(a -> a.get(0)));
        for (int i = 0; i <= n + 1; i++) {
            loop : for (int j = 0; j < times.size(); j++) {
                for (int k = 0; k < times.size(); k++) {
                    if (j != k) {
                        if (times.get(j).get(0) > times.get(k).get(0)) {
                            if (times.get(j).get(0) <= times.get(k).get(1)) {
                                if (times.get(j).get(1) >= times.get(k).get(1)) {
                                    times.get(k).set(1, times.get(j).get(1));
                                }
                                times.remove(j);
                                break loop;
                            }
                        } else {
                            if (times.get(j).get(1) >= times.get(k).get(0)) {
                                if (times.get(j).get(1) <= times.get(k).get(1)) {
                                    times.get(j).set(1, times.get(k).get(1));
                                }
                                times.remove(k);
                                break loop;
                            }
                        }
                    }
                }
            }
        }
        int maxLength = 0;
        int maxNone = 0;
        for (int i = 0; i < times.size(); i++) {
            int length = times.get(i).get(1) - times.get(i).get(0);
            int none = 0;
            if (i + 1 < times.size()) {
                none = times.get(i + 1).get(0) - times.get(i).get(1);
            }
            maxLength = Math.max(maxLength, length);
            maxNone = Math.max(maxNone, none);
        }
        pw.println(maxLength + " " + maxNone);
        pw.close();
        br.close();
    }
}