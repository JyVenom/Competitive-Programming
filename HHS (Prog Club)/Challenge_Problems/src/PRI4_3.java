import java.io.*;
import java.util.*;

public class PRI4_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        long[][] points = new long[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Long.parseLong(st.nextToken());
            points[i][1] = Long.parseLong(st.nextToken());
        }

        ArrayList<long[]> dist = new ArrayList<>((N * N) / 2);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long[] temp = new long[]{(long) i, (long) j, dist(points[i][0], points[i][1], points[j][0], points[j][1])};
                dist.add(temp);
            }
        }
        dist.sort(Comparator.comparingLong(a -> a[2]));
        boolean good = false;
        long min = dist.get(0)[2];
        loop:
        for (int i = 0; i < N; i++) {
            if (dist.get(i)[2] != min) {
                break;
            }
            for (int j = i + 1; j < N; j++) {
                if (dist.get(j)[2] != min) {
                    break;
                }
                if (dist.get(j)[0] != dist.get(i)[0] && dist.get(j)[0] != dist.get(i)[1] && dist.get(j)[1] != dist.get(i)[0] && dist.get(j)[1] != dist.get(i)[1]) {
                    good = true;
                    break loop;
                }
            }
        }
        if (good) {
            pw.println(1);
        }
        else {
            HashSet<Integer> temp = new HashSet<>();
            for (int i = 0; i < N; i++) {
                if (dist.get(i)[2] != min) {
                    break;
                }

                temp.add((int) dist.get(i)[0]);
                temp.add((int) dist.get(i)[1]);
            }
            ArrayList<Integer> all = new ArrayList<>(temp);
            Collections.sort(all);
            long max = 0;
            int maxNum = 0;
            for (Integer integer : all) {
                for (long[] longs : dist) {
                    if (longs[0] != integer && longs[1] != integer) {
                        if (longs[2] > max) {
                            max = longs[2];
                            maxNum = integer;
                        }
                        break;
                    }
                }
            }
            pw.println(maxNum + 1);
        }

        pw.close();
    }

    private static long dist(long ax, long ay, long bx, long by) {
        return (ax - bx) * (ax - bx) + (ay - by) * (ay - by);
    }
}
