/*
ID: jerryya2
LANG: JAVA
TASK: ditch
*/

import java.io.*;
import java.util.StringTokenizer;

public class ditch2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ditch.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
        }
    }

    private static void calcMaxFlow(int source, int sink) {
        int totalflow = 0;
        if (source == sink)
            totalflow = Infinity
        DONE

                totalflow = 0

        while (True)
             //find path with highest capacity from
   //source to sink
  //uses a modified djikstra 's algorithm
        for all nodes i
        prevnode(i) = nil
        flow(i) = 0
        visited(i) = False
        flow(source) = infinity

        while (True)
            maxflow = 0
        maxloc = nil
       //find the unvisited node with
         //the highest capacity to it
        for all nodes i
        if (flow(i) > maxflow AND
        not visited (i))
        maxflow = flow(i)
        maxloc = i
        if (maxloc = nil)
            break inner while loop
        if (maxloc = sink)
            break inner while loop
        a visited (maxloc) = true
                   //update its neighbors
        for all neighbors i of maxloc
        if (flow(i) < min(maxflow,
                capacity(maxloc, i)))
            prevnode(i) = maxloc
        flow(i) = min(maxflow,
                capacity(maxloc, i))

        if (maxloc = nil)         //no path
        break outer while loop

                pathcapacity = flow(sink)
        totalflow = totalflow + pathcapacity

   //add that flow to the network,
   //update capacity appropriately
                curnode = sink
         //for each arc, prevnode (curnode),
            //curnode on path:
        while (curnode != source)
            nextnode = prevnode(curnode)
        capacity(nextnode, curnode) =
                capacity(nextnode, curnode) -
                        pathcapacity
        capacity(curnode, nextnode) =
                capacity(curnode, nextnode) +
                        pathcapacity
        curnode = nextnode
    }
}
