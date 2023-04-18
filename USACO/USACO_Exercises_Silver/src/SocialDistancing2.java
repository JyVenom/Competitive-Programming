import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SocialDistancing2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<int[]> grass = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int[] temp = new int[2];
            temp[0] = Integer.parseInt(st.nextToken());
            temp[1] = Integer.parseInt(st.nextToken());
            grass.add(temp);
        }
        grass.sort(Comparator.comparingInt(a -> a[0]));
        br.close();

        int ans = 1;
        int low = 0;
        int high = ((grass.get(grass.size() - 1)[1] - grass.get(0)[0] + 1) / n) + 1;
        int[] pos = new int[high + 2];
        while (low <= high) {
            int mid = (low + high) / 2;
            if (pos[mid] == 0) {
                if (possible(grass, n, mid)) {
                    pos[mid] = 1;
                } else {
                    pos[mid] = -1;
                }
            }
            if (pos[mid + 1] == 0) {
                if (possible(grass, n, mid + 1)) {
                    pos[mid + 1] = 1;
                } else {
                    pos[mid + 1] = -1;
                }
            }
            if (pos[mid] == 1 && pos[mid + 1] == -1) {
                ans = mid;
                break;
            } else if (pos[mid] == 1) {
                ans = mid;
                low = mid + 1;
            } else if (pos[mid] == -1) {
                high = mid - 1;
            }
        }

        pw.println(ans);
        pw.close();
    }

    private static boolean possible(ArrayList<int[]> grass, int n, int d) {
        int count = 0;
        int min = 0;
        int temp = 0;
        for (int[] cur : grass) {
            int diff;
            if (min < cur[0]) {
                min = cur[0];
                diff = cur[1] - cur[0] + d;
            } else if (min <= cur[1]) {
                diff = cur[1] - min + d;
            } else {
                continue;
            }
            if (diff <= d) {
                count++;
            } else {
                int num = (diff / d);
                count += num;
                temp = num;
            }
            min += (temp * d);
            if (count >= n) {
                return true;
            }
        }
        return false;
    }
}
