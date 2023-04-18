import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SocialDistancing {
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

        int ans = -1;
        int max = (grass.get(grass.size() - 1)[1] - grass.get(0)[0] + 1) / n;
        for (int  i = 2; i <= max; i++) {
            if (!possible(grass, n, i)) {
                break;
            }
            ans = i;
        }

        pw.println(ans);
        pw.close();
    }

    private static boolean possible (ArrayList<int[]> grass, int n, int d) {
        int count = 0;
        for (int[] cur : grass) {
            int diff = cur[1] - cur[0] + d;
            if (diff <= d) {
                count++;
            } else {
                count += (diff / d);
            }
            if (count >= n) {
                return true;
            }
        }
        return false;
    }
}
