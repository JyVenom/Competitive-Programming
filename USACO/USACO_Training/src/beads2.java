/*
ID: jerryya2
LANG: JAVA
PROG: beads
*/

import java.io.*;

public class beads2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("beads.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

        int n = Integer.parseInt(br.readLine());
        String line = br.readLine();
        char[] necklace = new char[n];
        for (int i = 0; i < n; i++) {
            necklace[i] = line.charAt(i);
        }

        int max = findNum(necklace, 0);
        for (int i = 1; i < n; i++){
            if (necklace[i] == necklace[i - 1]) {
                continue;
            }
            max = Math.max(max, findNum(necklace, i));
            if (max == necklace.length) {
                break;
            }
        }
        pw.println(max);
        pw.close();
    }

    private static int findNum (char[] necklace, int start) {
        boolean[] visited = new boolean[necklace.length];
        int length = (start - 1 + necklace.length) % necklace.length;
        int at = length;
        int count = 0;
        //Go backwards
        char orig = necklace[length];
        while (!visited[at]) {
            visited[at] = true;
            if (necklace[at] == orig || necklace[at] == 'w') {
                count++;
            }
            else {
                break;
            }
            at = (at - 1 + necklace.length) % necklace.length;
        }
        if (count == necklace.length) {
            return count;
        }
        //Go forward
        at = start;
        orig = necklace[start];
        while (!visited[at]) {
            visited[at] = true;
            if (necklace[at] == orig || necklace[at] == 'w') {
                count++;
            }
            else {
                break;
            }
            at = (at + 1) % necklace.length;
        }
        return count;
    }
}
