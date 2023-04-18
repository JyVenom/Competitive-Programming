import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class PRA1_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int[] bessie = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] elsie = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxA = 0;
        int maxB = 0;
        for (int i = 0; i < n; i++) {
            if (bessie[i] > maxA) {
                maxA = bessie[i];
            }
            if (elsie[i] > maxB) {
                maxB = elsie[i];
            }
        }

        if (maxA > maxB) {
            pw.println("You win");
        }
        else {
            pw.println("Friend wins");
        }
        pw.close();
    }
}
