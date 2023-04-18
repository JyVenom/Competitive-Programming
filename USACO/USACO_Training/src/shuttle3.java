import java.io.*;

public class shuttle3 {
    private static int[] map;
    private static int n;
    private static int len;
    private static int len2;
    private static int pos;
    private static int stepCount;
    private static int targetPos;
    private static int lastDir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shuttle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.pw")));

        n = Integer.parseInt(br.readLine());
        len = n + n;
        len2 = len - 1;
        map = new int[len];
        for (int i = 0; i < n; i++) {
            map[i] = 2;
            map[len2 - i] = 1;
        }
        stepCount = 0;
        pos = n;
        move(pw, -1);
        while (true) {
            if (!moveNext(pw)) {
                break;
            }
        }
        pw.println();
    }

    private static boolean isMin(int newPos, int newStep, int minStep) {
        if (newStep < minStep)
            return true;
        return lastDir * (newPos - pos) > 0;
    }

    private static boolean moveNext(PrintWriter pw) {
        int minSwap = -1;
        int minStep = len;
        int minTarget = 0;
        for (int i = 0; i < len2; i++) {
            if (map[i] > map[i + 1]) {
                int step = calcStep(pw, i);
                if (isMin(targetPos, step, minStep)) {
                    minSwap = i;
                    minStep = step;
                    minTarget = targetPos;
                    if (step == 0)
                        break;
                }
            }
        }
        if (minSwap < 0) {
            moveMore(pw, n, true);
            return false;
        }
        moveMore(pw, minTarget, true);
        if (minTarget == minSwap)
            move(pw, 2);
        else {
            assert minTarget == minSwap + 2;
            move(pw, -2);
        }
        return true;
    }

    private static int calcStep(PrintWriter pw, int i) {
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
        int s1 = moveMore(pw, i, false);
        int s2 = moveMore(pw, i + 2, false);
        if (s2 < s1) {
            targetPos = i + 2;
            s1 = s2;
        } else
            targetPos = i;
        return s1;
    }

    private static int moveMore(PrintWriter pw, int newPos, boolean move) {
        if (newPos == pos)
            return 0;
        int count = 0;
        int p = pos;
        if (newPos > pos) //move right   
        {
            while (newPos > p) {
                if (newPos > p + 1 && map[p] == map[p + 1]) {
                    p += 2;
                    if (move) move(pw, 2);
                } else {
                    p += 1;
                    if (move) move(pw, 1);
                }
                count++;
            }
        } else {
            while (newPos < p) {
                if (newPos < p - 1 && map[p - 1] == map[p - 2]) {
                    p -= 2;
                    if (move) move(pw, -2);
                } else {
                    p -= 1;
                    if (move) move(pw, -1);
                }
                count++;
            }
        }
        return count;
    }

    private static void swap(int i, int j) {
        if (i < 0)
            throw new Error();
        int v = map[i];
        map[i] = map[j];
        map[j] = v;
    }

    private static void move(PrintWriter pw, int dir) {
        lastDir = dir;
        if (dir == -2)
            swap(pos - 2, pos - 1);
        else if (dir == 2)
            swap(pos, pos + 1);
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

 