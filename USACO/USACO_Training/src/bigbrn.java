/*
ID: jerryya2
LANG: JAVA
TASK: bigbrn
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class bigbrn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bigbrn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bigbrn.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        ArrayList<int[]> points = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int[] temp = new int[2];
            temp[0] = Integer.parseInt(st.nextToken()) - 1;
            temp[1] = Integer.parseInt(st.nextToken()) - 1;
            points.add(temp);
        }
        points.sort(Comparator.comparingInt(o -> o[1]));
        points.sort(Comparator.comparingInt(o -> o[0]));

        int[] prev = new int[N];
        for (int i = 0; i < N; i++) {
            if (points.size() > 0 && points.get(0)[0] == 0 && i == points.get(0)[1]) {
                points.remove(0);
                continue;
            }

            prev[i] = 1;
        }
        int max = 1;
        for (int i = 1; i < N; i++) {
            int[] cur = new int[N];
            for (int j = 0; j < N; j++) {
                if (points.size() > 0 && i == points.get(0)[0] && j == points.get(0)[1]) {
                    points.remove(0);
                    continue;
                }
                else if (j == 0) {
                    cur[0] = 1;
                    continue;
                }

                cur[j] = Math.min(prev[j - 1], Math.min(prev[j], cur[j - 1])) + 1;

                if (cur[j] > max) {
                    max = cur[j];
                }
            }
            prev = cur;
        }

        pw.println(max);
        pw.close();
    }
}
