import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Triangles3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));

        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<Integer>> rows = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> cols = new HashMap<>();
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
            rows.get(row).add(col);
            if (!cols.containsKey(col)) {
                cols.put(col, new ArrayList<>());
            }
            cols.get(col).add(row);
        }

        int ans = 0;
        int MAX = 1000000007;
        for (int[] point : points) {
            for (int row : rows.get(point[0])) {
                for (int col : cols.get(point[1])) {
                    long temp = Math.abs((long) (row - point[1]) * (long) (col - point[0])) % MAX;
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
}
