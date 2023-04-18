import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class GrassPlanting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("planting.in"));
        PrintWriter out = new PrintWriter(new File("planting.out"));

        int fields = Integer.parseInt(br.readLine());
        int[] grass = new int[fields + 1];
        int[][] data = new int[fields - 1][2];
        grass[1] = 1;

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

        for (int i = 0; i < fields - 1; i++){
            if (grass[data[i][0]] == 0){
                if (connected(data, data[i][0], data[i][1]) != -1){
                    int o1 = connected(data, data[i][0], data[i][1]);
                    if (connected(data, o1, data[i][0]) != -1){
                        int o2 = connected(data, o1, data[i][0]);
                        if (grass[o1] == 1){
                            if (grass[o2] == 2){
                                grass[data[i][0]] = 3;
                            }
                            else {
                                grass[data[i][0]] = 2;
                            }
                        }
                        else if (grass[o2] == 1){
                            if (grass[o1] == 2){
                                grass[data[i][0]] = 3;
                            }
                            else {
                                grass[data[i][0]] = 2;
                            }
                        }
                        else {
                            grass[data[i][0]] = 1;
                        }
                    }
                    else {
                        if (grass[o1] == 1){
                            grass[data[i][0]] = 2;
                        }
                        else {
                            grass[data[i][0]] = 1;
                        }
                    }
                }
                else {
                    if (grass[data[i][1]] == 1){
                        grass[data[i][0]] = 2;
                    }
                    else {
                        grass[data[i][0]] = 1;
                    }
                }
            }
            if (grass[data[i][1]] == 0){
                if (connected(data, data[i][1], data[i][0]) != -1){
                    int o1 = connected(data, data[i][1], data[i][1]);
                    if (connected(data, o1, data[i][1]) != -1){
                        int o2 = connected(data, o1, data[i][1]);
                        if (grass[o1] == 1){
                            if (grass[o2] == 2){
                                grass[data[i][1]] = 3;
                            }
                            else {
                                grass[data[i][1]] = 2;
                            }
                        }
                        else if (grass[o2] == 1){
                            if (grass[o1] == 2){
                                grass[data[i][1]] = 3;
                            }
                            else {
                                grass[data[i][1]] = 2;
                            }
                        }
                        else {
                            grass[data[i][1]] = 1;
                        }
                    }
                    else {
                        if (grass[o1] == 1){
                            grass[data[i][1]] = 2;
                        }
                        else {
                            grass[data[i][1]] = 1;
                        }
                    }
                }
                else {
                    if (grass[data[i][0]] == 1){
                        grass[data[i][1]] = 2;
                    }
                    else {
                        grass[data[i][1]] = 1;
                    }
                }
            }
        }

        HashSet<Integer> grasses = new HashSet<>();
        for (int i = 1; i < fields + 1; i++){
            grasses.add(grass[i]);
        }
        out.println(grasses.size());
        out.close();
    }

    private static int connected (int[][] in, int target, int other){
        for (int[] bridge : in) {
            if (bridge[0] == target) {
                if (bridge[1] != other) {
                    return bridge[1];
                }
            }
            if (bridge[1] == target) {
                if (bridge[0] != other) {
                    return bridge[0];
                }
            }
        }
        return -1;
    }
}
