import java.io.*;
import java.util.*;

public class BessiesBirthdayBuffet4 {
    private static int[] dist;
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V; // Number of vertices
    List<List<Node> > adj;

    public BessiesBirthdayBuffet4(int V) {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(V, new Node());
    }

    // Function for Dijkstra's Algorithm
    public void dijkstra(List<List<Node> > adj, int src) {
        int count = 0;
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;
        while (settled.size() != V && pq.size() > 0) {
            count++;

            // remove the minimum distance node
            // from the priority queue
            int u = pq.remove().node;

            // adding the node whose distance is
            // finalized
            settled.add(u);

            e_Neighbours(u);
        }
        System.out.println(count);
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

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("buffet.in"));
        PrintWriter out = new PrintWriter(new File("buffet.out"));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int cost = Integer.parseInt(line[1]);
        int[] max = new int[n];
        int[] q = new int[n];
        ArrayList<ArrayList<Integer>> quality = new ArrayList<>();
        List<List<Node> > adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>();

        for (int i = 0; i < n; i++){
            quality.add(new ArrayList<>());
            connections.add(new ArrayList<>());
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
                connections.get(i).add(Integer.parseInt(line[2 + j]) - 1);
//                adj.get(i).add(new Node(Integer.parseInt(line[2 + j]) - 1, cost));
            }
        }
        quality.sort(Comparator.comparing(a -> a.get(0)));
        for (int i = 0; i < n; i++){
            quality.get(i).add(i);
        }
        ArrayList<ArrayList<Integer>> connections2 = new ArrayList<>();
        for (int i = 0; i < n; i++){
            connections2.add(new ArrayList<>(connections.get(quality.get(i).get(1))));
        }
        quality.sort(Comparator.comparing(a -> a.get(1)));

        for (int i = 0; i < n; i++){
            for (int j = 0; j < connections2.get(i).size(); j++) {
                int index = binarySearch2d(quality, 0, quality.size() - 1, connections2.get(i).get(j));
                connections2.get(i).set(j, quality.get(index).get(2));
            }
        }

        for (int i = 0; i < n; i++) {
            int size = connections2.get(i).size();
            for (int j = 0; j < size; j++) {
                adj.get(i).add(new Node(connections2.get(i).get(j), cost));
            }
        }

        Arrays.sort(q);
        quality.sort(Comparator.comparing(a -> a.get(0)));
        int answer = 0;
        for (int i = 0; i < n; i++){
            //Find distances from patch i to all prev patches
//            int patch1 = quality.get(i).get(1);
            BessiesBirthdayBuffet4 dpq = new BessiesBirthdayBuffet4(n);
//            dpq.dijkstra(adj, patch1);
            dpq.dijkstra(adj, i);
//            max[patch1] = q[patch1];
            max[i] = q[i];
            for (int j = 0; j < i; j++) {
//                int patch2 = quality.get(j).get(1);
//                max[patch1] = Math.max(max[patch1], max[patch2] + q[patch1] - dist[patch2]);
                max[i] = Math.max(max[i], max[j] + q[i] - dist[j]);
            }
//            answer = Math.max(answer, max[patch1]);
            answer = Math.max(answer, max[i]);
        }

        out.println(answer);
        out.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int binarySearch2d(ArrayList<ArrayList<Integer>> arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid).get(1) == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub array
            if (arr.get(mid).get(1) > x)
                return binarySearch2d(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right sub array
            return binarySearch2d(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }
}

// Class to represent a node in the graph
class Node implements Comparator<Node> {
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