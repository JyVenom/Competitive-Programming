import java.io.*;
import java.util.LinkedList;

public class CowNavigation15 {
    private static boolean[][] data;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownav.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));

        int n = Integer.parseInt(br.readLine());
        N = n - 1;
        data = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = line.charAt(j);
                if (c == 'H') {
                    data[N - i][j] = true;
                }
            }
        }

        pw.println(BFS(new int[n][n][4][n][n][4]));
        pw.close();
    }

    private static int BFS(int[][][][][][] dp) {
        LinkedList<state> queue = new LinkedList<>();
        queue.add(new state(0, 0, 0, 0, 0, 1));
        queue.add(new state(0, 0, 1, 0, 0, 0));

        while (!queue.isEmpty()) {
            state cur = queue.removeFirst();

            if (cur.ax == N && cur.bx == N && cur.ay == N && cur.by == N) {
                return dp[cur.ax][cur.ay][cur.ad][cur.bx][cur.by][cur.bd];
            }

            for (state next : cur.genTrans()) {
                if (dp[next.ax][next.ay][next.ad][next.bx][next.by][next.bd] == 0) {
                    dp[next.ax][next.ay][next.ad][next.bx][next.by][next.bd] = dp[cur.ax][cur.ay][cur.ad][cur.bx][cur.by][cur.bd] + 1;
                    queue.add(next);
                }
            }
        }
        return -1;
    }

    private static class state {
        int ax, ay, ad, bx, by, bd;

        private state(int ax, int ay, int ad, int bx, int by, int bd) {
            this.ax = ax;
            this.ay = ay;
            this.ad = ad;
            this.bx = bx;
            this.by = by;
            this.bd = bd;
        }

        public state[] genTrans() {
            return new state[]{turnLeft(), turnRight(), advance()};
        }

        private state turnLeft() {
            return new state(ax, ay, (ad + 3) % 4, bx, by, (bd + 3) % 4);
        }

        private state turnRight() {
            return new state(ax, ay, (ad + 1) % 4, bx, by, (bd + 1) % 4);
        }

        private state advance() {
            int nax = ax;
            int nay = ay;
            int nbx = bx;
            int nby = by;
            if (!(nax == N && nay == N)) {
                if (ad == 0) {
                    int temp = nay + 1;
                    if (nay != N && !data[nax][temp]) {
                        nay = temp;
                    }
                } else if (ad == 1) {
                    int temp = nax + 1;
                    if (nax != N && !data[temp][nay]) {
                        nax = temp;
                    }
                } else if (ad == 2) {
                    int temp = nay - 1;
                    if (nay != 0 && !data[nax][temp]) {
                        nay = temp;
                    }
                } else {
                    int temp = nax - 1;
                    if (nax != 0 && !data[temp][nay]) {
                        nax = temp;
                    }
                }
            }
            if (!(nbx == N && nby == N)) {
                if (bd == 0) {
                    int temp = nby + 1;
                    if (nby != N && !data[nbx][temp]) {
                        nby = temp;
                    }
                } else if (bd == 1) {
                    int temp = nbx + 1;
                    if (nbx != N && !data[temp][nby]) {
                        nbx = temp;
                    }
                } else if (bd == 2) {
                    int temp = nby - 1;
                    if (nby != 0 && !data[nbx][temp]) {
                        nby = temp;
                    }
                } else {
                    int temp = nbx - 1;
                    if (nbx != 0 && !data[temp][nby]) {
                        nbx = temp;
                    }
                }
            }
            return new state(nax, nay, ad, nbx, nby, bd);
        }
    }
}
