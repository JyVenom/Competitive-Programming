import java.io.*;
import java.util.LinkedList;

public class CowNavigation11 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownav.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));

        int n = Integer.parseInt(br.readLine());
        int N = n - 1;
        boolean[][] data = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = line.charAt(j);
                if (c == 'H') {
                    data[N - i][j] = true;
                }
            }
        }

//        HashMap<state, ArrayList<state>> edges = new HashMap<>();
        boolean[][][][][][] visited = new boolean[n][n][4][n][n][4];
        int min = BFS(visited, data, N);

        pw.println(min);
        pw.close();
    }

    private static int BFS(boolean[][][][][][] visited, boolean[][] data, int N) {
        LinkedList<state> queue = new LinkedList<>();
        queue.offer(new state(0, 0, 0, 0, 0, 1, 0));
        queue.offer(new state(0, 0, 1, 0, 0, 0, 0));

        while (!queue.isEmpty()) {
            state cur = queue.removeFirst();
            visited[cur.ax][cur.ay][cur.ad][cur.bx][cur.by][cur.bd] = true;

            if (cur.ax == N && cur.ay == N && cur.bx == N && cur.by == N) {
                return cur.cost;
            }

            //turn left
            int newAd = (cur.ad + 3) % 4;
            int newBd = (cur.bd + 3) % 4;
            state leftTurn = new state(cur.ax, cur.ay, newAd, cur.bx, cur.by, newBd, cur.cost + 1);
            if (!visited[cur.ax][cur.ay][newAd][cur.bx][cur.by][newBd] && !queue.contains(leftTurn)) {
                queue.offer(leftTurn);
            }

            //turn right
            newAd = (cur.ad + 1) % 4;
            newBd = (cur.bd + 1) % 4;
            state rightTurn = new state(cur.ax, cur.ay, newAd, cur.bx, cur.by, newBd, cur.cost + 1);
            if (!visited[cur.ax][cur.ay][newAd][cur.bx][cur.by][newBd] && !queue.contains(rightTurn)) {
                queue.offer(rightTurn);
            }

            int ax = cur.ax;
            int ay = cur.ay;
            int bx = cur.bx;
            int by = cur.by;
            //forward
            if (cur.ad == 0) {
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ay < N) {
                        if (!data[cur.ay + 1][cur.ax]) {
                            ay++;
                        }
                    }
                }
            } else if (cur.ad == 1) {
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ax < N) {
                        if (!data[cur.ay][cur.ax + 1]) {
                            ax++;
                        }
                    }
                }
            } else if (cur.ad == 2) {
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ay > 0) {
                        if (!data[cur.ay - 1][cur.ax]) {
                            ay--;
                        }
                    }
                }
            } else {
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ax > 0) {
                        if (!data[cur.ay][cur.ax - 1]) {
                            ax--;
                        }
                    }
                }
            }
            if (cur.bd == 0) {
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.by < N) {
                        if (!data[cur.by + 1][cur.bx]) {
                            by++;
                        }
                    }
                }
            }
            else if (cur.bd == 1) {
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.bx < N) {
                        if (!data[cur.by][cur.bx + 1]) {
                            bx++;
                        }
                    }
                }
            }
            else if (cur.bd == 2) {
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.by > 0) {
                        if (!data[cur.by - 1][cur.bx]) {
                            by--;
                        }
                    }
                }
            }
            else {
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.bx > 0) {
                        if (!data[cur.by][cur.bx - 1]) {
                            bx--;
                        }
                    }
                }
            }
            state next = new state(ax, ay, cur.ad, bx, by, cur.bd, cur.cost + 1);
            if (!visited[ax][ay][cur.ad][bx][by][cur.bd] && !queue.contains(next)) {
                queue.offer(next);
            }
        }
        return Integer.MAX_VALUE;
    }

    private static class state {
        int ax, ay, bx, by, ad, bd, cost;

        private state(int ax, int ay, int ad, int bx, int by, int bd, int cost) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.ad = ad;
            this.bd = bd;
            this.cost = cost;
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
            if (ad == other.ad) {
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
    }
}
