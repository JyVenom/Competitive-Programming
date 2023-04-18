import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class PRA1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int[] bessie = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] elsie = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(bessie);
        Arrays.sort(elsie);
        boolean printed = false;
        for (int i = n - 1; i >= 0; i--) {
            if (bessie[i] > elsie[i]) {
                pw.println("You win");
                printed = true;
                break;
            } else if (bessie[i] < elsie[i]) {
                pw.println("Friend wins");
                printed = true;
                break;
            }
        }
        if (!printed) {
            pw.println("Draw");
        }

        pw.close();
    }
}
