import java.io.*;
import java.util.StringTokenizer;

public class TheMooParticle2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));

        int n = Integer.parseInt(br.readLine());
        int[][] particles = new int[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            particles[i][0] = Integer.parseInt(st.nextToken());
            particles[i][1] = Integer.parseInt(st.nextToken());
        }
/*
mark as visited instead of deleting
do a recursive search, either first foes away or second goes away
mark as uninteractable to avoid re-comparison
 */

    }
}
