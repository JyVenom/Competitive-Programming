/*
ID: jerryya2
LANG: JAVA
TASK: rockers
*/

import java.io.*;
import java.util.StringTokenizer;

public class rockers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("rockers.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] songs = new int[n];
        for (int i = 0; i < n; i++) {
            songs[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int best = solve(0, 0, 0, 0, 0, n, t, songs, m);

        pw.println(best);
        pw.close();
    }

    /**
     * Recursively solve by brute force
     *
     * @param i     the current song
     * @param n     number of songs used
     * @param t     space used on current disk
     * @param m     number of disks used
     * @param best  maximum number of songs used so far
     * @param N     total number of songs to choose from (problem input parameter)
     * @param T     maximum storage length of a disc
     * @param songs length of songs to choose from, ordered by the date they were written
     * @param M     number of discs (problem input parameter)
     * @return maximum number of songs that can be released on m discs following problem parameters
     */
    private static int solve(int i, int n, int t, int m, int best, int N, int T, int[] songs, int M) {
        if (i == N) {
            return Math.max(best, n);
        }
        best = solve(i + 1, n, t, m, best, N, T, songs, M);
        if (T >= t + songs[i]) {
            best = solve(i + 1, n + 1, t + songs[i], m, best, N, T, songs, M);
        } else if (m + 1 < M && T >= songs[i]) {
            best = solve(i + 1, n + 1, songs[i], m + 1, best, N, T, songs, M);
        }
        return best;
    }
}
