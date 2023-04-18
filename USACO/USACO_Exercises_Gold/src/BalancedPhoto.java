import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class BalancedPhoto {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bphoto.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));

        int n = Integer.parseInt(br.readLine());
        ArrayList<cow> cows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cows.add(new cow(i, Integer.parseInt(br.readLine())));
        }

        ArrayList<cow> copy = new ArrayList<>(cows);
        copy.sort((o1, o2) -> o2.height - o1.height);
        int count = 0;
        ArrayList<Integer> locs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cow cur = copy.get(i);

            int l;
            int temp = Collections.binarySearch(locs, cur.loc);
            if (temp < 0) {
                l = -1 * (temp + 1);
            }
            else {
                l = temp;
            }
            int r = locs.size() - l;

            if (l > 2 * r || r > 2 * l) {
                count++;
            }

            locs.add(l, cur.loc);

        }

        pw.println(count);
        pw.close();
    }

    private static class cow {
        int loc, height;

        private cow(int loc, int height) {
            this.loc = loc;
            this.height = height;
        }
    }
}
