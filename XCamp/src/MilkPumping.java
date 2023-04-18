import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MilkPumping {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<edge>> edges = new ArrayList<>();
        for (int i = -1; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            edges.get(a).add(new edge(b, c, f));
            edges.get(b).add(new edge(a, c, f));
        }

        PriorityQueue<edge> q = new PriorityQueue<>((o1, o2) -> Double.compare(o2.rate, o1.rate));
        q.add(new edge(1, 0, Integer.MAX_VALUE));
        assert q.peek() != null;
        q.peek().rate = Double.POSITIVE_INFINITY;
        boolean[] visited = new boolean[n + 1];
        while (!q.isEmpty()) {
            edge cur = q.poll();

            visited[cur.to] = true;

            if (cur.to == n) {
                pw.println((int) (cur.rate * 1000000));
                break;
            }

            for (edge next : edges.get(cur.to)) {
                if (!visited[next.to]) {
                    q.add(new edge(next.to, cur.cost + next.cost, Math.min(cur.flow, next.flow)));
                }
            }
        }

        pw.close();
    }

    private static class edge {
        int to, cost, flow;
        double rate;

        public edge(int to, int cost, int flow) {
            this.to = to;
            this.cost = cost;
            this.flow = flow;
            rate = (double) flow / (double) cost;
        }
    }
}
