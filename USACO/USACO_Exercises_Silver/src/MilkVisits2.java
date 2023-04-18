import java.io.*;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MilkVisits2 {
    private static int num;
    private static int[] comp;
    private static ArrayList<Integer>[] adj;
    private static char[] col;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String breeds = br.readLine();
        comp = new int[n + 1];
        col = new char[n + 1];
        adj = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++){
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n + 1; ++i){
            col[i] = breeds.charAt(i-1);
        }
        for (int i = 0; i < n - 1; ++i){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        for (int i = 1; i < n + 1; ++i){
            if (comp[i] == 0) {
                num++;
                dfs(i);
            }
        }
        for (int i = 0; i < m; ++i){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            if (col[a] == c || comp[a] != comp[b]) {
                pw.print(1);
            }
            else {
                pw.print(0);
            }
        }
        pw.close();
    }

    private static void dfs(int x) {
        if (comp[x] != 0) {
            return;
        }
        comp[x] = num;
        for (int i = 0; i < adj[x].size(); i++){
            int t = adj[x].get(i);
            if (col[t] == col[x]) {
                dfs(t);
            }
        }
    }
}