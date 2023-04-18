/*
ID: jerryya2
LANG: JAVA
TASK: inflate
*/

import java.io.*;
import java.util.Arrays;

public class inflate2 {
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


        double[][] data = new double[n][3];
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            data[i][1] = Double.parseDouble(line[0]);
            data[i][2] = Double.parseDouble(line[1]);
            data[i][0] = data[i][1] / data[i][2];
        }
        br.close();
        Arrays.sort(data, (o1, o2) -> Double.compare(o2[0], o1[0]));

        int at = 0;
        int points = 0;
        while (at < n && m > 0) {
            if (m > data[at][2]) {
                int num = (int) (m / data[at][2]);
                m -= num * data[at][2];
                points += num * data[at][1];
            }
            at++;
        }

        pw.println(points);
        pw.close();
    }
}
