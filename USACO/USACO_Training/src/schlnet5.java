/*
ID: jerryya2
LANG: JAVA
TASK: schlnet
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class schlnet5 {
    private static School[] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("schlnet.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("schlnet.out")));

        int n = Integer.parseInt(br.readLine());
        points = new School[n];
        for (int i = 0; i < n; i++) {
            points[i] = new School(i);
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken()) - 1;
            while (temp != -1) {
                link(i, temp);
                temp = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        int start = 0;
        for (int i = 0; i < n; i++) {
            if (points[i].start == 0) {
                start = 1 + points[i].walkNext(start + 1);
            }
        }
        ArrayList<School> list = new ArrayList<>();
        while (true) {
            School max = null;
            for (int i = 0; i < n; i++) {
                School p = points[i];
                if (p.group == null && (max == null || max.end < p.end)) {
                    max = p;
                }
            }
            if (max == null) break;
            School g = new School(list.size());
            list.add(g);
            max.walkPrev(g, max.start, max.end);
        }
        int g = list.size();
        School[] gs = new School[g];
        list.toArray(gs);
        boolean[][] map = new boolean[g][g];
        for (int i = 0; i < n; i++) {
            points[i].buildGroupMap(map);
        }
        n = g;
        points = gs;
        for (int i = 0; i < g; i++) {
            for (int j = 0; j < g; j++) {
                if (map[i][j]) {
                    link(i, j);
                }
            }
        }

        int roots = 0;
        int leaves = 0;
        for (int i = 0; i < n; i++) {
            if (points[i].prev.size() == 0) {
                roots++;
            }
            if (points[i].next.size() == 0) {
                leaves++;
            }
        }
        if (n == 1) {
            leaves = 0;
        }
        else if (leaves < roots)
            leaves = roots;
        pw.println(roots);
        pw.println(leaves);
        pw.close();
    }

    static void link(int from, int to) {
        points[from].addNext(to);
        points[to].addPrev(from);
    }

    static class School {
        int start, end, index;
        ArrayList<Integer> next;
        ArrayList<Integer> prev;
        School group;

        School(int index) {
            this.index = index;
            this.next = new ArrayList<>();
            this.prev = new ArrayList<>();
        }

        void addNext(int next) {
            this.next.add(next);
        }

        void addPrev(int prev) {
            this.prev.add(prev);
        }

        int walkNext(int start) {
            this.start = start;
            for (Integer integer : next) {
                School next = points[integer];
                if (next.start == 0) {
                    start = 1 + next.walkNext(start + 1);
                }
            }
            this.end = ++start;
            return start;
        }

        void walkPrev(School g, int start, int end) {
            this.group = g;
            for (Integer integer : prev) {
                School prev = points[integer];
                if (prev.group == null && prev.start >= start && prev.end <= end) {
                    prev.walkPrev(g, start, end);
                }
            }
        }

        void buildGroupMap(boolean[][] map) {
            for (Integer integer : next) {
                School next = points[integer];
                if (next.group != this.group) {
                    map[this.group.index][next.group.index] = true;
                }
            }
        }
    }
}

