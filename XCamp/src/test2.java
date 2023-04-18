import java.util.PriorityQueue;

public class test2 {
    public static void main(String[] args) {
        PriorityQueue<edge> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o2.rate, o1.rate));
        pq.add(new edge(0,1,1));
        pq.add(new edge(0,6,1));
        pq.add(new edge(0,2,1));
        pq.add(new edge(0,5,1));
        pq.add(new edge(0,3,1));
        pq.add(new edge(0,4,1));
        while (!pq.isEmpty()) {
            System.out.println(pq.poll().rate);
        }
    }

    private static class edge{
        int to, cost, flow;
        double rate;

        public edge(int to, int cost, int flow) {
            this.to = to;
            this.cost = cost;
            this.flow = flow;
            rate = (double) flow/(double) cost;
        }
    }
}
