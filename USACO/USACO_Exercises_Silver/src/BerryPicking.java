import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

public class BerryPicking {
    private static int n;
    private static int k;
    private static Integer[] b;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
        PrintWriter out = new PrintWriter(new File("berries.out"));

        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        k = Integer.parseInt(line[1]);
        b = new Integer[n];
        int val = 0;
        line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(line[i]);
            val += b[i];
        }
        val /= n;
        Arrays.sort(b, Collections.reverseOrder());

        int min = 0;
        int max = b[0];
        int prev = find(val);
        if (find(val - 1) > prev) {
            val--;
            while (max - min > 1) {
                int now = find(val);
                if (now > prev) {
                    max = val;
                } else {
                    min = val;
                }
                val = (min + max) / 2;
                prev = now;
            }
        }
        else if (find(val + 1) > prev){
            val++;
            while (max - min > 1) {
                int now = find(val);
                if (now > prev) {
                    min = val;
                } else {
                    max = val;
                }
                val = (min + max) / 2;
                prev = now;
            }
        }
        out.println(find(val));
        out.close();
    }

    private static int find (int value){
        Integer[] bTemp = b.clone();
        int prev = 0;
        Integer[] baskets = new Integer[k];
        for (int i = 0; i < baskets.length; i++) {
            for (int j = prev; j < n; j++) {
                if (bTemp[j] == 0){
                    continue;
                }
                int temp = bTemp[j] / value;
                if (temp > 1){
                    baskets[i] = value;
                    bTemp[j] -= value;
                }
                else {
                    baskets[i] = bTemp[j];
                    bTemp[j] = 0;
                    prev++;
                }
                break;
            }
        }
        Arrays.sort(baskets, Collections.reverseOrder());

        int sum = 0;
        for (int i = k / 2; i < k; i++){
            sum += baskets[i];
        }
        return sum;
    }
}
