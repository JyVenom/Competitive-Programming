import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CowNavigation5 {
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

        HashMap<state, ArrayList<state>> edges = new HashMap<>();

        boolean[][][][][] visited = new boolean[n][n][n][n][4];
        BFS(visited, 0, 0, 0, 0, 0);
        BFS(visited, 0, 0, 0, 0, 1);

        pw.close();
    }

    private static void BFS(boolean[][][][][] visited, int ax, int ay, int bx, int by, int dir) {
        LinkedList<state> queue = new LinkedList<>();
        queue.offer(new state(ax, ay, bx, by, dir));

        while (!queue.isEmpty()) {
            state cur = queue.removeFirst();
            visited[cur.ax][cur.ay][cur.bx][cur.by][cur.dir] = true;

            //turn left
            int newDir = (cur.dir + 3) % 4;
            state next = new state(cur.ax, cur.ay, cur.bx, cur.by, cur.dir);
            if (!visited[cur.ax][cur.ay][cur.bx][cur.by][newDir] && !queue.contains(next)) {
                queue.offer(new state(cur.ax, cur.ay, cur.bx, cur.by, cur.dir));
            }
            //turn right
            newDir = (dir + 1) % 4;
            if (!visited[ax][ay][bx][by][newDir]) {
                queue.offer(new state(ax, ay, bx, by, dir));
            }
            //forward

        }
    }

    private static class state {
        int ax, ay, bx, by, dir;

        private state(int ax, int ay, int bx, int by, int dir) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.dir = dir;
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
