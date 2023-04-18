import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class CircularBarnRevisited {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn2.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = Integer.parseInt(br.readLine());
        }

        int[][] copy = new int[n][2];
        for (int i = 0; i < n; i++) {
            copy[i][0] = i;
            copy[i][1] = r[i];
        }
        Arrays.sort(copy, (o1, o2) -> o2[1] - o1[1]);
        int[] open = new int[k];
        for (int i = 0; i < k; i++) {
            open[i] = copy[i][0];
        }
        int[] wait = new int[n];
        for (int i = 0; i < k; i++) {
            int cur = open[i];
            while (cur != open[(i + 1) % k]) {
                wait[open[i]] += r[cur];
                cur = (cur + 1) % n;
            }
        }
        ArrayList<ArrayList<cow>> at = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = wait[i];
            ArrayList<cow> room = new ArrayList<>(num);
            for (int j = 0; j < num; j++) {
                room.add(new cow());
            }
            at.add(room);
        }

        for (int i = 0; i < n; i++) {
            while (at.get(i).size() > r[i]) {
                int cur = i;
                while (at.get(cur).size() > r[cur]) {
                    int next = (cur + 1) % n;
                    at.get(cur).get(0).walk();
                    at.get(next).add(at.get(cur).get(0));
                    at.get(next).sort(Comparator.comparingLong(o -> o.walked));
                    at.get(cur).remove(0);
                    cur = next;
                }
            }
        }
        long sum = 0;
        for (ArrayList<cow> room : at) {
            for (cow cow : room) {
//                sum += cow.walked * cow.walked;
                sum += cow.walked;
            }
        }

        pw.println(sum);
        pw.close();
    }

    private static class cow {
        long walked;

        private cow() {
            walked = 0;
        }

        private void walk() {
            walked++;
        }
    }
}
