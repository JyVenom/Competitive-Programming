/*
ID: jerryya2
LANG: JAVA
PROG: milk3
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class milk3 {
    private static int A;
    private static int B;
    private static int C;
    private static final HashSet<Integer> pos = new HashSet<>();
    private static boolean[][][] visited;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        br.close();
//        if (A > B) {
//            int temp = A;
//            A = B;
//            B = temp;
//        }
        visited = new boolean[A + 1][B + 1][C + 1];

        dfs(0, 0, C);
        ArrayList<Integer> ans = new ArrayList<>(pos);
        Collections.sort(ans);

        for (int i = 0; i < ans.size() - 1; i++) {
            pw.print(ans.get(i) + " ");
        }
        pw.println(ans.get(ans.size() - 1));
        pw.close();
    }

    private static void dfs (int a, int b, int c) {
        if (visited[a][b][c]) {
            return;
        }

        visited[a][b][c] = true;
        if (a == 0) {
            pos.add(c);
        }

        int min = Math.min(B - b, a);
        dfs(a - min, b + min, c);
        min = Math.min(A - a, b);
        dfs(a + min, b - min, c);
        min = Math.min(C - c, b);
        dfs(a, b - min, c + min);
        min = Math.min(B - b, c);
        dfs(a, b + min, c - min);
        min = Math.min(C - c, a);
        dfs(a - min, b, c + min);
        min = Math.min(A - a, c);
        dfs(a + min, b, c - min);
    }
}
