/*
ID: jerryya2
LANG: JAVA
TASK: cowtour
*/

import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;

public class cowtour {
    static int[] x;
    static int[] y;
    static int[][] grid;
    static boolean[] visited;
    static int[] id;
    static int count;
    static int N;
    static double[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowtour.in"));
        PrintWriter pw = new PrintWriter(new File("cowtour.out"));

        N = Integer.parseInt(br.readLine());
        x = new int[N];
        y = new int[N];
        id = new int[N];
        visited = new boolean[N];
        grid = new int[N][N];
        dist = new double[N][N];
        count = 1;

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            x[i] = Integer.parseInt(line[0]);
            y[i] = Integer.parseInt(line[1]);
        }
        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(arr[j]);
            }
        }
        br.close();

        findCC();
        for (double[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) dist[i][j] = 0;
                else if (grid[i][j] == 1) dist[i][j] = dist(i, j);
            }
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        double[] diameters = new double[count + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (id[i] == id[j]) {
                    diameters[id[i]] = Math.max(diameters[id[i]], dist[i][j]);
                }
            }
        }
        double minDiam = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (id[i] != id[j]) {
                    double diam1 = 0;
                    double diam2 = 0;
                    for (int k = 0; k < N; k++) {
                        if (id[k] == id[i]) diam1 = Math.max(diam1, dist[i][k]);
                    }

                    for (int k = 0; k < N; k++) {
                        if (id[k] == id[j]) diam2 = Math.max(diam2, dist[j][k]);
                    }

                    double diam = Math.max(diameters[id[i]], Math.max(diameters[id[j]], diam1 + dist(i, j) + diam2));
                    minDiam = Math.min(minDiam, diam);
                }
            }
        }

        DecimalFormat df = new DecimalFormat("#0.000000");
        pw.println(df.format(minDiam));
        pw.close();
    }

    public static void findCC() {
        for (int v = 0; v < N; v++) {
            if (!visited[v]) {
                dfs(v);
                count++;
            }
        }

    }

    private static void dfs(int v) {
        visited[v] = true;
        id[v] = count;
        for (int i = 0; i < N; i++) {
            if (i != v && !visited[i] && grid[v][i] == 1) dfs(i);
        }
    }

    public static double dist(int v1, int v2) {
        int xcomp = (x[v1] - x[v2]) * (x[v1] - x[v2]);
        int ycomp = (y[v1] - y[v2]) * (y[v1] - y[v2]);
        return Math.sqrt(xcomp + ycomp);
    }

}