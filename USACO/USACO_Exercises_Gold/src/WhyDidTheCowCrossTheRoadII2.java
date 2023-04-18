import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WhyDidTheCowCrossTheRoadII2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("nocross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        List<List<Integer>> graph = createEmptyGraph(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int dif = Math.abs(a[i] - b[j]);
                if (dif <= 4) {
                    addEdge(graph, i, j + n);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int next : graph.get(i)) {

            }
        }


        pw.close();
    }

    private static int remove(int[] next, int matches, int n) {
        int temp = matches;
        for (int i = 0; i < n; i++) {

        }
        return matches;
    }

    private static List<List<Integer>> createEmptyGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        return graph;
    }

    private static void addEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
    }
}