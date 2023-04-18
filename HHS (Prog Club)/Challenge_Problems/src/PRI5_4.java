import java.io.*;
import java.util.*;

public class PRI5_4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<change> changes = new ArrayList<>(2 * n);
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            change start = new change(0, Integer.parseInt(st.nextToken()), i);
            change end = new change(1, Integer.parseInt(st.nextToken()), i);
            changes.add(start);
            changes.add(end);
            data[i][0] = start.loc;
            data[i][1] = end.loc;
        }


        int sum = 0;
        int right = 0;
        Arrays.sort(data, (o1, o2) -> Integer.compare(o2[1], o1[1]));
        Arrays.sort(data, Comparator.comparingInt(arr -> arr[0]));
        for (int i = 0; i < n; i++) {
            if (data[i][1] > right) {
                sum += data[i][1] - Math.max(data[i][0], right);
                right = data[i][1];
            }
        }
        changes.sort(Comparator.comparingInt(o -> o.loc));
        int[] alone = new int[n];
//        HashSet<Integer> cur = new HashSet<>();
        ArrayList<Integer> cur = new ArrayList<>();
        cur.add(changes.get(0).cow);
        for (int i = 1; i < changes.size(); i++) {
            change change = changes.get(i);
            if (change.loc != changes.get(i - 1).loc) {
                if (cur.size() == 1) {
                    alone[cur.get(0)] += change.loc - changes.get(i - 1).loc;
                }
            }
            if (change.type == 0) {
                cur.add(change.cow);
            } else {
                cur.remove((Integer) change.cow);
            }
        }
        Arrays.sort(alone);

        pw.println(sum - alone[0]);
        pw.close();
    }

    private static class change {
        int type, loc, cow; //type: 0 for start 1 for end

        private change(int type, int loc, int cow) {
            this.type = type;
            this.loc = loc;
            this.cow = cow;
        }
    }
}


