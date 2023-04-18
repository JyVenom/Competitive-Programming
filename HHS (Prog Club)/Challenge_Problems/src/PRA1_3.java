import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class PRA1_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int[] bessie = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] elsie = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxA = 0;
        int maxB = 0;
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < n; i++) {
            if (bessie[i] > maxA) {
                maxA = bessie[i];
                countA = 1;
            } else if (bessie[i] == maxA) {
                countA++;
            }
            if (elsie[i] > maxB) {
                maxB = elsie[i];
                countB = 1;
            } else if (elsie[i] == maxB) {
                countB++;
            }
        }

        if (maxA > maxB) {
            pw.println("You win");
        } else if (maxA < maxB) {
            pw.println("Friend wins");
        } else {
            if (countA > countB) {
                pw.println("You win");
            } else if (countA < countB) {
                pw.println("Friend wins");
            } else {
                pw.println("Draw");
            }

        }
        pw.close();
    }
}
