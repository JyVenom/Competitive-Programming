/*
ID: jerryya2
LANG: JAVA
TASK: schlnet
*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class schlnet {
    private static final ArrayList<ArrayList<Integer>> scc = new ArrayList<>();
    private static int Time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("schlnet.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("schlnet.out")));

        int N = Integer.parseInt(br.readLine());
        int[] inDeg = new int[N];
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken()) - 1;
            while (temp != -1) {
                list.add(temp);
                inDeg[temp]++;
                temp = Integer.parseInt(st.nextToken()) - 1;
            }
            list.add(i);
            edges.add(list);
        }

        ArrayList<HashSet<Integer>> temp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            temp.add(new HashSet<>());
        }
        for (int i = 0; i < N; i++) {
            boolean[] processed = new boolean[N];
            helper(temp.get(i), edges, processed, i);
        }
        ArrayList<ArrayList<Integer>> all = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            all.add(new ArrayList<>(temp.get(i)));
        }
//        all.sort((o1, o2) -> o2.size() - o1.size());
        int count = 0;
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (inDeg[i] == 0) {
                count++;
                for (int j = 0; j < all.get(i).size(); j++) {
                    visited[all.get(i).get(j)] = true;
                }
            }
        }
        ArrayList<Integer> rem = findRem(new ArrayList<>(), visited);
        while (rem.size() > 0) {
            int max = 0;
            for (int i = 1; i < rem.size(); i++) {
                if (all.get(rem.get(i)).size() > (all.get(rem.get(max)).size())) {
                    max = i;
                }
            }
            for (int i = 0; i < all.get(rem.get(max)).size(); i++) {
                visited[all.get(rem.get(max)).get(i)] = true;
            }
            count++;
            rem = findRem(new ArrayList<>(), visited);
        }
        Time = 0;
        SCC(edges, N);
        inDeg = new int[scc.size()];
        int[] outDeg = new int[scc.size()];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < edges.get(i).size(); j++) {
                int start = -1;
                int end = -1;
                for (int k = 0; k < scc.size(); k++) {
                    for (int l = 0; l < scc.get(k).size(); l++) {
                        if (scc.get(k).get(l) == i) {
                            start = k;
                            break;
                        }
                    }
                }
                for (int k = 0; k < scc.size(); k++) {
                    for (int l = 0; l < scc.get(k).size(); l++) {
                        if (scc.get(k).get(l).equals(edges.get(i).get(j))) {
                            end = k;
                            break;
                        }
                    }
                }
                if (end != start) {
                    inDeg[end] = 1;
                    outDeg[start] = 1;
                }
            }
        }
        int totInDeg = 0;
        int totOutDeg = 0;
        for (int i = 0; i < scc.size(); i++) {
            if (inDeg[i] == 0) {
                totInDeg++;
            }
            if (outDeg[i] == 0) {
                totOutDeg++;
            }
        }

        pw.println(count);
        if (scc.size() == 1) {
            pw.println(0);
        } else {
            pw.println(Math.max(totInDeg, totOutDeg));
        }
        pw.close();
    }

    private static ArrayList<Integer> findRem(ArrayList<Integer> rem, boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            boolean cur = visited[i];
            if (!cur) {
                rem.add(i);
            }
        }
        return rem;
    }

    private static void helper(HashSet<Integer> list, ArrayList<ArrayList<Integer>> edges, boolean[] processed, int at) {
        if (processed[at]) {
            return;
        }

        processed[at] = true;
        list.add(at);
        for (int i = 0; i < edges.get(at).size(); i++) {
            helper(list, edges, processed, edges.get(at).get(i));
        }
    }

    private static void SCCUtil(ArrayList<ArrayList<Integer>> edges, Stack<Integer> st, boolean[] stackMember, int[] low, int[] disc, int u) {
        disc[u] = Time;
        low[u] = Time;
        Time += 1;
        stackMember[u] = true;
        st.push(u);

        int n;

        for (Integer integer : edges.get(u)) {
            n = integer;

            if (disc[n] == -1) {
                SCCUtil(edges, st, stackMember, low, disc, n);
                low[u] = Math.min(low[u], low[n]);
            } else if (stackMember[n]) {
                low[u] = Math.min(low[u], disc[n]);
            }
        }

        int w = -1;
        if (low[u] == disc[u]) {
            ArrayList<Integer> temp = new ArrayList<>();
            while (w != u) {
                w = st.pop();
                temp.add(w);
                stackMember[w] = false;
            }
            scc.add(temp);
        }
    }

    private static void SCC(ArrayList<ArrayList<Integer>> edges, int N) {
        int[] disc = new int[N];
        int[] low = new int[N];
        for (int i = 0; i < N; i++) {
            disc[i] = -1;
            low[i] = -1;
        }

        boolean[] stackMember = new boolean[N];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (disc[i] == -1)
                SCCUtil(edges, st, stackMember, low, disc, i);
        }
    }
}
