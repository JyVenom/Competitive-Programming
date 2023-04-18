//should be faster than #4 but is not
//times out on prob ## 11 and 12, while #4 passes in 2s
//should be ~25M, same or better than #4
//this has a longest time of 3s vs the 2s of #4

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BessiesDream9 {
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, -1, 0, 1};
    public static long count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dream.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A.add(new ArrayList<>(M));
        }
        for (int i = 0; i < N; i++) {
            int[] values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                A.get(i).add(values[j]);
            }
        }

        boolean foundAns = false;
        LinkedList<Integer> q = new LinkedList<>();
        ArrayList<Integer> D = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            count++;
            D.add(-1);
        }
        int s = (new state(0, 0, -1, false)).pack();
        q.offer(s);
        D.set(s, 0);
        while (!q.isEmpty()) {
            count++;
            state states = state.unpack(q.peek());
            q.poll();

            if (states.r == N - 1 && states.c == M - 1) {
                count++;
                pw.println(D.get(states.pack()));
                foundAns = true;
                break;
            }
            if (A.get(states.r).get(states.c) == 4 && states.ld != -1) {
                count++;
                int col = getCell(A, states.r + dr[states.ld], states.c + dc[states.ld]);
                if (col != 0 && col != 3) {
                    count++;
                    state nst = new state(states.r + dr[states.ld], states.c + dc[states.ld], states.ld, col == 2);
                    if (D.get(nst.pack()) != -1) {
                        continue;
                    }
                    D.set(nst.pack(), D.get(states.pack()) + 1);
                    q.offer(nst.pack());
                    continue;
                }
            }
            for (int i = 0; i < 4; i++) {
                count++;
                int col = getCell(A, states.r + dr[i], states.c + dc[i]);
                if (col == 0 || (col == 3 && !states.smell)) {
                    continue;
                }
                state nst = new state(states.r + dr[i], states.c + dc[i], i, col == 2 || (col != 4 && states.smell));
                if (D.get(nst.pack()) != -1) {
                    continue;
                }
                D.set(nst.pack(), D.get(states.pack()) + 1);
                q.offer(nst.pack());
            }
        }

        if (!foundAns) {
            pw.println(-1);
        }
        pw.close();
    }

    private static int getCell(ArrayList<ArrayList<Integer>> A, int r, int c) {
        if (r < 0 || r >= A.size() || c < 0 || c >= A.get(r).size()) {
            return 0;
        }
        return A.get(r).get(c);
    }

    public static class state {
        int r;
        int c;
        int ld;
        boolean smell;

        public state(int r, int c, int ld, boolean smell) {
            this.r = r;
            this.c = c;
            this.ld = ld;
            this.smell = smell;
        }

        public static state unpack(int x) {
            return new state(x / 10000, (x / 10) % 1000, (x / 2) % 5 - 1, (x & 1) != 0);
        }

        public final int pack() {
            return (smell ? 1 : 0) + 2 * (ld + 1) + 10 * c + 10000 * r;
        }
    }
}
