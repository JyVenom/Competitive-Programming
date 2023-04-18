import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Triangles6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));

        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<Integer>> rows = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> cols = new HashMap<>();
        HashSet<Integer> rows2 = new HashSet<>();
        HashSet<Integer> cols2 = new HashSet<>();
//        int[][] points = new int[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());

//            points[i][0] = row;
//            points[i][1] = col;

            rows2.add(row);
            cols2.add(col);

            if (!rows.containsKey(row)) {
                rows.put(row, new ArrayList<>());
            }
            rows.get(row).add(col);
            if (!cols.containsKey(col)) {
                cols.put(col, new ArrayList<>());
            }
            cols.get(col).add(row);
        }

        int MAX = 1000000007;
        ArrayList<Integer> rows3 = new ArrayList<>(rows2);
        ArrayList<Integer> cols3 = new ArrayList<>(cols2);
        int[] rows4 = new int[rows3.size()];
        int[] cols4 = new int[cols3.size()];
        for (int i = 0; i < rows3.size(); i++) {
            int row = rows3.get(i);
            for (int j = 0; j < rows.get(row).size(); j++) {
                for (int k = j + 1; k < rows.get(row).size(); k++) {
                    rows4[i] += Math.abs(rows.get(row).get(j) - rows.get(row).get(k));
                    rows4[i] = rows4[i] % MAX;
                }
            }
        }
        for (int i = 0; i < cols3.size(); i++) {
            int col = cols3.get(i);
            for (int j = 0; j < cols.get(col).size(); j++) {
                for (int k = j + 1; k < cols.get(col).size(); k++) {
                    cols4[i] += Math.abs(cols.get(col).get(j) - cols.get(col).get(k));
                    cols4[i] = cols4[i] % MAX;
                }
            }
        }
        int ans = 0;
        for (int row : rows4) {
            if (row == 0) {
                continue;
            }
            for (int col : cols4) {
                if (col == 0) {
                    continue;
                }
                ans += ((long) row * (long) col) % MAX;
                ans = ans % MAX;
            }
        }

        pw.println(ans);
        pw.close();
    }
}