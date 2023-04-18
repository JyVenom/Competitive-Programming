/*
ID: jerryya2
LANG: JAVA
TASK: stall4
*/

import java.io.*;
import java.util.StringTokenizer;

public class stall46 {
    static int time = 0;
    static int[][] map;
    static Point[] pts;
    static Point start;
    static Point end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("stall4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int size = n + m + 2;
        pts = new Point[size];
        map = new int[size][size];
        pts[size - 2] = new Point(size - 2, new int[n]);
        start = pts[size - 2];
        for (int i = 0; i < n; i++) {
            start.next[i] = i;
            map[start.index][i] = 1;
        }
        pts[size - 1] = new Point(size - 1);
        end = pts[size - 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int[] arr = new int[s];
            for (int j = 0; j < s; j++) {
                int stall = Integer.parseInt(st.nextToken()) - 1 + m;
                arr[j] = stall;
                map[i][stall] = 1;
            }
            pts[i] = new Point(i, arr);
        }
        for (int i = 0; i < m; i++) {
            int stall = i + m;
            map[stall][end.index] = 1;
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (map[j][stall] != 0) count++;
            }
            int[] arr = new int[count + 1];
            arr[0] = end.index;
            count = 1;
            for (int j = 0; j < n; j++) {
                if (map[j][stall] != 0) arr[count++] = j;
            }
            pts[stall] = new Point(stall, arr);
        }

        int ans = 0;
        while (true) {
            int cap = search_next();
            if (cap < 0)
                break;
            ans += cap;
        }
        pw.println(ans);
        pw.close();
    }

    private static int search(int time, Point p) {
        for (int i = 0; i < p.next.length; i++) {
            int toIndex = p.next[i];
            Point to = pts[toIndex];
            if (to.time != time) {
                int cap = Math.min(p.cap, map[p.index][toIndex]);
                if (cap > 0) {
                    to.set_cap(time, cap, p);
                    if (to == end) {
                        return cap;
                    }
                    else {
                        cap = search(time, to);
                        if (cap > 0) {
                            return cap;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static int search_next() {
        int t = ++time;
        start.set_cap(t, Integer.MAX_VALUE, null);
        int cap = search(t, start);
        if (cap == -1) return -1;
        int toIndex = end.index;
        Point p = end;
        while (p.prev != null) {
            p = p.prev;
            int fromIndex = p.index;
            map[fromIndex][toIndex] -= cap;
            map[toIndex][fromIndex] += cap;
            toIndex = fromIndex;
        }
        return cap;
    }

    private static class Point {
        int cap;
        int time;
        int index;
        int[] next;
        Point prev;

        Point(int index) {
            this.index = index;
            next = new int[0];
        }

        Point(int index, int[] next) {
            this.index = index;
            this.next = next;
        }

        void set_cap(int time, int cap, Point prev) {
            this.time = time;
            this.cap = cap;
            this.prev = prev;
        }
    }
}   