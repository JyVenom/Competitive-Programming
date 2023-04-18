/*
ID: jerryya2
LANG: JAVA
TASK: bigbrn
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bigbrn2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bigbrn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bigbrn.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] points = new int[T][2];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken()) - 1;
            points[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        ArrayList<rect> all = new ArrayList<>();
        all.add(new rect(0, 0, N - 1, N - 1));
        for (int[] cur : points) {
            int size = all.size();
            for (int i = 0; i < size; i++) {
                if (all.get(i).inside(cur[0], cur[1])) {
                    ArrayList<rect> temp = all.get(i).update(cur[0], cur[1]);
                    all.remove(i);
                    i--;
                    size--;
                    all.addAll(temp);
                }
            }
        }
        int max = 0;
        for (rect rect : all) {
            max = Math.max(max, rect.min);
        }

        pw.println(max);
        pw.close();
    }

    private static class rect {
        int x1, y1, x2, y2; //(x1, y1) is top left, (x2, y2) is bottom right
        int height, length;
        int min; //smaller side (height or length)

        private rect(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;

            height = y2 - y1 + 1;
            length = x2 - x1 + 1;
            min = Math.min(height, length);
        }

        private boolean inside (int x, int y) {
            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }

        private ArrayList<rect> update (int x, int y){
            ArrayList<rect> temp = new ArrayList<>();

            if (x > x1) { //left
                rect r = new rect(x1, y1, x - 1, y2);
                temp.add(r);
            }
            if (x < x2) { //right
                rect r = new rect(x + 1, y1, x2, y2);
                temp.add(r);
            }
            if (y > y1) { //above
                rect r = new rect(x1, y1, x2, y - 1);
                temp.add(r);
            }
            if (y < y2) { //below
                rect r = new rect(x1, y + 1, x2, y2);
                temp.add(r);
            }

            return temp;
        }
    }
}
