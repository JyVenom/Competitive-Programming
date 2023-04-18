import java.io.*;
import java.util.*;

public class FencedIn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fencedin.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken()); //x
        int B = Integer.parseInt(st.nextToken()); //y
        int n = Integer.parseInt(st.nextToken()); //vertical/cols
        int m = Integer.parseInt(st.nextToken()); //horizontal/rows
//        int[] ver = new int[n];
//        int[] hor = new int[m];
        ArrayList<Integer> ver = new ArrayList<>();
        ArrayList<Integer> hor = new ArrayList<>();
        for (int i = 0; i < n; i++) {
//            ver[i] = Integer.parseInt(br.readLine());
            ver.add(Integer.parseInt(br.readLine()));
        }
        for (int i = 0; i < m; i++) {
//            hor[i] = Integer.parseInt(br.readLine());
            hor.add(Integer.parseInt(br.readLine()));
        }
        br.close();

        int N = n + 1;
        int M = m + 1;
        Collections.sort(ver);
        Collections.sort(hor);
        ver.add(0, 0);
        ver.add(A);
        hor.add(0, 0);
        hor.add(B);
        int[] rowH = new int[M];
        int[] colW = new int[N];
        for (int i = 0; i < N; i++) {
            colW[i] = ver.get(i + 1) - ver.get(i);
        }
        for (int i = 0; i < M; i++) {
            rowH[i] = hor.get(i + 1) - hor.get(i);
        }
        int size = N * M;
        int[][] dist = new int[size][size];
        Graph graph = new Graph(size);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = i * N + j;
                int right = i * N + j + 1;
                int up = (i + 1) * N + j;
                addEdge(graph, cur, right, rowH[i]);
                addEdge(graph, cur, up, colW[j]);
                dist[cur][right] = rowH[i];
                dist[right][cur] = rowH[i];
                dist[cur][up] = colW[j];
                dist[up][cur] = colW[j];
            }
        }
        for (int i = 0; i < n; i++) {
            int cur = m * N + i;
            int right = m * N + i + 1;
            addEdge(graph, cur, right, rowH[m]);
            dist[cur][right] = rowH[m];
            dist[right][cur] = rowH[m];
        }
        for (int i = 0; i < m; i++) {
                int cur = i * N + n;
                int up = (i + 1) * N + n;
                addEdge(graph, cur, up, colW[n]);
                dist[cur][up] = colW[n];
                dist[up][cur] = colW[n];
        }

        pw.println(mst(graph, dist));
        pw.close();
    }

    private static void addEdge(Graph graph, int src, int dest, int weight) {
        node1 toDest = new node1(dest, weight);
        node1 toSrc = new node1(src, weight);
        graph.adj.get(src).add(toDest);
        graph.adj.get(dest).add(toSrc);
    }

    private static long mst(Graph graph, int[][] dist) {
        boolean[] inSet = new boolean[graph.V];
        node[] nodes = new node[graph.V];
        int[] parent = new int[graph.V];

        for (int i = 0; i < graph.V; i++)
            nodes[i] = new node();

        for (int i = 0; i < graph.V; i++) {
            inSet[i] = false;
            nodes[i].key = Integer.MAX_VALUE;
            nodes[i].vertex = i;
            parent[i] = -1;
        }

        inSet[0] = true;
        nodes[0].key = 0;
        TreeSet<node> queue = new TreeSet<>(Comparator.comparingInt(o -> o.key));
        queue.addAll(Arrays.asList(nodes).subList(0, graph.V));

        while (!queue.isEmpty()) {
            node node = queue.pollFirst();
            assert node != null;
            inSet[node.vertex] = true;

            for (node1 next : graph.adj.get(node.vertex)) {
                if (!inSet[next.dest]) {
                    if (nodes[next.dest].key > next.weight) {
                        queue.remove(nodes[next.dest]);
                        nodes[next.dest].key = next.weight;
                        queue.add(nodes[next.dest]);
                        parent[next.dest] = node.vertex;
                    }
                }
            }
        }

        long sum = 0;
        for (int i = 1; i < graph.V; i++) {
            sum += dist[parent[i]][i];
        }
        return sum;
    }

    private static class node1 {
        int dest;
        int weight;

        private node1(int a, int b) {
            dest = a;
            weight = b;
        }
    }

    private static class Graph {
        int V;
        LinkedList<LinkedList<node1>> adj;

        private Graph(int v) {
            V = v;
            adj = new LinkedList<>();
            for (int i = 0; i < V; i++)
                adj.add(new LinkedList<>());
        }
    }

    private static class node {
        int vertex;
        int key;
    }
}
