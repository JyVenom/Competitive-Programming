/*
ID: jerryya2
LANG: JAVA
TASK: holstein
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class holstein {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("holstein.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

        int v = Integer.parseInt(br.readLine());
        int[] minVita = new int[v];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < v; i++) {
            minVita[i] = Integer.parseInt(st.nextToken());
        }
        int g = Integer.parseInt(br.readLine());
        int[][] feeds = new int[g][v];
        boolean good = true;
        for (int i = 0; i < g; i++) {
            st = new StringTokenizer(br.readLine());
            good = true;
            for (int j = 0; j < v; j++) {
                feeds[i][j] = Integer.parseInt(st.nextToken());
                if (feeds[i][j] < minVita[j]) {
                    good = false;
                }
            }
            if (good) {
                pw.println("1 " + (i + 1));
                break;
            }
        }
        if (!good){
            for (int min = 2; min <= g; min++) {
                int[] cur = new int[v];
                int[] used = new int[min];
                if (calc(minVita, feeds, cur, used, min, 0, 0)) {
                    Arrays.sort(used);
                    pw.print(min);
                    for (int i = 0; i < min; i++) {
                        pw.print(" " + (used[i] + 1));
                    }
                    pw.println();
                    break;
                }
            }
        }

        pw.close();
    }

    private static boolean calc (int[] minVita, int[][] feeds, int[] cur, int[] used, int rem, int at, int depth) {
        int[] total = cur.clone();
        for (int i = at; i < feeds.length; i++) {
            used[depth] = i;
            for (int j = 0; j < minVita.length; j++) {
                total[j] += feeds[i][j];
            }
            if (rem > 1) {
                if (calc(minVita, feeds, total, used, rem - 1, i + 1, depth + 1)) {
                    return true;
                }
            }
            else if (works(minVita, total)) {
                return true;
            }
            total = cur.clone();
        }
        return false;
    }

    private static boolean works (int[] min, int[] cur) {
        int size = min.length;
        for (int i = 0; i < size; i++) {
            if (cur[i] < min[i]) {
                return false;
            }
        }
        return true;
    }
}
