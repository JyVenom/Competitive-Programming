/*
ID: jerryya2
LANG: JAVA
TASK: schlnet
*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class schlnet3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("schlnet.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("schlnet.out")));

        int N = Integer.parseInt(br.readLine());
        int[] inDeg = new int[N];
        int[] outDeg = new int[N];
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken()) - 1;
            while (temp != -1) {
                list.add(temp);
                inDeg[temp]++;
                outDeg[i]++;
                temp = Integer.parseInt(st.nextToken()) - 1;
            }
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
            int max = rem.get(0);
            for (int i = 1; i < rem.size(); i++) {
                if (all.get(rem.get(i)).size() > (all.get(rem.get(max)).size())) {
                    max = i;
                }
            }
            for (int i = 0; i < all.get(max).size(); i++) {
                visited[all.get(max).get(i)] = true;
            }
            count++;
            rem = findRem(new ArrayList<>(), visited);
        }
        int totInDeg = 0;
        int totOutDeg = 0;
        for (int i = 0; i < N; i++) {
            totInDeg += inDeg[i];
            totOutDeg += outDeg[i];
        }

        pw.println(count);
        pw.println(Math.max(N - totInDeg, N - totOutDeg));
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
}
