import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ClockTree2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));

        int n = Integer.parseInt(br.readLine());
        int[] rooms = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            rooms[i] = Integer.parseInt(st.nextToken());
        }
        int N = n - 1;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int[] dist = bfs(edges, n, i);
            int temp = 0;
            for (int j = 0; j < n; j++) {
                if (dist[j] % 2 == 0) {
                    temp += rooms[j];
                } else {
                    temp -= rooms[j];
                }
            }
            int temp2 = ((temp % 12) + 12) % 12;
            if (temp2 == 0 || temp2 == 1) {
                count++;
            }
        }

        pw.println(count);
        pw.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int[] bfs(ArrayList<ArrayList<Integer>> edges, int n, int init) {
        LinkedList<Integer> queue = new LinkedList<>();
        int[] dist = new int[n];
        queue.offer(init);

        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();

            int newDist = dist[cur] + 1;
            for (int next : edges.get(cur)) {
                if (next != cur) {
                    if (dist[next] == 0) {
                        dist[next] = newDist;
                        queue.offer(next);
                    }
                }
            }

        }
        return dist;
    }
}
