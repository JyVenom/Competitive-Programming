import java.io.*;
import java.util.*;

public class MilkPails4 {
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
                if (temp.x != x) {
                    temp.x = x;
                    if (!cur.contains(temp)) {
                        cur.add(temp);
                    }
                }

                temp = new state(state.x, state.y);
                if (temp.y != y) {
                    temp.y = y;
                    if (!cur.contains(temp)) {
                        cur.add(temp);
                    }
                }

                //from x to y
                temp = new state(state.x, state.y);
                if (temp.y != y && temp.x != 0) {
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
                }

                //from y to x
                temp = new state(state.x, state.y);
                if (temp.x != x && temp.y != 0) {
                    int dif = x - temp.x;
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
            }
            cur.sort(Comparator.comparingInt(o -> (o.x + o.y)));
            prev = new ArrayList<>(cur);
        }
        HashSet<Integer> temp = new HashSet<>();
        for (state state : prev) {
            temp.add(state.x + state.y);
        }
        ArrayList<Integer> all = new ArrayList<>(temp);
        Collections.sort(all);
        int ans = binSearch(all, m);
        if (ans >= 0) {
            pw.println(0);
        }
        else {
            int loc = -1 * (ans + 1);
            if (loc == 0) {
                pw.println(Math.abs(all.get(loc) - m));
            }
            else if (loc == all.size()) {
                pw.println(m - all.get(all.size() - 1));
            }
            else {
                int a = Math.abs(all.get(loc) - m);
                int b = Math.abs(all.get(loc - 1) - m);
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
