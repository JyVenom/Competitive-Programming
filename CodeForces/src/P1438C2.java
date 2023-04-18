import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P1438C2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] c = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    c[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            cell[][] cells = new cell[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    cells[i][j] = new cell(i, j);
                }
            }
            HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> map = new HashMap<>(n);
            ArrayList<ArrayList<Integer>> connections = new ArrayList<>(n*m);
            for (int i = 0; i < n; i++) {
                map.put(i, new HashMap<>());
                for (int j = 0; j < m; j++) {
                    ArrayList<Integer> tmp2 = new ArrayList<>();
                    connections.add(tmp2);
                    map.get(i).put(j, tmp2);
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    if (c[i][j] == c[i][j - 1]) {
                        connections.get(code(i, j, m)).add(3);
                        connections.get(code(i, j-1, m)).add(1);
                        cells[i][j].connections.add(3);
                        cells[i][j-1].connections.add(1);
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (c[j][i] == c[j - 1][i]) {
                        connections.get(code(j, i, m)).add(0);
                        connections.get(code(j-1, i, m)).add(2);
                        cells[j][i].connections.add(0);
                        cells[j-1][i].connections.add(2);
                    }
                }
            }


            System.out.println();
        }

        pw.close();
    }

    private static int code(int row, int col, int m) {
        return (row*m)+ col;
    }

    private static class cell {
        int row, col;
        ArrayList<Integer> connections = new ArrayList<>();

        public cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
