/*
ID: jerryya2
LANG: JAVA
TASK: race3
*/

//m + n * (n + m)
//n <= 50
//m <= 100
//7600

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class race3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("race3.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("race3.out")));

        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        String line = br.readLine();
        while (!(line.equals("-1"))) {
            ArrayList<Integer> point = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                int street = Integer.parseInt(st.nextToken());
                if (street != -2) {
                    point.add(street);
                }
            }
            Collections.sort(point);
            points.add(point);
            line = br.readLine();
        }
        int N = points.size();
        int n = N - 1;
        points.remove(n);

        ArrayList<Integer> ans1 = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            boolean[] visited = new boolean[N];
            DFS(points, visited, i, n, 0);
            if (!visited[n]) {
                ans1.add(i);
            }
        }
        ArrayList<Integer> ans2 = new ArrayList<>(ans1);
        for (int i = 0; i < ans2.size(); i++) {
            int cur = ans2.get(i);
            boolean[] visited = new boolean[N];
            DFS2(points, visited, cur, 0);
            boolean[] visited2 = new boolean[N];
            if (DFS3(points, visited2, visited, n, cur)) {
                ans2.remove(i);
                i--;
            }
        }

        pw.print(ans1.size());
        for (int ans : ans1) {
            pw.print(" " + ans);
        }
        pw.println();
        pw.print(ans2.size());
        for (int ans : ans2) {
            pw.print(" " + ans);
        }
        pw.println();
        pw.close();
    }

    private static void DFS(ArrayList<ArrayList<Integer>> points, boolean[] visited, int avoid, int end, int at) {
        visited[at] = true;
        if (at == end) {
            return;
        }

        for (int next : points.get(at)) {
            if (next != avoid) {
                if (!visited[next]) {
                    DFS(points, visited, avoid, end, next);
                    if (visited[end]) {
                        return;
                    }
                }
            }
        }
    }

    private static void DFS2(ArrayList<ArrayList<Integer>> points, boolean[] visited, int avoid, int at) {
        visited[at] = true;

        for (int next : points.get(at)) {
            if (next != avoid) {
                if (!visited[next]) {
                    DFS2(points, visited, avoid, next);
                }
            }
        }
    }

    private static boolean DFS3(ArrayList<ArrayList<Integer>> points, boolean[] visited, boolean[] first, int end, int at) {
        visited[at] = true;

        for (int next : points.get(at)) {
            if (first[next]) {
                return true;
            }
            if (!visited[next]) {
                if (next != end) {
                    if (DFS3(points, visited, first, end, next)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
