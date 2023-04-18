import java.io.*;
import java.util.StringTokenizer;

public class Moocast3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));

        int n = Integer.parseInt(br.readLine());

        int[][] cows = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
            cows[i][2] = Integer.parseInt(st.nextToken());
        }
        br.close();

        boolean[][] canTransmit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int x = cows[i][0];
            int y = cows[i][1];
            int range = cows[i][2];
            for (int j = 0; j < n; j++) {
                int X = cows[j][0];
                int Y = cows[j][1];
                int dist = ((x - X) * (x - X) + (y - Y) * (y - Y));
                if (dist <= range * range) {
                    canTransmit[i][j] = true;
                }
            }
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            max = Math.max(max, DFS(i, visited, canTransmit));
        }

        pw.println(max);
        pw.close();
    }

    private static int DFS(int cur, boolean[] visited, boolean[][] canTransmit) {
        visited[cur] = true;
        int ans = 1;
        for(int i = 0; i < canTransmit[cur].length; i++) {
            if(canTransmit[cur][i]) {
                if (!visited[i]) {
                    ans += DFS(i, visited, canTransmit);
                }
            }
        }
        return ans;
    }
}
