/*
ID: jerryya2
LANG: JAVA
TASK: stamps
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class stamps2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("stamps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int at = 0;
        int[] values = new int[n];
        int last = n - 1;
        while (values[last] == 0) {
            st = new StringTokenizer(br.readLine());
            int tok = st.countTokens();
            for (int i = 0; i < tok; i++) {
                values[i + at] = Integer.parseInt(st.nextToken());
            }
            at += tok;
        }
        br.close();
        Arrays.sort(values);

        HashSet<Integer> list = new HashSet<>();
        addAll(list, values, k, 0, 0, 0);
        ArrayList<Integer> list1 = new ArrayList<>(list);
        int max = k * values[last] + 1;
        int[] all = new int[max];
        for (int i = 1; i < list1.size(); i++) {
            all[list1.get(i)] = 1;
        }
        int ans = 0;
        for (int i = 1; i < max; i++) {
            if (all[i] == 1) {
                int res = 1;
                boolean good = true;
                for (int j = i + 1; j < max; j++) {
                    if (all[j] == 0) {
                        i = j - 1;
                        good = false;
                        break;
                    }
                    else {
                        res++;
                    }
                }
                if (res > ans) {
                    ans = res;
                }
                if (!good) {
                    i = max - 1;
                }
            }
        }

        pw.println(ans);
        pw.close();
    }

    private static void addAll (HashSet<Integer> list, int[] values, int k, int at, int count, int sum) {
        int max = k - count;
        for (int i = 0; i <= max; i++) {
            if (at == values.length - 1) {
                list.add(sum + i * values[at]);
            }
            else {
                addAll(list, values, k, at + 1, count + i, sum + i * values[at]);
            }
        }
    }
}


//    int [] v = new int[N];
//    int maxv = -1;
//        for(int i = 0; i < N; i++) {
//        int val = in.nextInt();
//        v[i] = val; maxv = Math.max(maxv, val);
//        }
//
//        int [] dp = new int[K * maxv + 2];
//        Arrays.fill(dp, 1000000000);
//        dp[0] = 1;
//
//        for(int i = 1; i <= K * maxv + 1; i++) {
//        for(int j = 0; j < N; j++) {
//        if(v[j] <= i && dp[i - v[j]] > 0) {
//        dp[i] = Math.min(dp[i], dp[i - v[j]] + 1);
//        }
//        }
//
//        if(dp[i] > K + 1) {
//        pw.println(i - 1);
//        break;
//        }
//        }