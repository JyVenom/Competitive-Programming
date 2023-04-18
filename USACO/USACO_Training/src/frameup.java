/*
ID: jerryya2
LANG: JAVA
TASK: frameup
*/

//(w*h)*((n^2+n)/2)
//w <= 30
//h <= 30
//n <= 26
//315,900

//for loop through remaining not-yet-removed letters and check if it is the top frame
//screen down to find top
//screen up to find bottom
//screen left to find left
//screen right to find right
//check if any of these sides are covered by another frame
//if not, remove frame by marking as -1 (-1 = removes/unknown, 0 = empty, 1-26 = (letter of a) frame)

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class frameup {
    private static final Rect[] rc_map = new Rect[128];
    private static final ArrayList<String> resultList = new ArrayList<>();
    private static final boolean[] mark = new boolean[128];
    private static int N;
    private static int N1;
    private static char[][] map;
    private static Rect[] rcs;
    private static char[] searches;
    private static char[] results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("frameup.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frameup.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        N = 0;
        for (int i = 0; i < h; i++) {
            String line = br.readLine();
            for (int j = 0; j < w && j < line.length(); j++) {
                char c = line.charAt(j);
                if (c >= 'A' && c <= 'Z') {
                    Rect rc = rc_map[c];
                    if (rc == null) {
                        N++;
                        rc = new Rect(c);
                        rc_map[c] = rc;
                    }
                    map[i][j] = c;
                    rc.update(i, j);
                }
            }
        }
        N1 = N - 1;
        rcs = new Rect[N];
        int index = 0;
        for (Rect rect : rc_map) {
            if (rect != null)
                rcs[index++] = rect;
        }
        searches = new char[N * N];
        results = new char[N];
        mark[0] = true;
        search(0, 0);
        Collections.sort(resultList);
        for (Object o : resultList) pw.println(o);
        pw.close();
    }

    private static void search(int index, int search_index) {
        if (index == N) {
            resultList.add(new String(results));
            return;
        }
        int search_index1 = search_rect(search_index);
        for (int i = search_index; i < search_index1; i++) {
            Rect rc = rc_map[searches[i]];
            results[N1 - index] = rc.c;
            mark[rc.c] = true;
            search(index + 1, search_index1);
            mark[rc.c] = false;
        }
    }

    private static int search_rect(int index) {
        int start = index;
        for (int i = 0; i < N; i++) {
            Rect rc = rcs[i];
            if (!mark[rc.c] && rc.check_visible(start))
                searches[index++] = rc.c;
        }
        return index;
    }

    static class Rect {
        char c;
        int minRow, maxRow, minCol, maxCol;
        int visible_index = Integer.MAX_VALUE;

        Rect(char c) {
            this.c = c;
            maxRow = -1;
            maxCol = -1;
            minRow = Integer.MAX_VALUE;
            minCol = Integer.MAX_VALUE;
        }

        public String toString() {
            return c + "(" + minRow + "," + maxRow + "," + minCol + "," + maxCol + ")";
        }

        void update(int row, int col) {
            if (row < minRow) minRow = row;
            if (row > maxRow) maxRow = row;
            if (col < minCol) minCol = col;
            if (col > maxCol) maxCol = col;
        }

        boolean check_char(char c) {
            return c != 0 && c != this.c && !mark[c];
        }

        boolean check_visible(int index) {
            if (index > visible_index)
                return true;
            boolean r = this.check_visible();
            this.visible_index = r ? index : Integer.MAX_VALUE;
            return r;
        }

        boolean check_visible() {
            char[][] map = frameup.map;
            for (int i = minRow; i <= maxRow; i++) {
                if (check_char(map[i][minCol]))
                    return false;
                if (check_char(map[i][maxCol]))
                    return false;
            }
            for (int i = minCol + 1; i < maxCol; i++) {
                if (check_char(map[minRow][i]))
                    return false;
                if (check_char(map[maxRow][i]))
                    return false;
            }
            return true;
        }
    }
} 