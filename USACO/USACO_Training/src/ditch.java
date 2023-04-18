/*
ID: jerryya2
LANG: JAVA
TASK: ditch
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ditch {
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

            capacity[s][e] += c;
            edges.get(s).add(e);
        }

        int max = 0;
        int temp = maxFlow(m, capacity);
        while (temp != -1) {
            max += temp;
            temp = maxFlow(m, capacity);
        }
        pw.println(max);
        pw.close();
    }

    private static int maxFlow(int m, int[][] capacity) {
        int maxFlow;
        int maxLoc;
        int[] prev = new int[m];
        int[] flow = new int[m];
        boolean[] visited = new boolean[m];

        Arrays.fill(prev, -1);
        Arrays.fill(flow, 0);
        Arrays.fill(visited, false);

        flow[0] = Integer.MAX_VALUE;

        int sink = m - 1;
        while (true) {
            maxFlow = 0;
            maxLoc = -1;

            for (int i = 0; i < m; i++) {
                if (!visited[i] && flow[i] > maxFlow) {
                    maxFlow = flow[i];
                    maxLoc = i;
                }
            }
            if (maxLoc == -1) {
                return -1;
            }
            if (maxLoc == sink) {
                break;
            }
            visited[maxLoc] = true;
            for (int i = 0; i < m; i++) {
                if (capacity[maxLoc][i] > flow[i] && maxFlow > flow[i]) {
                    flow[i] = capacity[maxLoc][i];
                    if (flow[i] > maxFlow) {
                        flow[i] = maxFlow;
                    }
                    prev[i] = maxLoc;
                }
            }
        }
        maxFlow = flow[sink];

        for (int i = sink; i > 0; i = prev[i]) {
            capacity[prev[i]][i] -= maxFlow;
            capacity[i][prev[i]] += maxFlow;
        }
        return maxFlow;
    }
}
