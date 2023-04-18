import java.io.*;
import java.util.ArrayList;

public class CowNavigation4 {
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

        boolean[][][][][] visited = new boolean[n][n][n][n][4];
        BFS(visited, 0, 0, 0, 0, 0);

        pw.close();
    }

    private static void BFS(boolean[][][][][] visited, int ax, int ay, int bx, int by, int dir) {
        visited[ax][ay][bx][by][dir] = true;

        //turn left
        BFS(visited, ax, ay, bx, by, (dir + 3) % 4);
        //turn right
        BFS(visited, ax, ay, bx, by, (dir + 1) % 4);
        //forward


        visited[ax][ay][bx][by][dir] = false;
    }

    private static class state {
//        int ax, ay, ad, bx, by, bd;
        int ax, ay, bx, by, dir;

//        private state(int ax, int ay, int ad, int bx, int by, int bd) {
//            this.ax = ax;
//            this.ay = ay;
//            this.ad = ad;
//            this.bx = bx;
//            this.by = by;
//            this.bd = bd;
//        }

        private state(int ax, int ay, int bx, int by, int dir) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.dir = dir;
        }
    }
}
