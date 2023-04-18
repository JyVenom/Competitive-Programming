import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CowChecklist7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("checklist.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        long[][] h = new long[H][2];
        long[][] g = new long[G][2];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            h[i][0] = Integer.parseInt(st.nextToken());
            h[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(br.readLine());
            g[i][0] = Integer.parseInt(st.nextToken());
            g[i][1] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> order = new ArrayList<>();
        order.add(0);
        long min = tryAll(h, g, 1, 0, order, H, G, Long.MAX_VALUE);

        pw.println(min);
        pw.close();
    }

    private static long findDist(long[] from, long[] to) {
        return ((to[0] - from[0]) * (to[0] - from[0])) + ((to[1] - from[1]) * (to[1] - from[1]));
    }

    private static long tryAll (long[][] h, long[][] g, int a, int b, ArrayList<Integer> order, int H, int G, long min) {
        if (a == H - 1 && b == G) {
            order.add(a);
            long cur = findDist(order, h, g);
            if (cur < min) {
                min = cur;
                System.out.println(order);
            }
            return min;
        }
        if (a < H - 1) {
            ArrayList<Integer> copyA = new ArrayList<>(order);
            copyA.add(a);
            min = tryAll(h, g, a + 1, b, copyA, H, G, min);
        }
        if (b < G) {
            ArrayList<Integer> copyB = new ArrayList<>(order);
            copyB.add(b + H);
            min = tryAll(h, g, a, b + 1, copyB, H, G, min);
        }
        return min;
    }

    private static long findDist(ArrayList<Integer> order, long[][] h, long[][] g) {
        long sum = 0;
        for (int i = 0; i < order.size() - 1; i++) {
            int cur = order.get(i);
            int next = order.get(i + 1);
            if (cur < h.length) {
                if (next < h.length) {
                    sum += findDist(h[cur], h[next]);
                }
                else {
                    sum += findDist(h[cur], g[next - h.length]);
                }
            }
            else {
                if (next < h.length) {
                    sum += findDist(g[cur - h.length], h[next]);
                }
                else {
                    sum += findDist(g[cur - h.length], g[next - h.length]);
                }
            }
        }
        return sum;
    }
}
