/*
ID: jerryya2
LANG: JAVA
TASK: milk6
*/

//O(2*(max_flow * E) + removed_edges * log2(n))
//e < 1000
//n < 32
//removed edges < n
//max_flow < cm
//c < 2,000,000
//4e9

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class milk6 {
    private static int timestamp = 1;
    private static int n, m;
    private static int[][] map;
    private static Road[][] roadMap;
    private static Road[] roads;
    private static Point[] points;
    private static Point start;
    private static Point end;
    private static Road[] sorts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk6.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(i);
        }
        roadMap = new Road[n][n];
        ArrayList<Road> roadList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            Point from = points[Integer.parseInt(st.nextToken()) - 1];
            Point to = points[Integer.parseInt(st.nextToken()) - 1];
            int cost = Integer.parseInt(st.nextToken());
            Road road = roadMap[from.index][to.index];
            if (road == null) {
                road = new Road(i);
                road.from = from;
                road.to = to;
                road.cost = cost;
                roadMap[from.index][to.index] = road;
                roadList.add(road);
            } else {
                road.cost += cost;
                road.addIndex(i);
            }
        }

        m = roadList.size();
        roads = new Road[m];
        roadList.toArray(roads);
        start = points[0];
        end = points[n - 1];
        int bestCutCost = calcFlow(true);
        while (true) {
            if (!cutMap()) {
                break;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (roads[i].removed)
                list.addAll(roads[i].indexes);
        }
        Collections.sort(list);

        pw.println(bestCutCost + " " + list.size());
        for (Object o : list) {
            pw.println(o);
        }
        pw.close();
    }

    private static void buildMap(boolean cost) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Road r = roadMap[i][j];
                if (r != null && !r.removed) {
                    map[i][j] = cost ? r.cost : r.roadCount();
                } else {
                    map[i][j] = 0;
                }
            }
        }
    }

    private static boolean cutMap() {
        int costCap = calcFlow(true);
        if (costCap == 0) {
            return false;
        }
        if (sorts == null) {
            sorts = new Road[m];
        }
        int sortCount = 0;
        for (int i = 0; i < m; i++) {
            Road r = roads[i];
            if (!r.removed && r.fullFlow) {
                sorts[sortCount++] = r;
            }
        }
        int countCap = calcFlow(false);
        Arrays.sort(sorts, 0, sortCount);
        Road maxRoad = null;
        for (int i = 0; i < sortCount; i++) {
            Road road = sorts[i];
            if (selectRoad(road, costCap, true)) {
                if (selectRoad(road, countCap, false)) {
                    maxRoad = road;
                    break;
                }
                if (maxRoad == null || maxRoad.cost < road.cost || (maxRoad.cost == road.cost && maxRoad.index > road.cost)) {
                    maxRoad = road;
                }
            }
        }
        assert maxRoad != null;
        maxRoad.removed = true;
        return true;
    }

    private static boolean selectRoad(Road road, int lastCap, boolean cost) {
        road.removed = true;
        int cap = calcFlow(cost);
        road.removed = false;
        if (!road.fullFlow) {
            return false;
        }
        if (cost) {
            return lastCap - cap == road.cost;
        } else {
            return lastCap - cap == road.roadCount();
        }
    }

    private static int calcFlow(boolean cost) {
        buildMap(cost);
        int r = walkAll();
        for (int i = 0; i < m; i++) {
            roads[i].updateState();
        }
        return r;
    }

    private static int walkAll() {
        int totalCap = 0;
        for (; ; ) {
            start.cap = Integer.MAX_VALUE;
            int time = ++timestamp;
            if (!walk(start, time)) {
                break;
            }
            int cap = end.cap;
            totalCap += cap;
            Point p = end.prev;
            int nexIndex = end.index;
            while (p != null) {
                int index = p.index;
                map[index][nexIndex] -= cap;
                map[nexIndex][index] += cap;
                nexIndex = index;
                p = p.prev;
            }
        }
        return totalCap;
    }

    private static boolean walk(Point p, int time) {
        p.time = time;
        if (p == end) {
            return true;
        }
        int index = p.index;
        for (int i = 0; i < n; i++) {
            int v = map[index][i];
            if (v > 0) {
                Point next = points[i];
                if (next.time != time) {
                    next.cap = Math.min(p.cap, v);
                    next.prev = p;
                    if (walk(next, time)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static class Point {
        int time;
        int cap;
        int index;
        Point prev = null;

        Point(int index) {
            this.index = index;
        }
    }

    private static class Road implements Comparable<Road> {
        boolean removed = false;
        int index;
        Point from;
        Point to;
        int cost;
        boolean fullFlow;
        ArrayList<Integer> indexes = new ArrayList<>();

        Road(int index) {
            this.index = index;
            addIndex(index);
        }

        void addIndex(int index) {
            indexes.add(index + 1);
        }

        int roadCount() {
            return indexes.size();
        }

        void updateState() {
            fullFlow = map[from.index][to.index] == 0;
        }

        @Override
        public int compareTo(Road r1) {
            if (fullFlow != r1.fullFlow) {
                return fullFlow ? -1 : 1;
            }
            int r = r1.cost - cost;
            if (r != 0) return r;
            return index - r1.index;
        }
    }
} 