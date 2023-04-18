/*
ID: jerryya2
LANG: JAVA
TASK: bigbrn
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bigbrn3 {
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

        int n = N - 1;
        ArrayList<rect> all = new ArrayList<>();
        all.add(new rect(0, 0, n, n));
        rect max = all.get(0);
        ArrayList<int[]> skipped = new ArrayList<>();
        for (int[] cur : points) {
            int size = all.size();
            if (!max.inside(cur[0], cur[1])) {
                skipped.add(cur);
            } else {
                for (int i = 0; i < size; i++) {
                    if (all.get(i).inside(cur[0], cur[1])) {
                        ArrayList<rect> temp = all.get(i).update(cur[0], cur[1]);
                        all.remove(i);
                        i--;
                        size--;
                        all.addAll(temp);
                    }
                }
                all.sort((o1, o2) -> o2.min - o1.min);
                max = all.get(0);
                for (int i = 0; i < skipped.size(); i++) {
                    size = all.size();
                    int[] temp2 = skipped.get(i);
                    if (max.inside(temp2[0], temp2[1])) {
                        for (int j = 0; j < size; j++) {
                            if (all.get(j).inside(temp2[0], temp2[1])) {
                                ArrayList<rect> temp = all.get(j).update(temp2[0], temp2[1]);
                                all.remove(j);
                                j--;
                                size--;
                                all.addAll(temp);
                            }
                        }
                        all.sort((o1, o2) -> o2.min - o1.min);
                        max = all.get(0);
                        skipped.remove(i);
                        i = -1;
                    }
                }
            }
        }
//        int max = 0;
//        for (rect rect : all) {
//            max = Math.max(max, rect.min);
//        }

//        pw.println(max);
        pw.println(max.min);
        pw.close();
    }

    private static class rect {
        int x1, y1, x2, y2; //(x1, y1) is top left, (x2, y2) is bottom right
        int height, length;
        int min; //value of smaller side (height or length)

        private rect(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;

            height = y2 - y1 + 1;
            length = x2 - x1 + 1;
            min = Math.min(height, length);
        }

//        private rect(rect other) {
//            x1 = other.x1;
//            x2 = other.x2;
//            y1 = other.y1;
//            y2 = other.y2;
//            height = other.height;
//            length = other.length;
//            min = other.min;
//        }

        private boolean inside(int x, int y) {
            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }

        private ArrayList<rect> update(int x, int y) {
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
