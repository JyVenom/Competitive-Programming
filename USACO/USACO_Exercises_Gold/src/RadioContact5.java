import java.io.*;
import java.util.*;

public class RadioContact5 {
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
        ArrayList<long[]> list = new ArrayList<>();
        long ans = helper(new int[]{f[0], f[1], b[0], b[1]}, fPath, bPath, endF, endB, 0, 0, 0, 0, list);


//        pw.println(min[endF[0]][endF[1]][endB[0]][endB[1]]);
        pw.println(ans);
        pw.close();
    }

    private static long helper (int[] cur, int[] fPath, int[] bPath, int[] endF, int[] endB, int remF, int remB, long curVal, long ans, ArrayList<long[]> list) {
        if (ans != 0) {
            return ans;
        }
        if (cur[0] == endF[0] && cur[1] == endF[1] && cur[2] == endB[0] && cur[3] == endB[1]) {
            ans = curVal;
            if (curVal == 28) {
                System.out.println();
            }
        }

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

            long newVal = curVal + (next[0] - next[2]) * (next[0] - next[2]) + (next[1] - next[3]) * (next[1] - next[3]);

            list.add(new long[]{next[0], next[1], remF + 1, remB, newVal});
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

            long newVal = curVal + (next[0] - next[2]) * (next[0] - next[2]) + (next[1] - next[3]) * (next[1] - next[3]);

            list.add(new long[]{next[0], next[1], remF, remB + 1, newVal});
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

            long newVal = curVal + (next[0] - next[2]) * (next[0] - next[2]) + (next[1] - next[3]) * (next[1] - next[3]);

            list.add(new long[]{next[0], next[1], remF + 1, remB + 1, newVal});
        }

        list.sort(Comparator.comparingLong(a -> a[4]));
        long[] temp = list.get(0);
        list.remove(0);
        helper(new int[]{(int) temp[0], (int) temp[1]}, fPath, bPath, endF, endB, (int) temp[2], (int) temp[3],temp[4], ans, list);
        return ans;
    }
}
