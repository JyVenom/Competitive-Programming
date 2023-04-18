import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CowChecklist4 {
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
            h[i][0] = Long.parseLong(st.nextToken());
            h[i][1] = Long.parseLong(st.nextToken());
        }
        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(br.readLine());
            g[i][0] = Long.parseLong(st.nextToken());
            g[i][1] = Long.parseLong(st.nextToken());
        }

        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            order.add(i);
        }
//        ArrayList<ArrayList<Long>> dist = new ArrayList<>();
//        dist.add(new ArrayList<>());
//        dist.get(0).add(0L);
//        for (int i = H - 2; i >= 0; i--) {
//            long cur = findDist(h[i], h[i + 1]);
//            ArrayList<Long> copy = new ArrayList<>(dist.get(0));
//            for (int j = 0; j < copy.size(); j++) {
//                copy.set(j, copy.get(j) + cur);
//            }
//            copy.add(0, 0L);
//            dist.add(0, copy);
//        }
        int start = 1;
        long min = -1;
        int minIndex;
        for (int i = 0; i < G; i++) {
            int cur = i + H;
            min = Long.MAX_VALUE;
            minIndex = 0;
            for (int j = start; j < order.size(); j++) {
                ArrayList<Integer> copy = new ArrayList<>(order);
                copy.add(j, cur);
                long temp = findDist(copy, h, g);
                if (temp < min) {
                    min = temp;
                    minIndex = j;
                }
            }
            order.add(minIndex, cur);
            start = minIndex + 1;
        }

        pw.println(min);
        pw.close();
    }

    private static long findDist(long[] from, long[] to) {
        return (to[0] - from[0]) * (to[0] - from[0]) + (to[1] - from[1]) * (to[1] - from[1]);
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
