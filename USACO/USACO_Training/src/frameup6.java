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
import java.util.*;

public class frameup6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("frameup.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frameup.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] frames = new int[h][w];
        HashSet<Integer> all = new HashSet<>();
        for (int i = 0; i < h; i++) {
            String line = br.readLine();
            for (int j = 0; j < w; j++) {
                int letter = line.charAt(j) - 'A' + 1;
                if (letter >= 1 && letter <= 26) {
                    frames[i][j] = letter;
                    all.add(letter);
                }
            }
        }

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> remaining = new ArrayList<>(all);
        Collections.sort(remaining);
        int n = remaining.size();
        ArrayList<Integer> ans = new ArrayList<>();
        findAns(res, remaining, ans, frames, w, h);
        for (ArrayList<Integer> ans2 : res) {
            Collections.reverse(ans2);
        }
        for (int i = n - 1; i >= 0; i--) {
            int finalI = i;
            res.sort(Comparator.comparing(a -> a.get(finalI)));
        }

        for (ArrayList<Integer> ans2 : res) {
            for (int letter : ans2) {
                pw.print((char) (letter + 'A' - 1));
            }
            pw.println();
        }
        pw.close();
    }

    private static void findAns(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> remaining, ArrayList<Integer> ans, int[][] frames, int w, int h) {
        for (int i = 0; i < remaining.size(); i++) {
            int letter = remaining.get(i);
            int[] cur = findFrame(frames, letter, w, h);
            if (cur[0] != -1) {
                int[][] framesCopy = new int[h][w];
                for (int j = 0; j < h; j++) {
                    if (w >= 0) System.arraycopy(frames[j], 0, framesCopy[j], 0, w);
                }
                ArrayList<Integer> ansCopy = new ArrayList<>(ans);
                ArrayList<Integer> remainingCopy = new ArrayList<>(remaining.subList(0, i));
                remainingCopy.addAll(remaining.subList(i + 1, remaining.size()));
                markAsRemoved(framesCopy, cur[0], cur[1], cur[2], cur[3]);
                ansCopy.add(letter);
                findAns(res, remainingCopy, ansCopy, framesCopy, w, h);
            }
        }
        if (remaining.size() == 0) {
            res.add(ans);
        }
    }

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
            if (start != -1) {
                break;
            }
        }
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (frames[j][i] == letter) {
                    lhs = i;
                    break;
                }
            }
            if (lhs != -1) {
                break;
            }
        }
        for (int i = h - 1; i >= 0; i--) {
            for (int j = 0; j < w; j++) {
                if (frames[i][j] == letter) {
                    end = i;
                    break;
                }
            }
            if (end != -1) {
                break;
            }
        }
        for (int i = w - 1; i >= 0; i--) {
            for (int j = 0; j < h; j++) {
                if (frames[j][i] == letter) {
                    rhs = i;
                    break;
                }
            }
            if (rhs != -1) {
                break;
            }
        }
        for (int i = lhs; i <= rhs; i++) {
            if (!(frames[start][i] == letter || frames[start][i] == -1)) {
                return ans;
            }
            if (!(frames[end][i] == letter || frames[end][i] == -1)) {
                return ans;
            }
        }
        for (int i = start; i <= end; i++) {
            if (!(frames[i][lhs] == letter || frames[i][lhs] == -1)) {
                return ans;
            }
            if (!(frames[i][rhs] == letter || frames[i][rhs] == -1)) {
                return ans;
            }
        }
        ans[0] = start;
        ans[1] = lhs;
        ans[2] = end;
        ans[3] = rhs;
        return ans;
    }

    private static void markAsRemoved(int[][] frames, int start, int lhs, int end, int rhs) {
        for (int i = lhs; i <= rhs; i++) {
            frames[start][i] = -1;
            frames[end][i] = -1;
        }
        for (int i = start + 1; i < end; i++) {
            frames[i][lhs] = -1;
            frames[i][rhs] = -1;
        }
    }
}
