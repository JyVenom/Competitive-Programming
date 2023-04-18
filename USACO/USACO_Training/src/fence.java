/*
ID: jerryya2
LANG: JAVA
TASK: fence
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class fence {
    private static int circuitPos;
    private static Node[] nodes;
    private static int[] circuit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fence.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));

        int f = Integer.parseInt(br.readLine());
        nodes = new Node[501];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node();
        }
        circuit = new int[f + 1];
        for (int i = 0; i < f; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes[a].neighbors.add(b);
            nodes[b].neighbors.add(a);
        }
        for (Node node : nodes) {
            Collections.sort(node.neighbors);
        }
        br.close();

        findEulerCircuit();

        for (int i = f; i >= 0; i--) {
            pw.println(circuit[i]);
        }
        pw.close();
    }

    public static void findEulerCircuit() {
        circuitPos = 0;
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].neighbors.size() % 2 == 1) {
                findCircuit(i);
                return;
            }
        }
        findCircuit(1);
    }

    public static void findCircuit(int i) {
        while (nodes[i].neighbors.size() > 0) {
            int j = nodes[i].neighbors.remove(0);
            nodes[j].neighbors.remove((Integer) i);
            findCircuit(j);
        }
        circuit[circuitPos] = i;
        circuitPos++;
    }

    public static class Node {
        ArrayList<Integer> neighbors = new ArrayList<>();
    }
}
