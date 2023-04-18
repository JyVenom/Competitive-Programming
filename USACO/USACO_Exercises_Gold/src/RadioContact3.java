import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RadioContact3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("radio.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] f = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String line = br.readLine();
        int[] fPath = new int[n];
        int[] endF = f.clone();
        int maxFX = f[0];
        int maxFY = f[1];
        for (int i = 0; i < n; i++) {
            char c = line.charAt(i);
            if (c == 'N') {
                endF[1]++;
                maxFY = Math.max(maxFY, endF[1]);
            }
            else if (c == 'E') {
                fPath[i] = 1;
                endF[0]++;
                maxFX = Math.max(maxFX, endF[0]);
            }
            else if (c == 'S') {
                fPath[i] = 2;
                endF[1]--;
                maxFY = Math.max(maxFY, endF[1]);
            }
            else if (c == 'W') {
                fPath[i] = 3;
                endF[0]--;
                maxFX = Math.max(maxFX, endF[0]);
            }
        }
        line = br.readLine();
        int[] bPath = new int[m];
        int[] endB = b.clone();
        int maxBX = b[0];
        int maxBY = b[1];
        for (int i = 0; i < m; i++) {
            char c = line.charAt(i);
            if (c == 'N') {
                endB[1]++;
                maxBY = Math.max(maxBY, endB[1]);
            }
            if (c == 'E') {
                bPath[i] = 1;
                endB[0]++;
                maxBX = Math.max(maxBX, endB[0]);
            }
            else if (c == 'S') {
                bPath[i] = 2;
                endB[1]--;
                maxBY = Math.max(maxBY, endB[1]);
            }
            else if (c == 'W') {
                bPath[i] = 3;
                endB[0]--;
                maxBX = Math.max(maxBX, endB[0]);
            }
        }

//        ArrayList<int[][]> toProcess = new ArrayList<>();
//        toProcess.add(new int[][]{endF.clone(), endB.clone()});
//        int[][][][] min = new int[1000][1000][1000][1000];
////        for (int i = 0; i < toProcess.size(); i++) {
//        while (toProcess.size() > 0) {
//            int[][] cur = toProcess.get(0);
//            toProcess.remove(0);
//
//
//        }
        long[][][][] min = new long[maxFX + 1][maxFY + 1][maxBX + 1][maxBY + 1];
        helper(min, new int[]{f[0], f[1], b[0], b[1]}, fPath, bPath, 0, 0);


        pw.println(min[endF[0]][endF[1]][endB[0]][endB[1]]);
        pw.close();
    }

    private static void helper (long[][][][] min, int[] cur, int[] fPath, int[] bPath, int remF, int remB) {
        if (remF < fPath.length) {
            int temp = fPath[remF];
            int[] next = cur.clone();

            if (temp == 0) {
                next[1]++;
            }
            else if (temp == 1) {
                next[0]++;
            }
            else if (temp == 2) {
                next[1]--;
            }
            else if (temp == 3) {
                next[0]--;
            }

            long dist = min[cur[0]][cur[1]][cur[2]][cur[3]] + (next[0] - next[2]) * (next[0] - next[2]) + (next[1] - next[3]) * (next[1] - next[3]);
            if (min[next[0]][next[1]][next[2]][next[3]] == 0) {
                min[next[0]][next[1]][next[2]][next[3]] = dist;
            }
            else {
                if (dist < min[next[0]][next[1]][next[2]][next[3]]) {
                    min[next[0]][next[1]][next[2]][next[3]] = dist;
                }
            }

            helper(min, next, fPath, bPath, remF + 1, remB);
        }
        if (remB < bPath.length) {
            int temp = bPath[remB];
            int[] next = cur.clone();

            if (temp == 0) {
                next[3]++;
            }
            else if (temp == 1) {
                next[2]++;
            }
            else if (temp == 2) {
                next[3]--;
            }
            else if (temp == 3) {
                next[2]--;
            }

            long dist = min[cur[0]][cur[1]][cur[2]][cur[3]] + (next[0] - next[2]) * (next[0] - next[2]) + (next[1] - next[3]) * (next[1] - next[3]);
            if (min[next[0]][next[1]][next[2]][next[3]] == 0) {
                min[next[0]][next[1]][next[2]][next[3]] = dist;
            }
            else {
                if (dist < min[next[0]][next[1]][next[2]][next[3]]) {
                    min[next[0]][next[1]][next[2]][next[3]] = dist;
                }
            }

            helper(min, next, fPath, bPath, remF, remB + 1);
        }
        if (remF < fPath.length && remB < bPath.length) {
            int temp1 = fPath[remF];
            int temp2 = bPath[remB];
            int[] next = cur.clone();

            if (temp1 == 0) {
                next[1]++;
            }
            else if (temp1 == 1) {
                next[0]++;
            }
            else if (temp1 == 2) {
                next[1]--;
            }
            else if (temp1 == 3) {
                next[0]--;
            }
            if (temp2 == 0) {
                next[3]++;
            }
            else if (temp2 == 1) {
                next[2]++;
            }
            else if (temp2 == 2) {
                next[3]--;
            }
            else if (temp2 == 3) {
                next[2]--;
            }

            long dist = min[cur[0]][cur[1]][cur[2]][cur[3]] + (next[0] - next[2]) * (next[0] - next[2]) + (next[1] - next[3]) * (next[1] - next[3]);
            if (min[next[0]][next[1]][next[2]][next[3]] == 0) {
                min[next[0]][next[1]][next[2]][next[3]] = dist;
            }
            else {
                if (dist < min[next[0]][next[1]][next[2]][next[3]]) {
                    min[next[0]][next[1]][next[2]][next[3]] = dist;
                }
            }

            helper(min, next, fPath, bPath, remF + 1, remB + 1);
        }
    }
}
