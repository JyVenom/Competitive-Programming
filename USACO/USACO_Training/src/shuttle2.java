/*
ID: jerryya2
LANG: JAVA
TASK: shuttle
*/

import java.io.*;

public class shuttle2 {
    private static int stepCount;
    private static int targetPos;
    private static int lastDir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shuttle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));

        int n = Integer.parseInt(br.readLine());
        int len = n + n;
        int len2 = len - 1;
        int[] map = new int[len];
        for (int i = 0; i < n; i++) {
            map[i] = 2;
            map[len2 - i] = 1;
        }
        stepCount = 0;
        move(-1, pw, map, n);
        while (true) {
            if (!moveNext(pw, map, n, len, len2, n)) {
                break;
            }
        }
        pw.println();
    }

    private static boolean isMin(int newPos, int newStep, int minStep, int pos) {
        if (newStep < minStep)
            return true;
        return lastDir * (newPos - pos) > 0;
    }

    private static boolean moveNext(PrintWriter pw, int[] map, int n, int len, int len2, int pos) {
        int minSwap = -1;
        int minStep = len;
        int minTarget = 0;
        for (int i = 0; i < len2; i++) {
            if (map[i] > map[i + 1]) {
                int step = calcStep(pw, map, i, pos);
                if (isMin(targetPos, step, minStep, pos)) {
                    minSwap = i;
                    minStep = step;
                    minTarget = targetPos;
                    if (step == 0)
                        break;
                }
            }
        }
        if (minSwap < 0) {
            moveMore(pw, map, true, n, pos);
            return false;
        }
        moveMore(pw, map, true, minTarget, pos);
        if (minTarget == minSwap)
            move(2, pw, map, pos);
        else {
            assert minTarget == minSwap + 2;
            move(-2, pw, map, pos);
        }
        return true;
    }

    private static int calcStep(PrintWriter pw, int[] map, int i, int pos) {
        if (pos == i) {
            targetPos = i;
            return 0;
        }
        if (pos == i + 1) {
            if (lastDir > 0)
                targetPos = i + 2;
            else
                targetPos = i;
            return 1;
        }
        if (pos == i + 2) {
            targetPos = i + 2;
            return 0;
        }
        int s1 = moveMore(pw, map, false, i, pos);
        int s2 = moveMore(pw, map, false, i + 2, pos);
        if (s2 < s1) {
            targetPos = i + 2;
            s1 = s2;
        } else
            targetPos = i;
        return s1;
    }

    private static int moveMore(PrintWriter pw, int[] map, boolean move, int newPos, int pos) {
        if (newPos == pos)
            return 0;
        int count = 0;
        int p = pos;
        if (newPos > pos) //move right
        {
            while (newPos > p) {
                if (newPos > p + 1 && map[p] == map[p + 1]) {
                    p += 2;
                    if (move) move(2, pw, map, pos);
                } else {
                    p += 1;
                    if (move) move(1, pw, map, pos);
                }
                count++;
            }
        } else {
            while (newPos < p) {
                if (newPos < p - 1 && map[p - 1] == map[p - 2]) {
                    p -= 2;
                    if (move) move(-2, pw, map, pos);
                } else {
                    p -= 1;
                    if (move) move(-1, pw, map, pos);
                }
                count++;
            }
        }
        return count;
    }

    private static void swap(int[] map, int i, int j) {
        if (i < 0)
            throw new Error();
        int v = map[i];
        map[i] = map[j];
        map[j] = v;
    }

    private static void move(int dir, PrintWriter pw, int[] map, int pos) {
        lastDir = dir;
        if (dir == -2)
            swap(map, pos - 2, pos - 1);
        else if (dir == 2)
            swap(map, pos, pos + 1);
        pos += dir;
        if (stepCount % 20 == 0) {
            if (stepCount > 0)
                pw.println();
        } else
            pw.print(" ");
        pw.print((1 + pos));
        stepCount++;
    }
}
