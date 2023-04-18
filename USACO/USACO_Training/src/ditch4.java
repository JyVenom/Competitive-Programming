/*
ID: jerryya2
LANG: JAVA
TASK: ditch
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ditch4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ditch.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] capacity = new int[m][m];
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            capacity[s][e] = c;
            edges.get(s).add(e);
        }

        pw.println(maxFlow(m, edges, capacity));
        pw.close();
    }

    private static long maxFlow(int m, ArrayList<ArrayList<Integer>> edges, int[][] capacity) {
        int sink = m - 1;
        if (sink == 0) {
            return Integer.MAX_VALUE;
        }

        int totalFlow = 0;
//        int[] prev = new int[m];
        int[] flow = new int[m];
        boolean[] visited = new boolean[m];
//        Arrays.fill(prev, -1);
        Arrays.fill(flow, 0);
        Arrays.fill(visited, false);

        flow[0] = Integer.MAX_VALUE;
        int maxFlow;
        int maxLoc;
        while (true) {
            maxFlow = 0;
            maxLoc = -1;

            for (int i = 0; i < sink; i++) {
                if (flow[i] > maxFlow && !visited[i]) {
                    maxFlow = flow[i];
                    maxLoc = i;
                }
            }
            if (maxLoc == -1) {
                break;
            }
            visited[maxLoc] = true;
            ArrayList<Integer> cur = edges.get(maxLoc);
            for (int i : cur) {
                int min = Math.min(maxFlow, capacity[maxLoc][i]);
                if (i < sink) {
                    if (flow[i] < min) {
//                        prev[i] = maxLoc;
                        flow[i] = min;
                    }
                }
                else {
                    flow[sink] += min;
                }
            }
        }
        totalFlow += flow[sink];

//        int cur = sink;
//        while (cur != 0) {
//            int next = prev[cur];
//            capacity[next][cur] -= flow[sink];
//            capacity[cur][next] -= flow[sink];
//            cur = next;
//        }

        return totalFlow;
    }
}
