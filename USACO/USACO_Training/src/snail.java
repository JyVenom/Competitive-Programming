/*
ID: jerryya2
LANG: JAVA
TASK: snail
*/

//4n^2
//n <= 120
//57600

import java.io.*;

public class snail {
    static int N, N1;
    static boolean[][] map;
    static boolean[][] bars;
    static int max = 0;
    static boolean[] zeros;

    public static void main(String[] args) throws IOException {
//        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("snail.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snail.out")));

        String[] params = br.readLine().split(" ");
        N = Integer.parseInt(params[0]);
        N1 = N - 1;
        int M = Integer.parseInt(params[1]);
        map = new boolean[N][N];
        bars = new boolean[N][N];
        zeros = new boolean[N];
        for (int i = 0; i < M; i++) {
            String pos = br.readLine();
            int col = pos.charAt(0) - 'A';
            int row = Integer.parseInt(pos.substring(1)) - 1;
            map[row][col] = bars[row][col] = true;
        }

        map[0][0] = true;
        goRight(0, 0, 1);
        goDown(0, 0, 1);

        pw.println(max);
        pw.close();
//        System.out.println(System.currentTimeMillis() - start);
    }

    static void goRight(int row, int col, int steps) {
        boolean[] ar = map[row];
        int k = col + 1;
        for (; k < N && !ar[k]; k++) {
            ar[k] = true;
        }
        boolean x = check(row, k--);
        steps += k - col;
        if (x && k != col) {
            if (row > 0) goUp(row, k, steps);
            if (row < N1) goDown(row, k, steps);
        } else {
            updateSteps(steps);
        }
        System.arraycopy(zeros, 0, ar, col + 1, k - col);
    }

    static void goDown(int row, int col, int steps) {
        boolean[][] map = snail.map;
        int k = row + 1;
        for (; k < N && !map[k][col]; k++) {
            map[k][col] = true;
        }
        boolean x = check(k--, col);
        steps += k - row;
        if (x && k != row) {
            if (col > 0) goLeft(k, col, steps);
            if (col < N1) goRight(k, col, steps);
        } else {
            updateSteps(steps);
        }
        for (int i = row + 1; i <= k; i++) {
            map[i][col] = false;
        }
    }

    static void goLeft(int row, int col, int steps) {
        boolean[] ar = map[row];
        int k = col - 1;
        for (; k >= 0 && !ar[k]; k--) {
            ar[k] = true;
        }
        boolean x = check(row, k++);
        steps += col - k;
        if (x && k != col) {
            if (row > 0) goUp(row, k, steps);
            if (row < N1) goDown(row, k, steps);
        } else {
            updateSteps(steps);
        }
        System.arraycopy(zeros, 0, ar, k, col - k);
    }

    static void goUp(int row, int col, int steps) {
        boolean[][] map = snail.map;
        int k = row - 1;
        for (; k >= 0 && !map[k][col]; k--) {
            map[k][col] = true;
        }
        boolean x = check(k++, col);
        steps += row - k;
        if (x && k != row) {
            if (col > 0) goLeft(k, col, steps);
            if (col < N1) goRight(k, col, steps);
        } else {
            updateSteps(steps);
        }
        for (int i = row - 1; i >= k; i--) {
            map[i][col] = false;
        }
    }

    static boolean check(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return true;
        }
        return bars[row][col];
    }

    static void updateSteps(int steps) {
        if (steps > max) {
            max = steps;
        }
    }
}