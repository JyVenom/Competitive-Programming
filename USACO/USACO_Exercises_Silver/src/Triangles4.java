import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Triangles4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));

        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<point>> rows = new HashMap<>();
        HashMap<Integer, ArrayList<point>> cols = new HashMap<>();
        int[][] points = new int[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());

            points[i][0] = row;
            points[i][1] = col;

            if (!rows.containsKey(row)) {
                rows.put(row, new ArrayList<>());
            }
            rows.get(row).add(new point(col, i));
            if (!cols.containsKey(col)) {
                cols.put(col, new ArrayList<>());
            }
            cols.get(col).add(new point(row, i));
        }

        int ans = 0;
        int MAX = 1000000007;
        long[][] dist = new long[n][n];
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            for (point row : rows.get(point[0])) {
                for (point col : cols.get(point[1])) {
                    long temp;
                    if (dist[row.index][i] != 0) {
                        if (dist[col.index][i] != 0) {
                            temp = (dist[row.index][i] * dist[col.index][i]) % MAX;
                        }
                        else {
                            long temp2 = Math.abs(col.val - point[0]);
                            dist[col.index][i] = temp2;
                            dist[i][col.index] = temp2;
                            temp = Math.abs((long) (row.val - point[1]) * temp2) % MAX;
                        }
                    }
                    else {
                        long temp2 = Math.abs(row.val - point[1]);
                        if (dist[col.index][i] != 0) {
                            dist[row.index][i] = temp2;
                            dist[i][row.index] = temp2;
                            temp = Math.abs(temp2 * (long) (col.val - point[0])) % MAX;
                        }
                        else {
                            long temp3 = Math.abs(col.val - point[0]);
                            dist[col.index][i] = temp3;
                            dist[i][col.index] = temp3;
                            temp = Math.abs(temp2 * temp3) % MAX;
                        }
                    }
                    ans += temp;
                    ans = ans % MAX;
                }
            }
//            long temp = ((long) (rows.get(point[0]).size() - 1) * (long) (cols.get(point[1]).size() - 1)) % MAX;
//            ans += ((long) (rows.get(point[0]).size() - 1) * (long) (cols.get(point[1]).size() - 1)) % MAX;
        }

        pw.println(ans);
        pw.close();
    }

    private static class point {
        int val, index;

        private point(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}
