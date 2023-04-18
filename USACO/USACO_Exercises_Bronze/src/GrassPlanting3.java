import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GrassPlanting3 {
    public static void main(String[] args)  throws IOException {
        Scanner sc = new Scanner(new File("revegetate.in"));
        PrintWriter out = new PrintWriter(new File("revegetate.out"));
        String  temp = sc.nextLine();
        int pastures = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
        int numCows = Integer.parseInt(temp.substring(temp.indexOf(' ') + 1));
        int[][] cows = new int[numCows][2];
        ArrayList<Integer> pastureSeed = new ArrayList<>(pastures);
        int currentSeed;
        int[] seeds = {1,2,3,4};
        for (int i = 0; i < pastures; i++){
            pastureSeed.add(0);
        }
        pastureSeed.set(0, 1);
        for (int i = 0; i < numCows; i++){
            String line = sc.nextLine();
            cows[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            cows[i][1] = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
            Arrays.sort(cows[i]);
        }
//        Arrays.sort(cows, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(cows, new java.util.Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        for (int i = 1; i <= pastures; i++){
            currentSeed = 0;
            ArrayList<Integer> simPastures = new ArrayList<>();
            for (int j = 0; j < cows.length; j++){
                if (cows[j][0] == i){
                    simPastures.add(cows[j][1]);
                }
                else if (cows[j][1] == i){
                    simPastures.add(cows[j][0]);
                }
            }
            Collections.sort(simPastures);
            if (pastureSeed.get(i - 1) == 0){
                pastureSeed.set(i - 1, 1);
            }
            for (int j = 0; j < simPastures.size(); j++){
                if (pastureSeed.get(simPastures.get(j) - 1) == 0){
                    if (seeds[currentSeed] != pastureSeed.get(i - 1)){
                        pastureSeed.set(simPastures.get(j) - 1,seeds[currentSeed]);
                    }
                    else {
                        currentSeed++;
                        currentSeed = currentSeed%4;
                        pastureSeed.set(simPastures.get(j) - 1,seeds[currentSeed]);
                    }
                }
                else {
                    if (pastureSeed.get(i - 1).equals(pastureSeed.get(simPastures.get(j) - 1))){
                        currentSeed = pastureSeed.get(i - 1);
                        currentSeed = currentSeed%4;
                        if (i < simPastures.get(j)) {
                            pastureSeed.set(simPastures.get(j) - 1, seeds[currentSeed]);
                        }
                        else {
                            pastureSeed.set(i - 1, seeds[currentSeed]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < pastureSeed.size(); i++){
            out.print(pastureSeed.get(i));
        }
        out.close();
    }
}
