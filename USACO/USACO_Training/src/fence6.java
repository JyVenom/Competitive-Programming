/*
ID: jerryya2
LANG: JAVA
TASK: fence6
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class fence6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fence6.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));

        int n = Integer.parseInt(br.readLine());
        ArrayList<fence> fences = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            fences.add(new fence());
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            fence cur = new fence();
            int s = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            cur.setLength(l);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n1; j++) {
                cur.addLeftSide(Integer.parseInt(st.nextToken()) - 1);
            }
            Collections.sort(cur.lhs);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n2; j++) {
                cur.addRightSide(Integer.parseInt(st.nextToken()) - 1);
            }
            Collections.sort(cur.rhs);
            fences.set(s, cur);
        }

        int min = Integer.MAX_VALUE / 2;
        for (int start = 0; start < n; start++) {
            int temp = min;
            int cur = search(fences, new boolean[n], true, start, start, -1, temp, 0, 0);
            if (cur < min) {
                min = cur;
            }
        }

        pw.println(min);
        pw.close();
    }

    private static int search(ArrayList<fence> fences, boolean[] visited, boolean isStart, int start, int at, int prev, int min, int sum, int count) {
        if (!isStart) {
            if (count >= 3) {
                if (at == start) {
                    if (sum < min) {
                        min = sum;
                    }
                }
            }
        }
        if (visited[at]) {
            return min;
        }

        fence cur = fences.get(at);
        sum += cur.length;
        visited[at] = true;
        ArrayList<Integer> lhs = cur.lhs;
        ArrayList<Integer> rhs = cur.rhs;
        if (!isStart) {
            if (Collections.binarySearch(lhs, prev) >= 0) {
                for (int next : rhs) {
                    min = search(fences, visited, false, start, next, at, min, sum, count + 1);
                }
            } else {
                for (int next : lhs) {
                    min = search(fences, visited, false, start, next, at, min, sum, count + 1);
                }
            }
        } else {
            for (int next : lhs) {
                min = search(fences, visited, false, start, next, at, min, sum, count + 1);
            }
            for (int next : rhs) {
                min = search(fences, visited, false, start, next, at, min, sum, count + 1);
            }
        }
        visited[at] = false;

        return min;
    }

    private static class fence {
        int length;
        ArrayList<Integer> lhs = new ArrayList<>();
        ArrayList<Integer> rhs = new ArrayList<>();

        private void setLength(int length) {
            this.length = length;
        }

        private void addLeftSide(int other) {
            lhs.add(other);
        }

        private void addRightSide(int other) {
            rhs.add(other);
        }
    }
}
