import java.io.*;
import java.util.LinkedList;

public class CowNavigation14 {
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

        int[][][][][][] dp = new int[n][n][4][n][n][4];
        int min = BFS(dp);

        pw.println(min);
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }

            state other = (state) obj;
            if (ax != other.ax) {
                return false;
            }
            if (ay != other.ay) {
                return false;
            }
            if (ad != other.ad) {
                return false;
            }
            if (bx != other.bx) {
                return false;
            }
            if (by != other.by) {
                return false;
            }
            return bd == other.bd;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ax;
            result = prime * result + ay;
            result = prime * result + ad;
            result = prime * result + bx;
            result = prime * result + by;
            result = prime * result + bd;
            return result;
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
//            if (nax != 0 || nay != N) {
//                if (!data[nax][nay]) {
//                    int temp = nax + dx[ad];
//                    if (temp >= 0 && temp < n) {
//                        nax = temp;
//                    }
//                    temp = nay + dy[ad];
//                    if (temp >= 0 && temp < n) {
//                        nay = temp;
//                    }
//                }
//            }
//            if (nbx != 0 || nby != n - 1) {
//                if (!data[nbx][nby]) {
//                    int temp = nbx + dx[bd];
//                    if (temp >= 0 && temp < n) {
//                        nbx = temp;
//                    }
//                    temp = nby + dy[bd];
//                    if (temp >= 0 && temp < n) {
//                        nby = temp;
//                    }
//                }
//            }
            return new state(nax, nay, ad, nbx, nby, bd);
        }
    }
}
