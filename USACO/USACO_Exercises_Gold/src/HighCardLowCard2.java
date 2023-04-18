//3N + log(n) + 2 * log(n/2) + n^2 / 2 + 2 * n^2 / 4
//2 <= N <= 50,000
//150000 + 15 + 30 + 1250000000 + 625000000 = 1875150045

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HighCardLowCard2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));

        int n = Integer.parseInt(br.readLine());
        int N = n / 2;
        boolean[] used = new boolean[(2 * n) + 1];
        ArrayList<Integer> b = new ArrayList<>();
        ArrayList<Integer> e1 = new ArrayList<>();
        ArrayList<Integer> e2 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            used[num] = true;
            e1.add(num);
        }
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            used[num] = true;
            e2.add(num);
        }
        for (int i = 1; i < used.length; i++) {
            if (!used[i]) {
                b.add(i);
            }
        }

        int ans = 0;
        Collections.sort(b);
        Collections.sort(e1);
        Collections.sort(e2);
        Collections.reverse(e2);
        ArrayList<Integer> b1 = new ArrayList<>(b.subList(N, n));
        ArrayList<Integer> b2 = new ArrayList<>(b.subList(0, N));
        Collections.reverse(b2);
        int start = 0;
        for (int i = 0; i < N; i++) {
            int cur = e1.get(i);
            for (int j = start; j < N; j++) {
                if (b1.get(j) > cur) {
                    ans++;
                    start = j + 1;
                    break;
                }
            }
        }
        start = 0;
        for (int i = 0; i < N; i++) {
            int cur = e2.get(i);
            for (int j = start; j < N; j++) {
                if (b2.get(j) < cur) {
                    ans++;
                    start = j + 1;
                    break;
                }
            }
        }

        pw.println(ans);
        pw.close();
    }
}
