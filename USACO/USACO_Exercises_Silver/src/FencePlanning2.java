import java.io.*;
import java.util.ArrayList;

public class FencePlanning2 {
    private static int[] group;
    private static int[][] pos;
    private static ArrayList<ArrayList<Integer>> pairs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter out = new PrintWriter(new File("fenceplan.out"));

        String line = br.readLine();
        int n = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int m = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        pairs = new ArrayList<>();
        pos = new int[n][2];
        group = new int[n];

        for (int i = 0; i < n; i++){
            line = br.readLine();
            pos[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            pos[i][1] = Integer.parseInt(line.substring(line.indexOf(' ') + 1));

            pairs.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++){
            line = br.readLine();
            int i1 = Integer.parseInt(line.substring(0, line.indexOf(' '))) - 1;
            int i2 = Integer.parseInt(line.substring(line.indexOf(' ') + 1)) - 1;
            pairs.get(i1).add(i2);
            pairs.get(i2).add(i1);
        }

        int K = 1; //current group
        int best = 999999999;
        for (int i = 0; i < n; i++) {
            if (group[i] == 0) {
                int[] bb = {999999999, 0, 999999999, 0}; //minX, maxX, minY, maxY
                visit(i, K, bb);
                K++;
                best = Math.min(best, 2 * (bb[1] - bb[0] + bb[3] - bb[2]));
            }
        }

        out.println(best);
        out.close();
    }

    // Recursively visit cow i in group k with bounding box bb
    public static void visit(int i, int k, int[] bb) {
        group[i] = k;
        bb[0] = Math.min(bb[0], pos[i][0]);
        bb[1] = Math.max(bb[1], pos[i][0]);
        bb[2] = Math.min(bb[2], pos[i][1]);
        bb[3] = Math.max(bb[3], pos[i][1]);
        for (int j : pairs.get(i)){
            if (group[j] == 0){
                visit(j, k, bb);
            }
        }
    }
}
