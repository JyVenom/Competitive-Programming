/*
ID: jerryya2
LANG: JAVA
TASK: money
*/

import java.io.*;
import java.util.*;

public class money {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("money.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        HashSet<Integer> temp = new HashSet<>(); //To remove repeats of the same value
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

        long[][] table = new long[v + 1][n + 1];
        Arrays.fill(table[0], 0);
        for (int i = 1; i <= v; i++) {
            table[i] = table[i - 1].clone();
            long[] row = table[i];
            row[0] = 1;
            int cur = coins[i - 1];
            for (int j = cur; j <= n; j++) {
                row[j] += row[j - cur];
            }
        }

        pw.println(table[v][n]);
        pw.close();
    }
}
