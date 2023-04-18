import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoad {
    private static int t;
    private static int n;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("visitfj.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        N = n - 1;
        t = Integer.parseInt(st.nextToken());
        int[][] data = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        ArrayList<ArrayList<int[]>> edges = new ArrayList<>();
//        int n2 = n * n;
//        for (int i = 0; i < n2; i++) {
//            edges.add(new ArrayList<>());
//        }

        pw.println(BFS(data));
        pw.close();
    }

    private static int BFS(int[][] data) {
        LinkedList<state> queue = new LinkedList<>();
        queue.add(new state(0, 0, 0));
        int[][][] dp = new int[n][n][3];

        while (!queue.isEmpty()) {
            state cur = queue.removeFirst();

            int temp = dp[cur.row][cur.col][cur.rem] + t;
            for (state next : cur.genNext()) {
                int temp2 = temp;
                if (next.rem == 0) {
                    temp2 += data[next.row][next.col];
                }
                if (dp[next.row][next.col][next.rem] == 0 || temp2 < dp[next.row][next.col][next.rem]) {
                    dp[next.row][next.col][next.rem] = temp2;
                    queue.add(next);
                }
            }
        }
        return Math.min(dp[N][N][0], Math.min(dp[N][N][1], dp[N][N][2]));
    }

//    private static void genNext(ArrayList<ArrayList<int[]>> edges, int[][] data, int row, int col, int rem, int start, int n, int N, int cost) {
//        if (rem == 0) {
//            edges.get(start).add(new int[]{(row * n) + col, cost + data[row][col]});
//        }
//
//        int rem2 = rem - 1;
//        if (row > 0) {
//            genNext(edges, data, row - 1, col, rem2, start, n, N, cost);
//        }
//        if (col < N) {
//            genNext(edges, data, row, col + 1, rem2, start, n, N, cost);
//        }
//        if (row < N) {
//            genNext(edges, data, row + 1, col, rem2, start, n, N, cost);
//        }
//        if (col > 0) {
//            genNext(edges, data, row, col - 1, rem2, start, n, N, cost);
//        }
//    }

    private static class state {
        int row, col, rem;

        private state(int row, int col, int rem) {
            this.row = row;
            this.col = col;
            this.rem = rem;
        }

        private ArrayList<state> genNext() {
            ArrayList<state> temp = new ArrayList<>();

            int rem2;
            if (rem == 0) {
                rem2 = 2;
            } else {
                rem2 = rem - 1;
            }
            if (row > 0) {
                temp.add(new state(row - 1, col, rem2));
            }
            if (col < N) {
                temp.add(new state(row, col + 1, rem2));
            }
            if (row < N) {
                temp.add(new state(row + 1, col, rem2));
            }
            if (col > 0) {
                temp.add(new state(row, col - 1, rem2));
            }

            return temp;
        }
    }
}
