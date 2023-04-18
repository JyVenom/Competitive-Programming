import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class TheMooParticle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));

        int n = Integer.parseInt(br.readLine());
        ArrayList<int[]> particles = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int[] particle = new int[2];
            particle[0] = Integer.parseInt(st.nextToken());
            particle[1] = Integer.parseInt(st.nextToken());
            particles.add(particle);
        }

        particles.sort(Comparator.comparingInt(a -> a[1]));
        particles.sort(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < particles.size(); i++) {
            int[] cur = particles.get(i);
            for (int j = i + 1; j < particles.size(); j++) {
                int[] other = particles.get(i);
                if (cur[0] <= other[0] && cur[1] <= other[1]) {
                    particles.remove(j);
                    j--;
                }
            }
        }

        pw.println(particles.size());
        pw.close();
    }
}
