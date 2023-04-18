import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class BalancedPhoto2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bphoto.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));

        int n = Integer.parseInt(br.readLine());
        ArrayList<int[]> cows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cows.add(new int[]{i, Integer.parseInt(br.readLine())});
        }

        cows.sort((o1, o2) -> o2[1] - o1[1]);
        int count = 0;
        ArrayList<Integer> locs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int cur = cows.get(i)[0];

            int l;
            int temp = Collections.binarySearch(locs, cur);
            if (temp < 0) {
                l = -1 * (temp + 1);
            } else {
                l = temp;
            }
            int r = locs.size() - l;

            if (l > 2 * r || r > 2 * l) {
                count++;
            }

            locs.add(l, cur);
        }

        pw.println(count);
        pw.close();
    }
}
