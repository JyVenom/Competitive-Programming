/*
ID: jerryya2
LANG: JAVA
TASK: inflate
*/

import java.io.*;

public class inflate {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inflate.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));

        String[] line = br.readLine().split(" ");
        int m = -1;
        int n = -1;
        int tmp = 0;
        for (; tmp < line.length; tmp++) {
            if (!line[tmp].equals("")) {
                m = Integer.parseInt(line[tmp]);
                break;
            }
        }
        for (; tmp < line.length; tmp++) {
            if (!line[tmp].equals("")) {
                n = Integer.parseInt(line[tmp]);
            }
        }

        int [] v = new int[n];
        int [] time = new int[n];
        for(int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            v[i] = Integer.parseInt(line[0]);
            time[i] = Integer.parseInt(line[1]);
        }

        long [] score = new long[m + 1];
        for(int i = 0; i < n; i++) { // iterating through each contest
            for(int j = time[i]; j <= m; j++) { // iterating through all the possible times up to m
                score[j] = Math.max(score[j], v[i] + score[j - time[i]]);
            }
        }

        pw.println(score[m]);
        pw.close();
    }
}
