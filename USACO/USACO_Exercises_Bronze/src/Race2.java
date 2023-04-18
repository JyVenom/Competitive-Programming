import java.io.*;
import java.util.StringTokenizer;

public class Race2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("race.in"));
        PrintWriter out = new PrintWriter(new File("race.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            out.println(solve(k, br));
        }
        out.close();
    }
    private static int solve(int dist, BufferedReader br) throws IOException {
        int minspeed = Integer.parseInt(br.readLine());
        int lhstravel = 0;
        int rhstravel = 0;
        int timeused = 0;
        for (int currspeed = 1;; currspeed++) {
            lhstravel += currspeed;
            timeused++;
            if (lhstravel + rhstravel >= dist) {
                return timeused;
            }
            if (currspeed >= minspeed) {
                rhstravel += currspeed;
                timeused++;
                if (lhstravel + rhstravel >= dist) {
                    return timeused;
                }
            }
        }
    }
}
