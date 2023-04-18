import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class BessiesBirthdayBuffet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("buffet.in"));
        PrintWriter out = new PrintWriter(new File("buffet.out"));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int cost = Integer.parseInt(line[1]);
        int[] max = new int[n];
        int[] q = new int[n];
        ArrayList<ArrayList<Integer>> quality = new ArrayList<>();
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>();

        for (int i = 0; i < n; i++){
            quality.add(new ArrayList<>());
            connections.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++){
            line = br.readLine().split(" ");
            quality.get(i).add(Integer.parseInt(line[0]));
            quality.get(i).add(i);
            q[i] = Integer.parseInt(line[0]);
            int num = Integer.parseInt(line[1]);
            for (int j = 0; j < num; j++){
                connections.get(i).add(Integer.parseInt(line[2 + j]) - 1);
            }
        }
        quality.sort(Comparator.comparing(a -> a.get(0)));

        int answer = 0;
        int[] dist = new int[n];
        for (int i = 0; i < n; i++){
            //Find distances from patch i to all prev patches
            for (int j = 0; j < n; j++){
                dist[j] = 1000;
            }
            boolean[] visited = new boolean[n];
            int patch1 = quality.get(i).get(1);
            DFS(connections, dist, -1, patch1, visited);
            max[patch1] = q[patch1];
            for (int j = 0; j < i; j++) {
                int patch2 = quality.get(j).get(1);
                max[patch1] = Math.max(max[patch1], max[patch2] + q[patch1] - (dist[patch2] * cost));
            }
            answer = Math.max(answer, max[patch1]);
        }

        out.println(answer);
        out.close();
    }

    private static void DFS (ArrayList<ArrayList<Integer>> connections, int[] dist, int layer, int at, boolean[] visited){
        layer++;
        visited[at] = true;
        dist[at] = Math.min(dist[at], layer);
        for (int to : connections.get(at)) {
            if (!visited[to]){
                DFS(connections, dist, layer, to, visited);
            }
        }
    }
}