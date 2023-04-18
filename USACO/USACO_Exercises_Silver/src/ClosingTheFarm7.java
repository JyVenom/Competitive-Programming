import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ClosingTheFarm7 {
    private static ArrayList<Integer> base = new ArrayList<>();
    private static final ArrayList<Integer> islands = new ArrayList<>();
    private static boolean prev;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new File("closing.out"));

        String[] nM = br.readLine().split(" ");
        int n = Integer.parseInt(nM[0]);
        int m = Integer.parseInt(nM[1]);
        int[] order = new int[n];
        ArrayList<Integer>[] connections = new ArrayList[n];
        int cur = n;
        prev = true;

        //Connections starts empty, fill it in with empty array list to call and fill in later
        for (int i = 0; i < n; i++)
            connections[i] = new ArrayList<>();

        //Fill in connections with given/input data
        for (int i = 0; i < m; i++) {
            String[] temp = br.readLine().split(" ");
            int p1 = Integer.parseInt(temp[0]) - 1;
            int p2 = Integer.parseInt(temp[1]) - 1;
            connections[p1].add(p2);
            connections[p2].add(p1);
        }

        //Sort each row in connections
        for (int i = 0; i < n; i++) {
            Collections.sort(connections[i]);
        }

        //Fill in order with given/input data
        for (int i = 0; i < n; i++) {
            order[i] = Integer.parseInt(br.readLine()) - 1;
        }

        //Initialize all barns as active
        ArrayList<Integer> activeFarms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            activeFarms.add(i);
        }

        //First result (does it start fully connected before the closure of any barn)
        int size = 0;
        boolean[] visited = new boolean[n];
        size = DFS(connections, activeFarms.get(0), activeFarms, visited, size);
        if (size == n) {
            out.println("YES");
        } else {
            out.println("NO");
            prev = false;
        }

        //Main code to delete each element in given order and check for full connectivity
        for (int i = 0; i < order.length - 2; i++) {
            //Update current size
            cur--;
            //Remove the barn from active barns
            int index = binarySearch1d(activeFarms, 0, activeFarms.size() - 1, order[i]);
            activeFarms.remove(index);
            //Remove from islands
            index = binarySearch1d(islands, 0, islands.size() - 1, order[i]);
            if (index != -1) {
                islands.remove(index);
            }
            //Remove all connections with it
            for (int to : connections[order[i]]) {
                index = binarySearch1d(connections[to], 0, connections[to].size() - 1, order[i]);
                connections[to].remove(index);
                if (connections[to].size() == 0) {
                    islands.add(to);
                    Collections.sort(islands);
                }
            }
            size = 0;
            visited = new boolean[n];
            base = new ArrayList<>(connections[order[i]]);
            size = DFS(connections, activeFarms.get(0), activeFarms, visited, size);
            //If previously fully connected and all barns originally connected to this barn were visited, print yes (shortcut/trick)
            if (base.size() == 0 && prev) {
                out.println("YES");
            } else if (islands.size() > 0) { //If there are islands (active barn(s) (of more than 1) with no connections)
                out.println("NO");
                prev = false;
            } else { //If all active barns visited, print yes, else no
                if (size == cur) {
                    out.println("YES");
                    prev = true;
                } else {
                    out.println("NO");
                    prev = false;

                }
            }
        }
        out.println("YES");

        out.close();
    }

    private static int DFS(ArrayList<Integer>[] connections, int at, ArrayList<Integer> activeFarms, boolean[] visited, int size) {
        //Update size
        size++;
        //Mark point as visited
        visited[at] = true;
        //Specific to this problem
        //Remove from base (for the shortcut/trick)
        int contains = binarySearch1d(base, 0, base.size() - 1, at);
        if (contains != -1) {
            base.remove(contains);
            if (base.size() == 0 && prev) {
                return -1;
            }
        }

        //Recursively call DFS on all non-visited active adjacent vertices connected to this vertices
        for (int i = 0; i < connections[at].size(); i++) {
            int to = connections[at].get(i);
            boolean active = binarySearch1d(activeFarms, 0, activeFarms.size() - 1, to) != -1;
            if (active && !visited[to]) {
                size = DFS(connections, to, activeFarms, visited, size);
                //Specific to this problem
                if (size == -1) {
                    return -1;
                }
            }
        }
        return size;
    }

    private static int binarySearch1d(ArrayList<Integer> arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid) == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub array
            if (arr.get(mid) > x)
                return binarySearch1d(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right sub array
            return binarySearch1d(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }
}
