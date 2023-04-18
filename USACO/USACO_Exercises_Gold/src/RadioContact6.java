import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class RadioContact6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("radio.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] f = new int[2];
        f[0] = Integer.parseInt(st.nextToken());
        f[1] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] b = new int[2];
        b[0] = Integer.parseInt(st.nextToken());
        b[1] = Integer.parseInt(st.nextToken());
        String line = br.readLine();
        int[] fPath = new int[n];
        for (int i = 0; i < n; i++) {
            fPath[i] = line.charAt(i);
        }
        line = br.readLine();
        int[] bPath = new int[m];
        for (int i = 0; i < m; i++) {
            bPath[i] = line.charAt(i);
        }

        ArrayList<long[]> list = new ArrayList<>();
        long ans = helper(new int[]{f[0], f[1], b[0], b[1]}, fPath, bPath, 0, 0, 0, 0, list);

        pw.println(ans);
        pw.close();
    }

    private static long helper(int[] cur, int[] fPath, int[] bPath, int remF, int remB, long curVal, long ans, ArrayList<long[]> list) {
        if (ans != 0) {
            return ans;
        }
        if (remF == fPath.length && remB == bPath.length) {
            ans = curVal;
            return ans;
        }

        if (remF < fPath.length) {
            int[] next = cur.clone();

            if (fPath[remF] == 78) {
                next[1]++;
            } else if (fPath[remF] == 69) {
                next[0]++;
            } else if (fPath[remF] == 83) {
                next[1]--;
            } else if (fPath[remF] == 87) {
                next[0]--;
            }

            long newVal = curVal + (next[0] - next[2]) * (next[0] - next[2]) + (next[1] - next[3]) * (next[1] - next[3]);

            list.add(new long[]{next[0], next[1], next[2], next[3], remF + 1, remB, newVal});
        }
        if (remB < bPath.length) {
            int[] next = cur.clone();

            if (bPath[remB] == 78) {
                next[3]++;
            } else if (bPath[remB] == 69) {
                next[2]++;
            } else if (bPath[remB] == 83) {
                next[3]--;
            } else if (bPath[remB] == 87) {
                next[2]--;
            }

            long newVal = curVal + (next[0] - next[2]) * (next[0] - next[2]) + (next[1] - next[3]) * (next[1] - next[3]);

            list.add(new long[]{next[0], next[1], next[2], next[3], remF, remB + 1, newVal});
        }
        if (remF < fPath.length && remB < bPath.length) {
            int[] next = cur.clone();

            if (fPath[remF] == 78) {
                next[1]++;
            } else if (fPath[remF] == 69) {
                next[0]++;
            } else if (fPath[remF] == 83) {
                next[1]--;
            } else if (fPath[remF] == 87) {
                next[0]--;
            }
            if (bPath[remB] == 78) {
                next[3]++;
            } else if (bPath[remB] == 69) {
                next[2]++;
            } else if (bPath[remB] == 83) {
                next[3]--;
            } else if (bPath[remB] == 87) {
                next[2]--;
            }

            long newVal = curVal + (next[0] - next[2]) * (next[0] - next[2]) + (next[1] - next[3]) * (next[1] - next[3]);

            list.add(new long[]{next[0], next[1], next[2], next[3], remF + 1, remB + 1, newVal});
        }

        list.sort(Comparator.comparingLong(a -> a[6]));
        long[] temp = list.get(0);
        list.remove(0);
        ans = helper(new int[]{(int) temp[0], (int) temp[1], (int) temp[2], (int) temp[3]}, fPath, bPath, (int) temp[4], (int) temp[5], temp[6], ans, list);
        return ans;
    }
}
