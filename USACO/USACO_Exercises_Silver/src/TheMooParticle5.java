import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class TheMooParticle5 {
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

        Arrays.sort(particles, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(particles, Comparator.comparingInt(arr -> arr[0]));
        int ans = 1;
        for (int i = 1; i < n; i++) {
            if (particles[i][0] > particles[i - 1][0]) {
                if (particles[i][1] < particles[i - 1][1]) {
                    ans++;
                }
            }
        }

        pw.println(ans);
        pw.close();
    }
}
