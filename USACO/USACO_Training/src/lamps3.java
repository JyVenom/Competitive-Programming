/*
ID: jerryya2
LANG: JAVA
TASK: lamps
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class lamps3 {
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
        Arrays.fill(orig, 1);
        addAll(all, orig, c);
        for (int i = n - 1; i >= 0; i--) {
            int finalI = i;
            all.sort(Comparator.comparingInt(a -> a[finalI]));
        }

        boolean impossible = true;
        for (int i = 0; i < all.size(); i++) {
            int[] cur = all.get(i);
            boolean good = true;
            for (int loc : on) {
                if (cur[loc] != 1) {
                    all.remove(i);
                    i--;
                    good = false;
                    break;
                }
            }
            if (!good) {
                continue;
            }
            for (int loc : off) {
                if (cur[loc] != 0) {
                    all.remove(i);
                    i--;
                    good = false;
                    break;
                }
            }
            if (!good) {
                continue;
            }
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

            impossible = false;
            for (int value : cur) {
                pw.print(value);
            }
            pw.println();
        }
        if (impossible) {
            pw.println("IMPOSSIBLE");
        }
        pw.close();
    }

    private static void addAll(ArrayList<int[]> all, int[] state, int c) {
        if (c == 0) {
            all.add(state);
        } else {
            addAll(all, button1(state), c - 1);
            addAll(all, button2(state), c - 1);
            addAll(all, button3(state), c - 1);
            addAll(all, button4(state), c - 1);
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
