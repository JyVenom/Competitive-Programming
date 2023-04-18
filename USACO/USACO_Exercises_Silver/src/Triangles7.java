import java.io.*;
import java.util.*;

public class Triangles7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));

        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<Integer>> rows = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> cols = new HashMap<>();
        HashSet<Integer> tempRows = new HashSet<>();
        HashSet<Integer> tempCols = new HashSet<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());

            tempRows.add(row);
            tempCols.add(col);

            if (!rows.containsKey(row)) {
                rows.put(row, new ArrayList<>());
            }
            rows.get(row).add(col);
            if (!cols.containsKey(col)) {
                cols.put(col, new ArrayList<>());
            }
            cols.get(col).add(row);
        }

        ArrayList<Integer> rows2 = new ArrayList<>(tempRows);
        ArrayList<Integer> cols2 = new ArrayList<>(tempCols);
        Collections.sort(rows2);
        Collections.sort(cols2);
        for (Integer at : rows2) {
            Collections.sort(rows.get(at));
        }
        for (Integer at : cols2) {
            Collections.sort(cols.get(at));
        }
        HashMap<Integer, int[]> rows3 = new HashMap<>();
        HashMap<Integer, int[]> cols3 = new HashMap<>();
        for (int row : rows2) {
            rows3.put(row, new int[rows.get(row).size()]);
        }
        for (int col : cols2) {
            cols3.put(col, new int[cols.get(col).size()]);
        }
        for (int row : rows2) {
            int temp = 0;
            for (int i = 1; i < rows.get(row).size(); i++) {
                temp += rows.get(row).get(i) - rows.get(row).get(0);
            }
            rows3.get(row)[0] = temp;
            for (int i = 1; i < rows.get(row).size(); i++) {
                int I = i - 1;
                rows3.get(row)[i] = rows3.get(row)[I] + (((2 * i) - rows.get(row).size()) * (rows.get(row).get(i) - rows.get(row).get(I)));
            }
        }
        for (int col : cols2) {
            int temp = 0;
            for (int i = 1; i < cols.get(col).size(); i++) {
                temp += cols.get(col).get(i) - cols.get(col).get(0);
            }
            cols3.get(col)[0] = temp;
            for (int i = 1; i < cols.get(col).size(); i++) {
                int I = i - 1;
                cols3.get(col)[i] = cols3.get(col)[I] + (((2 * i) - cols.get(col).size()) * (cols.get(col).get(i) - cols.get(col).get(I)));
            }
        }
        for (int row : rows2) {

        }










        int MAX = 1000000007;
        int[] rows4 = new int[rows2.size()];
        int[] cols4 = new int[cols2.size()];
        for (int i = 0; i < rows2.size(); i++) {
            int row = rows2.get(i);
            for (int j = 0; j < rows.get(row).size(); j++) {
                for (int k = j + 1; k < rows.get(row).size(); k++) {
                    rows4[i] += Math.abs(rows.get(row).get(j) - rows.get(row).get(k));
                    rows4[i] = rows4[i] % MAX;
                }
            }
        }
        for (int i = 0; i < cols2.size(); i++) {
            int col = cols2.get(i);
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