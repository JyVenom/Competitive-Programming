import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class P1438C3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int n1 = n - 1;
            int m1 = m - 1;

            int[][] c = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    c[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            HashMap<Integer, HashMap<Integer, cell>> map = new HashMap<>(n);
            ArrayList<cell> cells = new ArrayList<>(n * m);
            for (int i = 0; i < n; i++) {
                map.put(i, new HashMap<>());
                for (int j = 0; j < m; j++) {
                    map.get(i).put(j, new cell(i, j));
                    cells.add(map.get(i).get(j));
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    if (c[i][j] == c[i][j - 1]) {
                        map.get(i).get(j).connections.add(3);
                        map.get(i).get(j - 1).connections.add(1);
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (c[j][i] == c[j - 1][i]) {
                        map.get(j).get(i).connections.add(0);
                        map.get(j - 1).get(i).connections.add(2);
                    }
                }
            }

            PriorityQueue<cell> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.connections.size(), o1.connections.size()));
            pq.addAll(cells);
            while (!pq.isEmpty()) {
                cell cur = pq.poll();

                if (cur.connections.size() == 0) {
                    break;
                }

                if (cur.visited) {
                    continue;
                }

                cur.visited = true;
                c[cur.row][cur.col]++;

                for (int dir : cur.connections) {
                    if (dir == 0) {
                        map.get(cur.row - 1).get(cur.col).connections.remove(2);
                    } else if (dir == 1) {
                        map.get(cur.row).get(cur.col + 1).connections.remove(3);
                    } else if (dir == 2) {
                        map.get(cur.row + 1).get(cur.col).connections.remove(0);
                    } else {
                        map.get(cur.row).get(cur.col - 1).connections.remove(1);
                    }
                }
                cur.connections.clear();

                if (cur.row > 0) {
                    if (c[cur.row][cur.col] == c[cur.row - 1][cur.col]) {
                        cur.connections.add(0);
                        map.get(cur.row - 1).get(cur.col).connections.add(2);
                    }
                }
                if (cur.col > 0) {
                    if (c[cur.row][cur.col] == c[cur.row][cur.col - 1]) {
                        cur.connections.add(3);
                        map.get(cur.row).get(cur.col - 1).connections.add(1);
                    }
                }
                if (cur.row < n1) {
                    if (c[cur.row][cur.col] == c[cur.row + 1][cur.col]) {
                        cur.connections.add(2);
                        map.get(cur.row + 1).get(cur.col).connections.add(0);
                    }
                }
                if (cur.col < m1) {
                    if (c[cur.row][cur.col] == c[cur.row][cur.col + 1]) {
                        cur.connections.add(1);
                        map.get(cur.row).get(cur.col + 1).connections.add(3);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    pw.print(c[i][j] + " ");
                }
                pw.println();
            }
        }

        pw.close();
    }

    private static class cell {
        int row, col;
        boolean visited = false;
        HashSet<Integer> connections = new HashSet<>();

        public cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
