import java.io.*;
import java.util.Arrays;

public class SleepyCowHerding {
    private static int cows;
    private static int[] data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
        PrintWriter out = new PrintWriter(new File("herding.out"));

        cows = Integer.parseInt(br.readLine());
        data = new int[cows];

        for (int i = 0; i < cows; i++){
            data[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(data);

        out.println(solveMin());
        out.println(Math.max(data[cows - 2] - data[0], data[cows - 1] - data[1]) - (cows - 2)); //which is larger: dist between first and second last cow, or dist between second cows and last cow minus (cows - 2)
        out.close();
    }
    
    private static int solveMin (){
        if ((data[cows - 2] - data[0] == cows - 2) && (data[cows - 1] - data[cows - 2] > 2)) { //if there is a set of N−1 consecutive cows, then a gap of size more than 2, then another cow -- this case requires 2 moves instead of just 1.
            return 2;
        }
        if ((data[cows - 1] - data[1] == cows - 2) && (data[1] - data[0] > 2)) { //same as above
            return 2;
        }

        //main
        int j = 0;
        int best = 0;
        for (int i = 0; i < cows; i++) {
            while (j < cows - 1 && data[j + 1] - data[i] <= cows - 1) {
                j++;
            }
            best = Math.max(best, j - i + 1);
        }
        return cows - best;
    }
}
