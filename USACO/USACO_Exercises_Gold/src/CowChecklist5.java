import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CowChecklist5 {
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
        ArrayList<Long> dist = new ArrayList<>();
        for (int i = 1; i < H; i++) {
            dist.add(findDist(h[i], h[i - 1]));
        }
        int start = 1;
        long min = -1;
        int minIndex;
        for (int i = 0; i < G; i++) {
            int cur = i + H;
            min = Long.MAX_VALUE;
            minIndex = 0;
            ArrayList<Long> best = new ArrayList<>(dist);
            for (int j = start; j < order.size(); j++) {
                ArrayList<Long> copy = new ArrayList<>(dist);
                int prev = order.get(j - 1);
                int next = order.get(j);
                if (prev < H) {
                    copy.set(j - 1, findDist(h[prev], g[i]));
                }
                else {
                    copy.set(j - 1, findDist(g[prev - H], g[i]));
                }
                if (next < H) {
                    copy.add(j, findDist(g[i], h[next]));
                }
                else {
                    copy.set(j, findDist(g[i], g[next - H]));
                }
                long temp = findSum(copy);
                if (temp < min) {
                    min = temp;
                    minIndex = j;
                    best = new ArrayList<>(copy);
                }
            }
            order.add(minIndex, cur);
            start = minIndex + 1;
            dist = new ArrayList<>(best);
        }

        pw.println(min);
        pw.close();
    }

    private static long findDist(long[] from, long[] to) {
        return (to[0] - from[0]) * (to[0] - from[0]) + (to[1] - from[1]) * (to[1] - from[1]);
    }

    private static long findSum(ArrayList<Long> dist) {
        long sum = 0;
        for (Long l : dist) {
            sum += l;
        }
        return sum;
    }
}
