import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MilkPumping2 {
    private static final int[][] dp = new int[1001][1001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("pump.in"));
        PrintWriter pw = new PrintWriter(new File("pump.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dp[1][1000] = 0;
        ArrayList<ArrayList<Integer>>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());

            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(b);
            temp.add(f);
            temp.add(c);
            edges[a].add(temp);
            ArrayList<Integer> temp2 = new ArrayList<>();
            temp2.add(a);
            temp2.add(f);
            temp2.add(c);
            edges[b].add(temp2);

        }
        br.close();

        PriorityQueue<ArrayList<Integer>> q = new PriorityQueue<>();
        ArrayList<Integer> init = new ArrayList<>();
        init.add(0);
        init.add(1);
        init.add(1000);
        q.add(init);
        double ans = -1;
        while (q.size() != 0){
            ArrayList<Integer> curr = q.peek();
            q.poll();
            if (curr.get(1) == n) {
                ans = Math.max(ans, curr.get(2) / (double)dp[curr.get(1)][curr.get(2)]);
                continue;
            }
            for (ArrayList<Integer> out : edges[curr.get(1)]){
                int nf = Math.min(out.get(1), curr.get(2));
                int nc = dp[curr.get(1)][curr.get(2)] + out.get(2);
                int nd = out.get(0);
                if (nc < dp[nd][nf]) {
                    dp[nd][nf] = nc;
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(-dp[nd][nf]);
                    temp.add(nd);
                    temp.add(nf);
                    q.add(temp);
                }
            }
        }

        pw.println((int)(1000000 * ans));
        pw.close();
    }
}
