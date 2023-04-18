import java.io.*;
import java.util.LinkedList;

public class CowNavigation9 {
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

//        HashMap<state1, ArrayList<state1>> edges = new HashMap<>();
        boolean[][][][][] visited = new boolean[n][n][n][n][4];
        int a = BFS1(visited, data, N);
        visited = new boolean[n][n][n][n][4];
        int b = BFS2(visited, data, N);
        int min = Math.min(a, b);

        pw.println(min);
        pw.close();
    }

    private static int BFS1(boolean[][][][][] visited, boolean[][] data, int N) {
        LinkedList<state1> queue = new LinkedList<>();
        queue.offer(new state1(0, 0, 0, 0, 0, 0));

        while (!queue.isEmpty()) {
            state1 cur = queue.removeFirst();
            visited[cur.ax][cur.ay][cur.bx][cur.by][cur.dir] = true;

            if (cur.ax == N && cur.ay == N && cur.bx == N && cur.by == N) {
                return cur.cost;
            }

            //turn left
            int newDir = (cur.dir + 3) % 4;
            state1 leftTurn = new state1(cur.ax, cur.ay, cur.bx, cur.by, newDir, cur.cost + 1);
            if (!visited[cur.ax][cur.ay][cur.bx][cur.by][newDir] && !queue.contains(leftTurn)) {
                queue.offer(leftTurn);
            }

            //turn right
            newDir = (cur.dir + 1) % 4;
            state1 rightTurn = new state1(cur.ax, cur.ay, cur.bx, cur.by, newDir, cur.cost + 1);
            if (!visited[cur.ax][cur.ay][cur.bx][cur.by][newDir] && !queue.contains(rightTurn)) {
                queue.offer(rightTurn);
            }

            //forward
            if (cur.dir == 0) {
                int ay = cur.ay;
                int bx = cur.bx;
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ay < N) {
                        if (!data[cur.ay + 1][cur.ax]) {
                            ay++;
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.bx < N) {
                        if (!data[cur.by][cur.bx + 1]) {
                            bx++;
                        }
                    }
                }
                state1 next = new state1(cur.ax, ay, bx, cur.by, cur.dir, cur.cost + 1);
                if (!visited[cur.ax][ay][bx][cur.by][cur.dir] && !queue.contains(next)) {
                    queue.offer(next);
                }

            } else if (cur.dir == 1) {
                int ax = cur.ax;
                int by = cur.by;
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ax < N) {
                        if (!data[cur.ay][cur.ax + 1]) {
                            ax++;
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.by > 0) {
                        if (!data[cur.by - 1][cur.bx]) {
                            by--;
                        }
                    }
                }
                state1 next = new state1(ax, cur.ay, cur.bx, by, cur.dir, cur.cost + 1);
                if (!visited[ax][cur.ay][cur.bx][by][cur.dir] && !queue.contains(next)) {
                    queue.offer(next);
                }
            } else if (cur.dir == 2) {
                int ay = cur.ay;
                int bx = cur.bx;
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ay > 0) {
                        if (!data[cur.ay - 1][cur.ax]) {
                            ay--;
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.bx > 0) {
                        if (!data[cur.by][cur.bx - 1]) {
                            bx--;
                        }
                    }
                }
                state1 next = new state1(cur.ax, ay, bx, cur.by, cur.dir, cur.cost + 1);
                if (!visited[cur.ax][ay][bx][cur.by][cur.dir] && !queue.contains(next)) {
                    queue.offer(next);
                }
            } else {
                int ax = cur.ax;
                int by = cur.by;
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ax > 0) {
                        if (!data[cur.ay][cur.ax - 1]) {
                            ax--;
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.by < N) {
                        if (!data[cur.by + 1][cur.bx]) {
                            by++;
                        }
                    }
                }
                state1 next = new state1(ax, cur.ay, cur.bx, by, cur.dir, cur.cost + 1);
                if (!visited[ax][cur.ay][cur.bx][by][cur.dir] && !queue.contains(next)) {
                    queue.offer(next);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static int BFS2(boolean[][][][][] visited, boolean[][] data, int N) {
        LinkedList<state2> queue = new LinkedList<>();
        queue.offer(new state2(0, 0, 0, 0, 1, 0));

        while (!queue.isEmpty()) {
            state2 cur = queue.removeFirst();
            visited[cur.ax][cur.ay][cur.bx][cur.by][cur.dir] = true;

            if (cur.ax == N && cur.ay == N && cur.bx == N && cur.by == N) {
                return cur.cost;
            }

            //turn left
            int newDir = (cur.dir + 3) % 4;
            state2 leftTurn = new state2(cur.ax, cur.ay, cur.bx, cur.by, newDir, cur.cost + 1);
            if (!visited[cur.ax][cur.ay][cur.bx][cur.by][newDir] && !queue.contains(leftTurn)) {
                queue.offer(leftTurn);
            }

            //turn right
            newDir = (cur.dir + 1) % 4;
            state2 rightTurn = new state2(cur.ax, cur.ay, cur.bx, cur.by, newDir, cur.cost + 1);
            if (!visited[cur.ax][cur.ay][cur.bx][cur.by][newDir] && !queue.contains(rightTurn)) {
                queue.offer(rightTurn);
            }

            //forward
            if (cur.dir == 0) {
                int ay = cur.ay;
                int bx = cur.bx;
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ay < N) {
                        if (!data[cur.ay + 1][cur.ax]) {
                            ay++;
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.bx > 0) {
                        if (!data[cur.by][cur.bx - 1]) {
                            bx--;
                        }
                    }
                }
                state2 next = new state2(cur.ax, ay, bx, cur.by, cur.dir, cur.cost + 1);
                if (!visited[cur.ax][ay][bx][cur.by][cur.dir] && !queue.contains(next)) {
                    queue.offer(next);
                }

            } else if (cur.dir == 1) {
                int ax = cur.ax;
                int by = cur.by;
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ax < N) {
                        if (!data[cur.ay][cur.ax + 1]) {
                            ax++;
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.by < N) {
                        if (!data[cur.by + 1][cur.bx]) {
                            by++;
                        }
                    }
                }
                state2 next = new state2(ax, cur.ay, cur.bx, by, cur.dir, cur.cost + 1);
                if (!visited[ax][cur.ay][cur.bx][by][cur.dir] && !queue.contains(next)) {
                    queue.offer(next);
                }
            } else if (cur.dir == 2) {
                int ay = cur.ay;
                int bx = cur.bx;
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ay > 0) {
                        if (!data[cur.ay - 1][cur.ax]) {
                            ay--;
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.bx < N) {
                        if (!data[cur.by][cur.bx + 1]) {
                            bx++;
                        }
                    }
                }
                state2 next = new state2(cur.ax, ay, bx, cur.by, cur.dir, cur.cost + 1);
                if (!visited[cur.ax][ay][bx][cur.by][cur.dir] && !queue.contains(next)) {
                    queue.offer(next);
                }
            } else {
                int ax = cur.ax;
                int by = cur.by;
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ax > 0) {
                        if (!data[cur.ay][cur.ax - 1]) {
                            ax--;
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.by > 0) {
                        if (!data[cur.by - 1][cur.bx]) {
                            by--;
                        }
                    }
                }
                state2 next = new state2(ax, cur.ay, cur.bx, by, cur.dir, cur.cost + 1);
                if (!visited[ax][cur.ay][cur.bx][by][cur.dir] && !queue.contains(next)) {
                    queue.offer(next);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static class state1 {
        int ax, ay, bx, by, dir, cost;

        private state1(int ax, int ay, int bx, int by, int dir, int cost) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.dir = dir;
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

            state1 other = (state1) obj;
            if (ax != other.ax) {
                return false;
            }
            if (ay != other.ay) {
                return false;
            }
            if (bx != other.bx) {
                return false;
            }
            if (by != other.by) {
                return false;
            }
            return dir == other.dir;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ax;
            result = prime * result + ay;
            result = prime * result + bx;
            result = prime * result + by;
            result = prime * result + dir;
            return result;
        }
    }

    private static class state2 {
        int ax, ay, bx, by, dir, cost;

        private state2(int ax, int ay, int bx, int by, int dir, int cost) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.dir = dir;
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

            state2 other = (state2) obj;
            if (ax != other.ax) {
                return false;
            }
            if (ay != other.ay) {
                return false;
            }
            if (bx != other.bx) {
                return false;
            }
            if (by != other.by) {
                return false;
            }
            return dir == other.dir;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ax;
            result = prime * result + ay;
            result = prime * result + bx;
            result = prime * result + by;
            result = prime * result + dir;
            return result;
        }
    }
}
