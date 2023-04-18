/*
ID: jerryya2
LANG: JAVA
TASK: fence
*/

import java.io.*;
import java.util.*;

public class fence3 {
    private static LinkedList<Integer>[] adj = new LinkedList[0];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fence.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));

        int f = Integer.parseInt(br.readLine());
        adj = new LinkedList[500];
        for (int i = 0; i < 500; ++i)
            adj[i] = new LinkedList();
        for (int i = 0; i < f; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            addEdge(a, b);
        }
        br.close();

        int i;
        // Find a vertex with non-zero degree
        for (i = 0; i < 500; i++)
            if (adj[i].size() != 0)
                break;
        ArrayList<Integer> circuit = DFSUtil(i, new boolean[500], new ArrayList<>());

        for (Integer node : circuit) {
            pw.println(node + 1);
        }
        pw.close();
    }

    //Function to add an edge into the graph
    private static void addEdge(int v, int w) {
        adj[v].add(w);// Add w to v's list.
        adj[w].add(v); //The graph is undirected
    }

    // A function used by DFS
    private static ArrayList<Integer> DFSUtil(int v, boolean[] visited, ArrayList<Integer> circuit) {
        // Mark the current node as visited
        visited[v] = true;
        circuit.add(v);

        // Recur for all the vertices adjacent to this vertex
        for (int n : adj[v]) {
            if (!visited[n])
                DFSUtil(n, visited, circuit);
        }
        return circuit;
    }

    // Method to check if all non-zero degree vertices are
    // connected. It mainly does DFS traversal starting from
//    private static boolean isConnected(ArrayList<Integer> circuit) {
//        // Mark all the vertices as not visited
//        boolean[] visited = new boolean[V];
//        int i;
//        for (i = 0; i < V; i++)
//            visited[i] = false;
//
//        // Find a vertex with non-zero degree
//        for (i = 0; i < V; i++)
//            if (adj[i].size() != 0)
//                break;
//
//        // If there are no edges in the graph, return true
//        if (i == V)
//            return true;
//
//        // Start DFS traversal from a vertex with non-zero degree
//        DFSUtil(i, visited, circuit);
//
//        // Check if all non-zero degree vertices are visited
//        for (i = 0; i < V; i++)
//            if (!visited[i] && adj[i].size() > 0)
//                return false;
//
//        return true;
//    }

    /* The function returns one of the following values
       0 --> If grpah is not Eulerian
       1 --> If graph has an Euler path (Semi-Eulerian)
       2 --> If graph has an Euler Circuit (Eulerian)  */
//    private static ArrayList<Integer> isEulerian(ArrayList<Integer> circuit) {
//        // Check if all non-zero degree vertices are connected
//        if (!isConnected(circuit))
//            return 0;

//        // Count vertices with odd degree
//        int odd = 0;
//        for (int i = 0; i < V; i++)
//            if (adj[i].size() % 2 != 0)
//                odd++;
//
//        // If count is more than 2, then graph is not Eulerian
//        if (odd > 2)
//            return 0;
//
//        // If odd count is 2, then semi-eulerian.
//        // If odd count is 0, then eulerian
//        // Note that odd count can never be 1 for undirected graph
//        return (odd == 2) ? 1 : 2;
//    }

//    // Function to run test cases
//    void test(ArrayList<Integer> circuit) {
//        int res = isEulerian(circuit);
//        if (res == 0)
//            System.out.println("graph is not Eulerian");
//        else if (res == 1)
//            System.out.println("graph has a Euler path");
//        else
//            System.out.println("graph has a Euler cycle");
//    }
}