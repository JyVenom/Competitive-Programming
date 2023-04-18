import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ClockTree {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
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
        System.out.println(System.currentTimeMillis() - start);

        int[] dist = bfs(edges, n);
        long sumEven = 0;
        long sumOdd = 0;
        int numEven = 0;
        int numOdd = 0;
        for (int i = 0; i < n; i++) {
            if ((dist[i] % 2) != 0) {
                sumOdd += rooms[i];
                numOdd++;
            } else {
                sumEven += rooms[i];
                numEven++;
            }
        }

        if ((sumEven % 12) == (sumOdd % 12)) {
            pw.println(n);
        } else if ((sumEven + 1) % 12 == (sumOdd % 12)) {
            pw.println(numOdd);
        } else if ((sumEven % 12) == ((sumOdd + 1) % 12)) {
            pw.println(numEven);
        } else {
            pw.println(0);
        }
        pw.close();
    }

    private static int[] bfs(ArrayList<ArrayList<Integer>> edges, int n) {
        LinkedList<Integer> queue = new LinkedList<>();
        int[] dist = new int[n];
        queue.offer(0);
        boolean[] visited = new boolean[n];

        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();

            int newDist = dist[cur] + 1;
            for (int next : edges.get(cur)) {
                if (next != cur) {
                    if (dist[next] == 0) {
                        if (!visited[next]) {
                            visited[next] = true;
                            dist[next] = newDist;
                            queue.offer(next);
                        }
                    }
                }
            }

        }
        return dist;
    }
}
