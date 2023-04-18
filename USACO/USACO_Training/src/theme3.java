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

public class theme3 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
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
        for (int i = 4; i < max; i++) {
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
                ArrayList<Integer> theme = new ArrayList<>();
                int end2 = j + i;
                for (int k = j; k < end2; k++) {
                    theme.add(dist[k]);
                }

                int k;
                int pos = j + i + 1;
                if (prevK != -1) {
                    k = Math.max(prevK, j + i + 1);
                }
                else {
                    k = pos;
                }
                for (; k < n - i; k++) {
                    boolean good = true;
                    for (int l = 0; l < i; l++) {
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

        pw.println(ans + 1);
        pw.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
