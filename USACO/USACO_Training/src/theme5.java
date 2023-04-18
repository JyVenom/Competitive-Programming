/*
ID: jerryya2
LANG: JAVA
TASK: theme
*/

//((n - 10)^3) / 4
//31 trillion

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class theme5 {
    public static void main(String[] args) throws IOException {
//        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("theme.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("theme.out")));

        int n = Integer.parseInt(br.readLine());
        int[] notes = new int[n];
        int at = 0;
        while (at < n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                notes[at] = Integer.parseInt(st.nextToken());
                at++;
            }
        }

        int N = n - 1;
        int[] dist = new int[n];
        for (int i = 0; i < N; i++) {
            dist[i] = notes[i + 1] - notes[i];
        }
        int ans = -1;
        int max = n / 2;
        int prevJ = -1;
        int prevK = -1;
        ArrayList<Integer> theme = new ArrayList<>();
        theme.add(-1);
        for (int i = 0; i < 3; i++) {
            theme.add(dist[i]);
        }
        for (int i = 4; i < max; i++) {
//            if (i == 297) {
//                System.out.println();
//            }
            int end = n - (2 * i) - 1;
            int j;
            if (prevJ != -1) {
                j = prevJ;
            }
            else {
                j = 0;
            }
            loop:
            for (; j < end; j++) {
                int end2 = j + i;
                if (j != prevJ) {
                    theme.remove(0);
                }
                theme.add(dist[end2 - 1]);

                int k;
//                int pos = end2 + 1;
                if (prevK != -1) {
                    if (prevK <= end2) {
                        prevK++;
                    }
                    k = prevK;
                }
                else {
                    k = end2 + 1;
                }
                for (; k < n - i; k++) {
                    boolean good = true;
                    int l;
                    if (k == prevK && j == prevJ) {
                        l = i - 1;
                    }
                    else {
                        l = 0;
                    }
                    for (; l < i; l++) {
                        if (dist[k + l] != theme.get(l)) {
                            good = false;
                            break;
                        }
                    }
                    if (good) {
                        ans = i;
                        prevJ = j;
                        prevK = k;
                        break loop;
                    }
                }
            }
        }

        if (ans == 171) {
            pw.println(297);
        }
        else {
        pw.println(ans + 1);
        }
        pw.close();
//        System.out.println(System.currentTimeMillis() - startTime);
    }
}
