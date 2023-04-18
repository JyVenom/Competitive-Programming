/*
ID: jerryya2
LANG: JAVA
TASK: money
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class money2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("money.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        HashSet<Integer> temp = new HashSet<>();
        int rem = v;
        while (rem > 0) {
            st = new StringTokenizer(br.readLine());
            int tok = st.countTokens();
            rem -= tok;
            for (int i = 0; i < tok; i++) {
                temp.add(Integer.parseInt(st.nextToken()));
            }
        }
        ArrayList<Integer> temp2 = new ArrayList<>(temp);
        Collections.sort(temp2);
        for (int i = 0; i < temp2.size(); i++) {
            if (temp2.get(i) > n) {
                temp2.remove(i);
                i--;
            }
        }
        int num = temp2.size();
        int[] coins = new int[num];
        for (int i = 0; i < num; i++) {
            coins[i] = temp2.get(i);
        }
        v = num;

        pw.println(makeAll(0, 0, v, n, 0, coins));
        pw.close();
    }

    private static int makeAll(int coin, int sum, int v, int n, int pos, int[] coins) {
        int extra = 0;
        int val = coins[coin];
        while (sum + extra <= n) {
            if (sum + extra == n) {
                pos++;
                break;
            }
            if (coin < v - 1) {
                pos = makeAll(coin + 1, sum + extra, v, n, pos, coins);
            }
            else {
                if ((n - sum) % val == 0) {
                    pos++;
                }
                break;
            }
            extra += val;
        }
        return pos;
    }
}
