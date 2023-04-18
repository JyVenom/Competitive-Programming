import java.io.*;
import java.util.*;

public class TheMooParticle3 {
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
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (particles[i][1] <= particles[j][1]) {
                    edges.get(i).add(j);
                    edges.get(j).add(i);
                }
            }
        }
        for (ArrayList<Integer> particle : edges) {
            Collections.sort(particle);
        }
        int[] component = new int[n];
        int comp = 1;
        for (int i = 0; i < n; i++) {
            if (component[i] == 0) {
                dfs(edges, component, i, comp);
                comp++;
            }
        }

        pw.println(comp - 1);
        pw.close();
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, int[] component, int at, int comp) {
        component[at] = comp;
        ArrayList<Integer> cur = edges.get(at);
        for (int next : cur) {
            if (component[next] == 0) {
                dfs(edges, component, next, comp);
            }
        }
    }
}
