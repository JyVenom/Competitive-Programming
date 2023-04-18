import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhyDidTheCowCrossTheRoadII {
    static final int FREE = -1;
    static int visitToken = 1;

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
        int[] next = new int[2 * n];
        int matches = mbm(graph, next, n, n);



        pw.close();
    }

    private static int remove(int[] next, int matches, int n) {
        int temp = matches;
        for (int i = 0; i < n; i++) {

        }
        return matches;
    }


    /**
     * This function counts what the Maximum Cardinality Bipartite Matching (MCBM) is a bipartite
     * graph where the nodes [0,n) are in the left set and [n, n+m) in right set
     *
     * @param graph - An adjacency list representing an undirected bipartite graph.
     */
    public static int mbm(List<List<Integer>> graph, int[] next, int n, int m) {

        int N = n + m, matches = 0;

        int[] visited = new int[n];
        Arrays.fill(next, FREE);

        for (int i = 0; i < n; i++) {
            matches += augment(graph, visited, next, i);
            visitToken++;
        }

        return matches;
    }

    private static int augment(List<List<Integer>> graph, int[] visited, int[] next, int at) {

        // Node already visited in this augmenting path
        if (visited[at] == visitToken) return 0;
        visited[at] = visitToken;

        for (int node : graph.get(at)) {
            // If the value of oppositeNode is FREE then it has not yet been
            // matched, otherwise the value refers to the index of the node
            // used to reach the oppositeNode.
            int oppositeNode = next[node];

            if (oppositeNode == FREE) {
                // Record which node we came from and return
                // 1 to indicate a path was found
                next[node] = at;
                return 1;
            }

            // We were able to find an alternating path
            if (augment(graph, visited, next, oppositeNode) != 0) {

                // Record which node we came from and return
                // 1 to indicate a path was found
                next[node] = at;
                return 1;
            }
        }

        // No path found :/
        return 0;
    }

    private static List<List<Integer>> createEmptyGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        return graph;
    }

    private static void addEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
    }

//        public static void main(String[] args) {
//            int n = 8;
//            List<List<Integer>> graph = createEmptyGraph(n);
//
//            // Left set includes {0,1,2,3} and right set {4,5,6,7}
//            addEdge(graph, 0, 4);
//            addEdge(graph, 1, 5);
//            addEdge(graph, 2, 7);
//            addEdge(graph, 3, 6);
//            addEdge(graph, 4, 1);
//            addEdge(graph, 5, 1);
//            addEdge(graph, 6, 1);
//
//            // Prints '4' because that's the maximum matching.
//            System.out.println(mcbm(graph, 4, 4));
//        }
//    }
}
