import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringTokenizer;

public class MilkPails2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<state> prev = new ArrayList<>();
        prev.add(new state(0, 0));
        for (int i = 0; i < k; i++) {
            ArrayList<state> cur = new ArrayList<>();
            for (state state : prev) {
                state temp = new state(state.x, state.y);
                temp.x = x;
                if (!cur.contains(temp)) {
                    cur.add(temp);
                }

                temp = new state(state.x, state.y);
                temp.y = y;
                if (!cur.contains(temp)) {
                    cur.add(temp);
                }

                temp = new state(state.x, state.y);
                int dif = y - temp.y;
                if (temp.x <= dif) {
                    temp.y += temp.x;
                    temp.x = 0;
                } else {
                    temp.y = y;
                    temp.x -= dif;
                }
                if (!cur.contains(temp)) {
                    cur.add(temp);
                }

                temp = new state(state.x, state.y);
                dif = x - temp.x;
                if (temp.y < dif) {
                    temp.x += temp.y;
                    temp.y = 0;
                } else {
                    temp.x = x;
                    temp.y -= dif;
                }
                if (!cur.contains(temp)) {
                    cur.add(temp);
                }
            }
            cur.sort(Comparator.comparingInt(o -> (o.x + o.y)));
            prev = new ArrayList<>(cur);
        }
        ArrayList<Integer> all = new ArrayList<>();
        for (state state : prev) {
            all.add(state.x + state.y);
        }
        int ans = binSearch(all, m);
        if (ans >= 0) {
            pw.println(0);
        }
        else {
            int temp = -1 * (ans + 1);
            if (temp == 0) {
                pw.println(Math.min(m, Math.abs(all.get(temp) - m)));
            }
            else {
                int a = Math.abs(all.get(temp) - m);
                int b = Math.abs(all.get(temp - 1) - m);
                pw.println(Math.min(a, b));
            }
        }



        pw.close();
    }

    private static int binSearch(ArrayList<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            } else if (arr.get(mid) == key) {
                index = mid;
                break;
            }
        }
        if (index == Integer.MAX_VALUE) {
            return (-1 * low) - 1;
        }
        else {
            return index;
        }
    }

    private static class state {
        int x, y;

        private state (int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            state state = (state) o;
            return x == state.x && y == state.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
