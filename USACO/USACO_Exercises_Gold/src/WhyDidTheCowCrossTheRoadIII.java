import java.io.*;
import java.util.ArrayList;

public class WhyDidTheCowCrossTheRoadIII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("circlecross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));

        int n = Integer.parseInt(br.readLine());
        int n2 = 2 * n;
        point[] order = new point[n2];
        point[] start = new point[n];
        for (int i = 0; i < n2; i++) {
            int cow = Integer.parseInt(br.readLine()) - 1;
            point cur = new point(i, cow);
            order[i] = cur;
            if (start[cow] == null) {
                start[cow] = cur;
            }
        }

        boolean[] started = new boolean[n];
        ArrayList<point> open = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < n2; i++) {
            point cur = order[i];
            if (!started[cur.cow]) {
                started[cur.cow] = true;
                open.add(cur);
            } else {
                int loc = binSearch(open, start[cur.cow].loc);
                open.remove(loc);
                sum += open.size() - loc;
            }
        }

        pw.println(sum);
        pw.close();
    }

    private static int binSearch(ArrayList<point> arr, int key) {
        int index = Integer.MAX_VALUE;
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid).loc < key) {
                low = mid + 1;
            } else if (arr.get(mid).loc > key) {
                high = mid - 1;
            } else if (arr.get(mid).loc == key) {
                index = mid;
                break;
            }
        }
        if (index == Integer.MAX_VALUE) {
            return (-1 * low) - 1;
        } else {
            return index;
        }
    }

    private static class point {
        int loc, cow;

        private point(int loc, int cow) {
            this.loc = loc;
            this.cow = cow;
        }
    }
}
