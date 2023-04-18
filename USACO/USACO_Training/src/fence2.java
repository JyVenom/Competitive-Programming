/*
ID: jerryya2
LANG: JAVA
TASK: fence
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class fence2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fence.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));

        int f = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < f; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        br.close();
        for (ArrayList<Integer> edge : edges) {
            Collections.sort(edge);
        }

        ArrayList<Integer> circuit = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            ArrayList<ArrayList<Integer>> copy = new ArrayList<>(edges);
            circuit = findCircuit(i, new ArrayList<>(), copy);
            boolean good = true;
            for (ArrayList<Integer> node : copy) {
                if (node.size() > 0) {
                    good = false;
                    break;
                }
            }
            if (circuit.size() < f + 1 || !good) {
                circuit = new ArrayList<>();
            }
            else {
                break;
            }
        }
        for (Integer node : circuit) {
            pw.println(node + 1);
        }

        pw.close();
    }

    private static ArrayList<Integer> findCircuit(int node, ArrayList<Integer> circuit, ArrayList<ArrayList<Integer>> edges) {
        if (edges.get(node).size() != 0) {
            while (edges.get(node).size() > 0) {
                int next = edges.get(node).get(0);
                edges.get(node).remove(0);
                edges.get(next).remove((Integer) node);
                findCircuit(next, circuit, edges);
            }
        }
        circuit.add(0, node);

        return circuit;
    }
}