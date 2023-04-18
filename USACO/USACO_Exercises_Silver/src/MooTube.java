import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MooTube {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<int[]>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            edges.get(a).add(new int[]{b, c});
            edges.get(b).add(new int[]{a, c});
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()) - 1;

            pw.println(BFS(edges, v, n, k));
        }

        pw.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int BFS(ArrayList<ArrayList<int[]>> edges, int init, int n, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(init);
        boolean[] visited = new boolean[n];
        int count = 0;

        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();
            visited[cur] = true;

            for (int[] next : edges.get(cur)) {
                if (!visited[next[0]]) {
                    if (next[1] >= k) {
                        count++;
                        queue.offer(next[0]);
                    }
                }
            }

        }
        return count;
    }
}
