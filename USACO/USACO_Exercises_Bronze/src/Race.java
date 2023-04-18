import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Race {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("race.in"));
        PrintWriter out = new PrintWriter(new File("race.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] values = new int[n];
        for (int i = 0; i < n; i++){
            values[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            int speed = 0;
            int dist = 0;
            int time = 0;
            while (dist < k) {
                time++;
                if (sumUp(values[i], speed + 1) <= k - dist) {
                    speed++;
                }
                else if (sumUp(values[i], speed) > k - dist) {
                    speed--;
                }
                dist += speed;
            }
            out.println(time);
        }

        out.close();
    }

    private static int sumUp (int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++){
            sum += i;
        }
        return sum;
    }
}
