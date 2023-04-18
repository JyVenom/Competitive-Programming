import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FencePlanning {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter out = new PrintWriter(new File("fenceplan.out"));

        String line = br.readLine();
        int n = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int m = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        int[][] pos = new int[n][2];
        int[][] pairs = new int[m][2];
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();

        for (int i = 0; i < n; i++){
            line = br.readLine();
            pos[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            pos[i][1] = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        }
        for (int i = 0; i < m; i++){
            line = br.readLine();
            pairs[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' '))) - 1;
            pairs[i][1] = Integer.parseInt(line.substring(line.indexOf(' ') + 1)) - 1;
        }

        boolean added;
        for (int i = 0; i < m; i++){
            added = false;
            for (ArrayList<Integer> group : groups) {
                if (group.contains(pairs[i][0])) {
                    added = true;
                    if (!group.contains(pairs[i][1])) {
                        group.add(pairs[i][1]);
                    }
                    break;
                }
                if (group.contains(pairs[i][1])) {
                    added = true;
                    if (!group.contains(pairs[i][0])) {
                        group.add(pairs[i][0]);
                    }
                    break;
                }
            }
            if (!added){
                groups.add(new ArrayList<>());
                groups.get(groups.size() - 1).add(pairs[i][0]);
                groups.get(groups.size() - 1).add(pairs[i][1]);
            }
        }

        int[] perm = new int[groups.size()];
        int minX, maxX, minY, maxY;
        for (int i = 0; i < groups.size(); i++){
            minX = 100000001;
            maxX = 0;
            minY = 100000001;
            maxY = 0;
            for (int j = 0; j < groups.get(i).size(); j++){
                int cur = groups.get(i).get(j);
                minX = Math.min(minX, pos[cur][0]);
                maxX = Math.max(maxX, pos[cur][0]);
                minY = Math.min(minY, pos[cur][1]);
                maxY = Math.max(maxY, pos[cur][1]);
            }
            perm[i] = (maxX - minX) * 2 + (maxY - minY) * 2;
        }

        Arrays.sort(perm);
        out.println(perm[0]);
        out.close();
    }
}
