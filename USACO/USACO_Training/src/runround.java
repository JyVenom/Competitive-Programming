/*
ID: jerryya2
LANG: JAVA
TASK: runround
*/

import java.io.*;

public class runround {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("runround.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));

        long M = Long.parseLong(br.readLine());
        br.close();

        long cur = M + 1;
        while (!isRunround(cur)) {
            cur++;
        }
        pw.println(cur);
        pw.close();
    }

    private static boolean isRunround (long num) {
        String n = Long.toString(num);
        int len = n.length();
        boolean[] used = new boolean[len];
        boolean[] usedNum = new boolean[10];
        int at = 0;
        for (int i = 0; i < len; i++) {
            if (used[at]) {
                return false;
            }
            used[at] = true;
            int amount = Character.getNumericValue(n.charAt(at));
            if (usedNum[amount]) {
                return false;
            }
            usedNum[amount] = true;
            at = (at + amount) % len;
        }
        return at == 0;
    }
}