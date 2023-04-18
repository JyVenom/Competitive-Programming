import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1438C {
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

            ArrayList<ArrayList<ArrayList<Integer>>> connections = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    tmp.add(new ArrayList<>());
                }
                connections.add(tmp);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    if (c[i][j] == c[i][j - 1]) {
                        connections.get(i).get(j).add(6);
                        connections.get(i).get(j - 1).add(2);
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (c[j][i] == c[j - 1][i]) {
                        connections.get(j).get(i).add(0);
                        connections.get(j - 1).get(i).add(4);
                    }
                }
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    if (c[i][j] == c[i - 1][j - 1]) {
                        connections.get(i).get(j).add(7);
                        connections.get(i - 1).get(j - 1).add(3);
                    }
                }
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    if (c[i][j - 1] == c[i - 1][j]) {
                        connections.get(i).get(j - 1).add(1);
                        connections.get(i - 1).get(j).add(5);
                    }
                }
            }

            System.out.println();
        }

        pw.close();
    }

    private static class cell {
        int count = 0;
        ArrayList<Integer> connections = new ArrayList<>();
    }
}
