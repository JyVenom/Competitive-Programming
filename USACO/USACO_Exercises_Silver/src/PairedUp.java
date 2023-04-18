import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class PairedUp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
        PrintWriter out = new PrintWriter(new File("pairup.out"));

        int n = Integer.parseInt(br.readLine());
        int[][] numTimes = new int[n][2];
        for (int i = 0; i < n; i++){
            String[] temp = br.readLine().split(" ");
            numTimes[i][0] = Integer.parseInt(temp[0]);
            numTimes[i][1] = Integer.parseInt(temp[1]);
        }
        Arrays.sort(numTimes, Comparator.comparingInt(arr -> arr[1]));

        int max = 0;
        int i = 0;
        int j = n - 1;
        while (i <= j) {
            int x = Math.min(numTimes[i][0], numTimes[j][0]);
            if (i == j) {
                x /= 2;
            }
            max = Math.max(max, numTimes[i][1] + numTimes[j][1]);
            numTimes[i][0] -= x;
            numTimes[j][0] -= x;
            if (numTimes[i][0] == 0) {
                i++;
            }
            if (numTimes[j][0] == 0) {
                j--;
            }
        }

        out.println(max);
        out.close();
    }
}
