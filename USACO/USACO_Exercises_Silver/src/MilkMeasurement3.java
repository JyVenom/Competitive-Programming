import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MilkMeasurement3 {
    public static int[] cowID;
    public static int[] delta;
    public static HashMap<Integer, Integer> cows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
//        st.nextToken();
        cowID = new int[1000001];
        delta = new int[1000001];
        cows = new HashMap<>();
        int ID = 1;
        for (int i = 0; i < n; i++) {
            // Store the cow in the appropriate day.
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            cowID[day] = Integer.parseInt(st.nextToken());

            // Add to our list if we've never seen this cow.
            if (!cows.containsKey(cowID[day])) cows.put(cowID[day], ID++);

            delta[day] = Integer.parseInt(st.nextToken());
        }
        br.close();

        // Remap cows.
        for (int i = 0; i < cowID.length; i++)
            if (cowID[i] != 0)
                cowID[i] = cows.get(cowID[i]);
        int[] milk = new int[ID];
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(0, ID);
        int res = 0, max = 0;
        // Go through each day.
        for (int i = 0; i < cowID.length; i++) {
            if (cowID[i] == 0) continue;

            // Get previous and current milk.
            int prev = milk[cowID[i]];
            int cur = prev + delta[i];
            milk[cowID[i]] = cur;

            // Change Tree Map - remove old value.
            int numOld = tm.get(prev);
            if (numOld == 1) {
                tm.remove(prev);
            } else
                tm.put(prev, numOld - 1);

            // Place new value.
            if (tm.containsKey(cur))
                tm.put(cur, tm.get(cur) + 1);
            else
                tm.put(cur, 1);

            // Wasn't best now I am.
            if (prev < max && cur >= max) res++;

            // Become unique best, after being in a tie.
            if (prev == max && numOld > 1 && cur > max) res++;

            int newTop = tm.lastKey();

            // Was best now I am not.
            if (prev == max && cur < newTop) res++;

            // Was best, still am best, but now I share.
            if (prev == max && cur == newTop && tm.get(newTop) > 1) res++;

            // Update max.
            max = newTop;
        }

        pw.println(res);
        pw.close();
    }
}