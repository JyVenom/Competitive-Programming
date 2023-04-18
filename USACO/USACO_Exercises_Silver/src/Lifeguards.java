import java.io.*;
import java.util.*;

public class Lifeguards {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        ArrayList<change> changes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            changes.add(new change(i, Integer.parseInt(st.nextToken()), true));
            changes.add(new change(i, Integer.parseInt(st.nextToken()), false));
        }

        long start = System.currentTimeMillis();
        changes.sort(Comparator.comparingInt(o -> o.loc));
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        int total = 0;
        int[] alone = new int[n];
        HashSet<Integer> prev = new HashSet<>();
        prev.add(changes.get(0).cow);
        int n2 = n * 2;
        for (int i = 1; i < n2; i++) {
            int I = i - 1;
            if (prev.size() == 1) {
                ArrayList<Integer> temp = new ArrayList<>(prev);
                alone[temp.get(0)] += changes.get(i).loc - changes.get(I).loc;
            }
            if (prev.size() > 0) {
                total += changes.get(i).loc - changes.get(I).loc;
            }

            if (changes.get(i).start) {
                prev.add(changes.get(i).cow);
            } else {
                prev.remove(changes.get(i).cow);
            }
        }
        Arrays.sort(alone);

        pw.println(total - alone[0]);
        pw.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    private static class change {
        int cow, loc;
        boolean start;

        private change(int cow, int loc, boolean start) {
            this.cow = cow;
            this.loc = loc;
            this.start = start;
        }
    }
}
