import java.io.*;
import java.util.*;

public class BessiesBirthdayBuffet2 {
    private static int[] dist;
    private final Set<Integer> settled;
    private final PriorityQueue<Node> pq;
    private final int V; // Number of vertices
    List<List<Node>> adj;

    public BessiesBirthdayBuffet2(int V)
    {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(V, new Node());
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("buffet.in"));
        PrintWriter out = new PrintWriter(new File("buffet.out"));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int cost = Integer.parseInt(line[1]);
        int[] max = new int[n];
        int[] q = new int[n];
        ArrayList<ArrayList<Integer>> quality = new ArrayList<>();
        List<List<Node> > adj = new ArrayList<>();

        for (int i = 0; i < n; i++){
            quality.add(new ArrayList<>());
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        for (int i = 0; i < n; i++){
            line = br.readLine().split(" ");
            quality.get(i).add(Integer.parseInt(line[0]));
            quality.get(i).add(i);
            q[i] = Integer.parseInt(line[0]);
            int num = Integer.parseInt(line[1]);
            for (int j = 0; j < num; j++){
                adj.get(i).add(new Node(Integer.parseInt(line[2 + j]) - 1, cost));
            }
        }
        quality.sort(Comparator.comparing(a -> a.get(0)));

        int answer = 0;
        for (int i = 0; i < n; i++){
            //Find distances from patch i to all prev patches
            int patch1 = quality.get(i).get(1);
            BessiesBirthdayBuffet2 dpq = new BessiesBirthdayBuffet2(n);
            dpq.dijkstra(adj, patch1);
            max[patch1] = q[patch1];
            for (int j = 0; j < i; j++) {
                int patch2 = quality.get(j).get(1);
                max[patch1] = Math.max(max[patch1], max[patch2] + q[patch1] - dist[patch2]);
            }
            answer = Math.max(answer, max[patch1]);
        }

        out.println(answer);
        out.close();
    }

    // Function for Dijkstra's Algorithm
    public void dijkstra(List<List<Node> > adj, int src) {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;
        while (settled.size() != V) {

            // remove the minimum distance node
            // from the priority queue
            if (pq.size() == 0){
                System.out.println();
            }
            int u = pq.remove().node;

            // adding the node whose distance is
            // finalized
            settled.add(u);

            e_Neighbours(u);
        }
    }

    // Function to process all the neighbours
    // of the passed node
    private void e_Neighbours(int u) {
        int edgeDistance;
        int newDistance;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;

                // Add the current node to the queue
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    // Class to represent a node in the graph
    static class Node implements Comparator<Node> {
        public int node;
        public int cost;

        public Node() {
        }

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            return Integer.compare(node1.cost, node2.cost);
        }
    }
}