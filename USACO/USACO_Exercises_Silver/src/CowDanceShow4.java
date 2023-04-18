import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CowDanceShow4 {
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
            if (times[i] > times[max]) {
                max = i;
            }
        }
        br.close();

        int init = (int) Math.ceil((double) sum / (double) t);
        if (times[max] == t) {
            if ((max + 1) > init) {
//                init = max + 1;
                init = binarySearch(times, t, init - 1, max + 2);
            } else {
                init = binarySearch(times, t, max, init + 1);
            }
        }

        int jump = 1;
        if (works(init, t, times)) {
            init -= jump;
            jump++;
            while (works(init, t, times)) {
                init -= jump;
                jump++;
            }
            while (!works(init, t, times)) {
                init++;
            }
        } else {
            init += jump;
            jump++;
            while (!works(init, t, times)) {
                init += jump;
                jump++;
            }
            do {
                init--;
            }
            while (works(init, t, times));
            init++;
        }

        pw.println(init);
        pw.close();
    }

    private static int binarySearch(int[] times, int t, int first, int last) {
        if (last >= first) {
            int mid = first + (last - first) / 2;
            if (mid == times.length) {
                return mid;
            }
            if (works(mid, t, times) && !works(mid - 1, t, times)) {
                return mid;
            }
            if (works(mid - 1, t, times)) {
                return binarySearch(times, t, first, mid - 1);
            } else {
                return binarySearch(times, t, mid + 1, last);
            }
        }
        return -1;
    }

    private static boolean works(int k, int t, int[] times) {
        int[] cur = new int[k];
        System.arraycopy(times, 0, cur, 0, k);

        int time = 0;
        int wait = k;
        int len = times.length;
        while (wait < len) {
            Arrays.sort(cur);
            int amt = cur[0];
            time += amt;
            for (int i = 0; i < k; i++) {
                cur[i] -= amt;
                if (cur[i] == 0) {
                    if (wait < len) {
                        cur[i] = times[wait];
                        wait++;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        Arrays.sort(cur);
        time += cur[cur.length - 1];

        return time <= t;
    }
}
