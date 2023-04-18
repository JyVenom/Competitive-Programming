/*
ID: jerryya2
LANG: JAVA
TASK: fence
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class fence4 {
    private static PrintWriter pw;
    private final int vertices; // No. of vertices
    private boolean first = true;
    private ArrayList<Integer>[] adj; // adjacency list

    private fence4(int numOfVertices) {
        this.vertices = numOfVertices;

        initGraph();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fence.in"));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));

        int f = Integer.parseInt(br.readLine());
        fence4 g = new fence4(500);
        for (int i = 0; i < f; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            g.addEdge(a, b);
        }
        br.close();
        g.sortGraph();

        g.printEulerTour();
        pw.close();
    }

    // utility method to initialise adjacency list 
    @SuppressWarnings("unchecked")
    private void initGraph() {
        adj = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    private void sortGraph() {
        for (int i = 0; i < vertices; i++) {
            Collections.sort(adj[i]);
        }
    }

    // add edge u-v
    private void addEdge(Integer u, Integer v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    // This function removes edge u-v from graph. 
    private void removeEdge(Integer u, Integer v) {
        adj[u].remove(v);
        adj[v].remove(u);
    }

    /* The main function that print Eulerian Trail.  
       It first finds an odd degree vertex (if there  
       is any) and then calls printEulerUtil() to 
       print the path */
    private void printEulerTour() {
        // Find a vertex with odd degree 
        int u = 0;
        for (int i = 0; i < vertices; i++) {
            if (adj[i].size() % 2 == 1) {
                u = i;
                break;
            }
        }

        // Print tour starting from oddv
        printEulerUtil(u);
    }

    // Print Euler tour starting from vertex u 
    private void printEulerUtil(Integer u) {
        // Recur for all the vertices adjacent to this vertex 
        for (int i = 0; i < adj[u].size(); i++) {
            Integer v = adj[u].get(i);
            // If edge u-v is a valid next edge 
            if (isValidNextEdge(u, v)) {
                if (!first) {
                    pw.println(v + 1);
                } else {
                    pw.println(u + 1);
                    pw.println(v + 1);
                    first = false;
                }

                // This edge is used so remove it now 
                removeEdge(u, v);
                printEulerUtil(v);
            }
        }
    }

    // The function to check if edge u-v can be 
    // considered as next edge in Euler Tout 
    private boolean isValidNextEdge(Integer u, Integer v) {
        // The edge u-v is valid in one of the 
        // following two cases: 

        // 1) If v is the only adjacent vertex of u  
        // ie size of adjacent vertex list is 1 
        if (adj[u].size() == 1) {
            return true;
        }

        // 2) If there are multiple adjacents, then 
        // u-v is not a bridge Do following steps  
        // to check if u-v is a bridge 
        // 2.a) count of vertices reachable from u 
        boolean[] isVisited = new boolean[this.vertices];
        int count1 = dfsCount(u, isVisited);

        // 2.b) Remove edge (u, v) and after removing 
        //  the edge, count vertices reachable from u 
        removeEdge(u, v);
        isVisited = new boolean[this.vertices];
        int count2 = dfsCount(u, isVisited);

        // 2.c) Add the edge back to the graph 
        addEdge(u, v);
        return count1 <= count2;
    }

    // A DFS based function to count reachable 
    // vertices from v 
    private int dfsCount(Integer v, boolean[] isVisited) {
        // Mark the current node as visited 
        isVisited[v] = true;
        int count = 1;
        // Recur for all vertices adjacent to this vertex 
        for (int adj : adj[v]) {
            if (!isVisited[adj]) {
                count = count + dfsCount(adj, isVisited);
            }
        }
        return count;
    }
}