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
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class frameup2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("frameup.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frameup.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] frames = new int[h][w];
        ArrayList<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < w; j++) {
//                char cur = line[j].charAt(0);
//                if (cur != '.') {
//                    frames[i][j] = cur - 'A';
//
//                }
                int letter = line[j].charAt(0) - 'A' + 1;
                frames[i][j] = Math.max(letter, 0);
                remaining.add(letter);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (remaining.size() > 0) {
            for (int letter : remaining) {
                int[] cur = findFrame(frames, letter, w, h);
                if (cur[0] == -1) {
                    markAsRemoved(frames, cur[0], cur[1], cur[2], cur[3]);
                }
            }
        }
        Collections.reverse(ans);

        for (int letter : ans){
            pw.print(letter);
        }
        pw.println();
        pw.close();
    }

//    private static int findFrame(int[][] frames, int letter, int w, int h) {
    private static int[] findFrame(int[][] frames, int letter, int w, int h) {
        int start = -1; //x1
        int lhs = -1; //y1
        int end = -1; //x2
        int rhs = -1; //y2
        int[] ans = new int[4];
        Arrays.fill(ans, -1);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (frames[i][j] == letter) {
                    start = i;
                    break;
                }
            }
        }
        for (int i = lhs + 3; i <= w; i++) {
            if (frames[start][i] != letter) {
                rhs = i - 1;
            }
        }
        for (int i = rhs + 1; i < w; i++) {
            if (frames[start][i] == letter) {
                return ans;
            }
        }
        for (int i = h - 1; i >= 0; i--) {
            for (int j = 0; j < w; j++) {
                if (frames[i][j] == letter) {
                    end = i;
                    break;
                }
            }
        }
        ans[0] = start;
        ans[1] = lhs;
        ans[2] = end;
        ans[3] = rhs;
        return ans;
    }

    private static void markAsRemoved(int[][] frames, int start, int lhs, int rhs, int end) {
        
    }
}
