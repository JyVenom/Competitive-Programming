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

public class lamps {
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
        if (c == 0) {
            int[] orig = new int[n];
            Arrays.fill(orig, 1);
            all.add(orig);
        }
        else if (c == 1) {
            int[] orig = new int[n];
            all.add(orig.clone());
            all.add(button2(orig).clone());
            all.add(button1(orig).clone());
            all.add(button4(button2(orig)).clone());
        }
        else if (c == 2) {
            int[] orig = new int[n];
            all.add(orig.clone());
            all.add(button1(orig.clone()));
            all.add(button2(orig).clone());
            all.add(button1(orig).clone());
            all.add(button4(button3(orig)).clone());
            all.add(button3(orig).clone());
            all.add(button1(orig));
        }
        else {
            int[] orig = new int[n];
            all.add(orig.clone());
            all.add(button2(orig).clone());
            all.add(button1(orig).clone());
            all.add(button2(orig).clone());
            all.add(button4(orig).clone());
            all.add(button1(orig).clone());
            all.add(button3(orig).clone());
            all.add(button1(orig));
        }
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

    private static int[] button1(int[] state) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                state[i] = 1;
            } else {
                state[i] = 0;
            }
        }
        return state;
    }

    private static int[] button2(int[] state) {
        for (int i = 0; i < state.length; i += 2) {
            if (state[i] == 0) {
                state[i] = 1;
            } else {
                state[i] = 0;
            }
        }
        return state;
    }

    private static int[] button3(int[] state) {
        for (int i = 1; i < state.length; i += 2) {
            if (state[i] == 0) {
                state[i] = 1;
            } else {
                state[i] = 0;
            }
        }
        return state;

    }

    private static int[] button4(int[] state) {
        for (int i = 0; i < state.length; i += 3) {
            if (state[i] == 0) {
                state[i] = 1;
            } else {
                state[i] = 0;
            }
        }
        return state;
    }
}
