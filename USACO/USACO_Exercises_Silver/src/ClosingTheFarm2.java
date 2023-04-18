import java.util.*;
import java.io.*;

public class ClosingTheFarm2 {

    public static int n;
    public static int e;
    public static ArrayList[] graph;

    public static void main(String[] args) throws Exception {

        // Read in data.
        BufferedReader stdin = new BufferedReader(new FileReader("closing.in"));
        StringTokenizer tok = new StringTokenizer(stdin.readLine());
        n = Integer.parseInt(tok.nextToken());
        e = Integer.parseInt(tok.nextToken());
        graph = new ArrayList[n];
        for (int i=0; i<n; i++) graph[i] = new ArrayList<Integer>();

        // Read in edges.
        for (int i=0; i<e; i++) {
            tok = new StringTokenizer(stdin.readLine());
            int v1 = Integer.parseInt(tok.nextToken())-1;
            int v2 = Integer.parseInt(tok.nextToken())-1;
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        // Store items to be removed.
        int[] remList = new int[n];
        for (int i=0; i<n; i++)
            remList[i] = Integer.parseInt(stdin.readLine().trim())-1;

        // Producing answers backwards, so store them.
        boolean[] res = new boolean[n];
        dset dj = new dset(n);
        res[n-1] = true;
        boolean[] inGraph = new boolean[n];
        inGraph[remList[n-1]] = true;

        // Go backwards through the list of deletions.
        for (int i=n-2; i>=0; i--) {

            int item = remList[i];
            for (int j=0; j<graph[item].size(); j++) {
                int next = (Integer)(graph[item].get(j));
                if (inGraph[next]) {
                    dj.union(item, next);
                }
            }

            res[i] = (dj.numTrees == i+1);
            inGraph[item] = true;
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<n; i++) {
            if (res[i])
                sb.append("YES\n");
            else
                sb.append("NO\n");
        }

        PrintWriter out = new PrintWriter(new FileWriter("closing.out"));
        out.print(sb);
        out.close();
        stdin.close();

    }
}

//  Just used for the Disjoint set class.
class pair {
    public int parent;
    public int height;

    public pair(int a, int b) {
        parent = a;
        height = b;
    }
}

// Basic Disjoint Set without path compression.
class dset {

    private final pair[] parents;
    public int numTrees;

    public dset(int n) {
        parents = new pair[n];
        for (int i=0; i<n; i++)
            parents[i] = new pair(i,0);
        numTrees = n;
    }

    public int find(int child) {
        while (parents[child].parent != child)
            child = parents[child].parent;
        return child;
    }

    public boolean union(int c1, int c2) {
        int root1 = find(c1);
        int root2 = find(c2);

        // Nothing to union.
        if (root1 == root2)
            return false;

        // Root 1 stays parent.
        if (parents[root1].height > parents[root2].height) {
            parents[root2].parent = root1;
        }

        // Tie case get assigned to root 1 also.
        else if (parents[root1].height == parents[root2].height) {
            parents[root2].parent = root1;
            parents[root1].height++;
        }

        // Must use root 2 here.
        else {
            parents[root1].parent = root2;
        }

        numTrees--;
        return true;
    }
}