/*
ID: jerryya2
LANG: JAVA
TASK: lamps
*/

import java.io.*;
import java.util.*;

public class lamps2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lamps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));

        int n = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        ArrayList<Integer> on = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int next = Integer.parseInt(st.nextToken());
        while (next != -1) {
            on.add(next - 1);
            next = Integer.parseInt(st.nextToken());
        }
        ArrayList<Integer> off = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        next = Integer.parseInt(st.nextToken());
        while (next != -1) {
            off.add(next - 1);
            next = Integer.parseInt(st.nextToken());
        }

        ArrayList<int[]> all = new ArrayList<>();
        int[] orig = new int[n];
        addAll(all, orig, n - 1, off, on);
        for (int i = n - 1; i >= 0; i--) {
            int finalI = i;
            all.sort(Comparator.comparingInt(a -> a[finalI]));
        }

        boolean impossible = true;
        for (int i = 0; i < all.size(); i++) {
            int[] cur = all.get(i);
            boolean good = true;
            if (i > 0) {
                int[] prev = all.get(i - 1);
                boolean same = true;
                for (int j = 0; j < cur.length; j++) {
                    if (prev[j] != cur[j]) {
                        same = false;
                        break;
                    }
                }
                if (same) {
                    all.remove(i);
                    i--;
                    good = false;
                }
            }
            if (!good) {
                continue;
            }
            if (pos(cur, c)) {
                impossible = false;
                for (int value : cur) {
                    pw.print(value);
                }
                pw.println();
            }
        }
        if (impossible) {
            pw.println("IMPOSSIBLE");
        }
        pw.close();
    }

    private static void addAll(ArrayList<int[]> all, int[] state, int rem, ArrayList<Integer> off, ArrayList<Integer> on) {
        if (rem == -1) {
            all.add(state);
        } else {
            int[] copy = state.clone();
            if (!on.contains(rem)) {
                copy[rem] = 0;
                addAll(all, copy, rem - 1, off, on);
            }
            if (!off.contains(rem)) {
                copy[rem] = 1;
                addAll(all, copy, rem - 1, off, on);
            }
        }
    }

    private static boolean pos(int[] state, int c) {
        if (c == 0) {
            for (int value : state) {
                if (value == 0) {
                    return false;
                }
            }
            return true;
        } else {
            if (pos(button1(state), c - 1)) {
                return true;
            }
            if (pos(button2(state), c - 1)) {
                return true;
            }
            if (pos(button3(state), c - 1)) {
                return true;
            }
            return pos(button4(state), c - 1);
        }
    }

    private static int[] button1(int[] state) {
        int[] copy = state.clone();
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] == 0) {
                copy[i] = 1;
            } else {
                copy[i] = 0;
            }
        }
        return copy;
    }

    private static int[] button2(int[] state) {
        int[] copy = state.clone();
        for (int i = 0; i < copy.length; i += 2) {
            if (copy[i] == 0) {
                copy[i] = 1;
            } else {
                copy[i] = 0;
            }
        }
        return copy;
    }

    private static int[] button3(int[] state) {
        int[] copy = state.clone();
        for (int i = 1; i < copy.length; i += 2) {
            if (copy[i] == 0) {
                copy[i] = 1;
            } else {
                copy[i] = 0;
            }
        }
        return copy;

    }

    private static int[] button4(int[] state) {
        int[] copy = state.clone();
        for (int i = 0; i < copy.length; i += 3) {
            if (copy[i] == 0) {
                copy[i] = 1;
            } else {
                copy[i] = 0;
            }
        }
        return copy;
    }
}
