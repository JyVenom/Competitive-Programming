import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class PRA5_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> order = new ArrayList<>(n);
//        int[] test = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < n; i++) {
            order.add(Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (order.get(i) > order.get(i + 1)) {
                ans = i + 1;
                break;
            }
        }
        pw.println(ans);
        for (int i = 0; i < ans; i++) {
            int rem = ans - i;
            int place = -1 * (Collections.binarySearch(order.subList(rem, order.size()), order.get(0)) + 1);
            if (place < order.size()) {
                order.add(place + rem, order.get(0));
            }
            else {
                order.add(order.get(0));
            }
            order.remove(0);
            if (i == 0) {
                pw.print(place + rem - 1);
            }
            else {
                pw.print(" " + (place + rem - 1));
            }
        }
        pw.println();

        pw.close();
    }
}
