import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class GrassPlanting2 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis(); //jerry
        BufferedReader br = new BufferedReader(new FileReader("planting.in"));
        PrintWriter out = new PrintWriter(new File("planting.out"));

        int fields = Integer.parseInt(br.readLine());
        int[][] data = new int[fields - 1][2];
        ArrayList<HashSet<Integer>> connectedGrasses = new ArrayList<>(fields + 1);
        int[] grass = new int[fields + 1];
        for (int i = 0; i <= fields; i++) {
            connectedGrasses.add(new HashSet<>());
        }

        String line;
        for (int i = 0; i < fields - 1; i++){
            line = br.readLine();
            int i1 = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            int i2 = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
            if (i1 < i2){
                data[i][0] = i1;
                data[i][1] = i2;
            }
            else {
                data[i][0] = i2;
                data[i][1] = i1;
            }
        }
        Arrays.sort(data, Comparator.comparingInt(a -> a[0]));

        int target;
        int other;
        for (int i = 0; i < fields - 1; i++){
            target = data[i][0];
            if (grass[target] == 0){
                other = data[i][1];
                addGrass(fields,connectedGrasses,grass,target,other);
            }
            target = data[i][1];
            if (grass[target] == 0){
                other = data[i][0];
                addGrass(fields,connectedGrasses,grass,target,other);
            }
        }

        HashSet<Integer> grasses = new HashSet<>();
        for (int i = 1; i < fields + 1; i++){
            grasses.add(grass[i]);
        }
        out.println(grasses.size());
        out.close();
        System.out.println(System.currentTimeMillis() - startTime); //jerry
    }

    private static void addGrass(int fields, ArrayList<HashSet<Integer>> connectedGrasses, int[] grass, int target, int other) {
        for (int type = 1; type <= fields; type++){
            if (connectedGrasses.get(other).contains(type)){
                continue;
            }
            if (grass[other] != type) {
                connectedGrasses.get(other).add(type);
                connectedGrasses.get(target).add(grass[other]);
                grass[target] = type;
                return;
            }
        }
    }
}
