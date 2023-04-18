/*
ID: jerryya2
LANG: JAVA
TASK: fence6
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class fence62 {
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
            int temp = search(fences, new boolean[n], true, start, start, Integer.MAX_VALUE / 2, 0);
            if (temp < min) {
                min = temp;
            }
        }

        pw.println(min);
        pw.close();
    }

    private static int search(ArrayList<fence> fences, boolean[] visited, boolean isStart, int start, int at, int min, int sum) {
        fence cur = fences.get(at);
        sum += cur.length;
        if (!isStart) {
            if (at == start) {
                if (sum < min) {
                    min = sum;
                }
            }
        }
        if (visited[at]) {
            return min;
        }

        visited[at] = true;
        ArrayList<Integer> lhs = cur.lhs;
        ArrayList<Integer> rhs = cur.rhs;
        for (int next : lhs) {
            min = search(fences, visited, false, start, next, min, sum);
        }
        for (int next : rhs) {
            min = search(fences, visited, false, start, next, min, sum);
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
