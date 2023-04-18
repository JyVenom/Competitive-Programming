/*
ID: jerryya2
LANG: JAVA
TASK: nocows
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class nocows2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("nocows.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        br.close();

        int max = (int) Math.pow(2, k - 1);
        int[][] tree = new int[k][max];
        tree[0][0] = 1;
        tree[1][0] = 1;
        tree[1][1] = 1;

        boolean[][] visited = new boolean[k][max];
        int count = all(tree, 1, 0, n - 3, visited, k);

        pw.println(count);
        pw.close();
    }

    private static int all (int[][] tree, int level, int count, int rem, boolean[][] visited, int k) {
        int max = (int) Math.pow(2, level);
        for (int i = 0; i < max; i++) {
            if (i > 0 && !visited[level][i - 1]) {
                visited[level][i] = true;
                continue;
            }
            else if (visited[level][i]) {
                continue;
            }
            else if (tree[level][i] == 0) {
                visited[level][i] = true;
                continue;
            }
            visited[level][i] = true;
            if (i < max - 1) {
                count = all(tree, level, count, rem, visited, k);
            }
            else {
                if (level < k - 1) {
                    count = all(tree, level + 1, count, rem, visited, k);
                    Arrays.fill(visited[level + 1], false);
                }
            }
            if (level < k - 1) {
                rem -= 2;
                tree[level + 1][i * 2] = 1;
                tree[level + 1][(i * 2) + 1] = 1;
                if (rem == 0) {
                    count += 1;
                    System.out.println(Arrays.deepToString(tree));
                    System.out.println();
                } else {
                    if (i < max - 1) {
                        count = all(tree, level, count, rem, visited, k);
                    }
                    else {
                        count = all(tree, level + 1, count, rem, visited, k);
                        Arrays.fill(visited[level + 1], false);
                    }
                }
                tree[level + 1][i * 2] = 0;
                tree[level + 1][(i * 2) + 1] = 0;
                rem += 2;
                visited[level][i] = false;
            }
        }
        return count;
    }
}
