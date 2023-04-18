import java.io.*;
import java.util.LinkedList;

public class CowNavigation7 {
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
        boolean[][][][][] visited = new boolean[n][n][n][n][4];
        int a = BFS(visited, data, 0, N);
        int b = BFS(visited, data, 1, N);
        int min = Math.min(a, b);

        pw.println(min);
        pw.close();
    }

    private static int BFS(boolean[][][][][] visited, boolean[][] data, int dir, int N) {
        LinkedList<state> queue = new LinkedList<>();
        queue.offer(new state(0, 0, 0, 0, dir, 0));

        int min = Integer.MAX_VALUE / 2;
        while (!queue.isEmpty()) {
            state cur = queue.removeFirst();
            visited[cur.ax][cur.ay][cur.bx][cur.by][cur.dir] = true;

            if (cur.ax == N && cur.ay == N && cur.bx == N && cur.by == N) {
                if (cur.cost < min) {
                    min = cur.cost;
                }
            }

            //turn left
            int newDir = (cur.dir + 3) % 4;
            state leftTurn = new state(cur.ax, cur.ay, cur.bx, cur.by, newDir, cur.cost + 1);
            if (!visited[cur.ax][cur.ay][cur.bx][cur.by][newDir] && !queue.contains(leftTurn)) {
                queue.offer(leftTurn);
            }

            //turn right
            newDir = (dir + 1) % 4;
            state rightTurn = new state(cur.ax, cur.ay, cur.bx, cur.by, newDir, cur.cost + 1);
            if (!visited[cur.ax][cur.ay][cur.bx][cur.by][newDir] && !queue.contains(rightTurn)) {
                queue.offer(rightTurn);
            }

            //forward
            if (cur.dir == 0) {
                int ay = cur.ay;
                int bx = cur.bx;
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ay < N) {
                        state next = new state(cur.ax, cur.ay + 1, cur.bx, cur.by, cur.dir, cur.cost + 1);
                        if (!visited[cur.ax][cur.ay + 1][cur.bx][cur.by][cur.dir] && !data[cur.ax][cur.ay + 1] && !queue.contains(next)) {
                            queue.offer(next);
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.bx < N) {
                        state next = new state(cur.ax, cur.ay, cur.bx + 1, cur.by, cur.dir, cur.cost + 1);
                        if (!visited[cur.ax][cur.ay][cur.bx + 1][cur.by][cur.dir] && !data[cur.bx + 1][cur.by] && !queue.contains(next)) {
                            queue.offer(next);
                        }
                    }
                }
            } else if (cur.dir == 1) {
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ax < N) {
                        state next = new state(cur.ax + 1, cur.ay, cur.bx, cur.by, cur.dir, cur.cost + 1);
                        if (!visited[cur.ax + 1][cur.ay][cur.bx][cur.by][cur.dir] && !data[cur.ax + 1][cur.ay] && !queue.contains(next)) {
                            queue.offer(next);
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.by > 0) {
                        state next = new state(cur.ax, cur.ay, cur.bx, cur.by - 1, cur.dir, cur.cost + 1);
                        if (!visited[cur.ax][cur.ay][cur.bx][cur.by - 1][cur.dir] && !data[cur.bx][cur.by - 1] && !queue.contains(next)) {
                            queue.offer(next);
                        }
                    }
                }
            } else if (cur.dir == 2) {
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ay > 0) {
                        state next = new state(cur.ax, cur.ay - 1, cur.bx, cur.by, cur.dir, cur.cost + 1);
                        if (!visited[cur.ax][cur.ay - 1][cur.bx][cur.by][cur.dir] && !data[cur.ax][cur.ay - 1] && !queue.contains(next)) {
                            queue.offer(next);
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.bx > 0) {
                        state next = new state(cur.ax, cur.ay, cur.bx - 1, cur.by, cur.dir, cur.cost + 1);
                        if (!visited[cur.ax][cur.ay][cur.bx - 1][cur.by][cur.dir] && !data[cur.bx - 1][cur.by] && !queue.contains(next)) {
                            queue.offer(next);
                        }
                    }
                }
            } else {
                if (!(cur.ax == N && cur.ay == N)) {
                    if (cur.ax > 0) {
                        state next = new state(cur.ax - 1, cur.ay, cur.bx, cur.by, cur.dir, cur.cost + 1);
                        if (!visited[cur.ax - 1][cur.ay][cur.bx][cur.by][cur.dir] && !data[cur.ax - 1][cur.ay] && !queue.contains(next)) {
                            queue.offer(next);
                        }
                    }
                }
                if (!(cur.bx == N && cur.by == N)) {
                    if (cur.by < N) {
                        state next = new state(cur.ax, cur.ay, cur.bx, cur.by + 1, cur.dir, cur.cost + 1);
                        if (!visited[cur.ax][cur.ay][cur.bx][cur.by + 1][cur.dir] && !data[cur.bx][cur.by + 1] && !queue.contains(next)) {
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return min;
    }

    private static class state {
        int ax, ay, bx, by, dir, cost;

        private state(int ax, int ay, int bx, int by, int dir, int cost) {
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

            state other = (state) obj;
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
