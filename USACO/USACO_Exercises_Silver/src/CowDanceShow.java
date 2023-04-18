import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CowDanceShow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int max = 0;
        int sum = 0;
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(br.readLine());
            sum += times[i];
            if (times[i] >= times[max]) {
                max = i;
            }
        }
        br.close();

        int init = (int) Math.ceil((double) sum / (double) t);
        if (times[max] == t) {
            if ((max + 1) > init) {
                init = max + 1;
            }
        }

        if (works(init, t, times)) {
            init--;
            while (works(init, t, times)) {
                init--;
            }
            init++;
        } else {
            init++;
            while (!works(init, t, times)) {
                init++;
            }
        }

        pw.println(init);
        pw.close();
    }

    private static boolean works(int k, int t, int[] times) {
        int[] cur = new int[k];
        System.arraycopy(times, 0, cur, 0, k);

        int time = 0;
        int wait = k;
        int len = times.length;
        while (wait < len) {
            time++;
            for (int i = 0; i < k; i++) {
                cur[i]--;
                if (cur[i] == 0) {
                    if (wait < len) {
                        cur[i] = times[wait];
                        wait++;
                    }
                }
            }
        }
        Arrays.sort(cur);
        time += cur[cur.length - 1];

        return time <= t;
    }
}
