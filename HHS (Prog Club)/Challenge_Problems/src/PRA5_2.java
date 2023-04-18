import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class PRA5_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> order = new ArrayList<>(n);
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
        ArrayList<Integer> moves = new ArrayList<>();
        ArrayList<Integer> inOrder = new ArrayList<>(order.subList(ans, order.size()));
        order = new ArrayList<>(order.subList(0, ans));
        int rem = ans;
        for (int i = 0; i < ans; i++) {
            int place = -1 * (Collections.binarySearch(inOrder, order.get(0)) + 1);
            if (place < order.size()) {
                inOrder.add(place, order.get(0));
            }
            else {
                inOrder.add(order.get(0));
            }
            order.remove(0);
            moves.add(place + rem - 1);
            rem--;
        }

        pw.println(ans);
        pw.print(moves.get(0));
        for (int i = 1; i < moves.size(); i++) {
            pw.print(" " + moves.get(i));
        }
        pw.println();
        pw.close();
    }
}
