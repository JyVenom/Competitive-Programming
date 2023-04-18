import java.io.*;
import java.util.ArrayList;

public class CowNavigation3 {
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

        ArrayList<state> states = new ArrayList<>();
        for (int ax = 0; ax < n; ax++) {
            for (int ay = 0; ay < n; ay++) {
                for (int bx = 0; bx < n; bx++) {
                    for (int by = 0; by < n; by++) {
                        for (int dir = 0; dir < 4; dir++) {
                            states.add(new state(ax, ay, bx, by, dir));
                        }
                    }
                }
            }
        }

        pw.close();
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
