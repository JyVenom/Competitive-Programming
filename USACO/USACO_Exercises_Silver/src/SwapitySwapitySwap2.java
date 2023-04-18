import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SwapitySwapitySwap2 {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("swap.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] swaps = new int[m][2];
        for (int i = 0; i < m;i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            swaps[i][0] = l;
            swaps[i][1] = r;
        }
        br.close();


        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            order.add(i);
        }
        ArrayList<ArrayList<Integer>> all = new ArrayList<>();
        all.add(order);
        int num = -1;
        ArrayList<Integer> temp = new ArrayList<>();
        for (int j = 0; j < m; j++) {
            for (int l = swaps[j][0]; l <= swaps[j][1]; l++){
                temp.add(order.get(swaps[j][0]));
                order.remove(swaps[j][0]);
            }
            int size = temp.size();
            for (int l = 0; l < size; l++){
                order.add(swaps[j][0],temp.get(0));
                temp.remove(0);
            }
        }
        ArrayList<Integer> prev = new ArrayList<>(order);
        all.add(prev);
        ArrayList<ArrayList<Integer>> switches = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if (order.get(i) != i + 1){
                ArrayList<Integer> s = new ArrayList<>();
                s.add(order.get(i) - 1);
                s.add(i);
                switches.add(s);
            }
        }
        for (int i = 0; i < k - 1; i++){
            ArrayList<Integer> after = new ArrayList<>(order);
            for (ArrayList<Integer> aSwitch : switches) {
                after.set(aSwitch.get(1), order.get(aSwitch.get(0)));
            }
            if (inOrder(after)){
                num = i + 2;
                break;
            }
            order = new ArrayList<>(after);
            prev = new ArrayList<>(after);
            all.add(prev);
        }

        if (num != -1){
            int one = k % num;
            for (int i = 0; i < n; i++){
                pw.println(all.get(one).get(i));
            }
        }
        else {
            for (int i = 0; i < n; i++){
                pw.println(all.get(all.size() - 1).get(i));
            }
        }
        pw.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static boolean inOrder (ArrayList<Integer> arr){
        for (int i = 0; i < arr.size(); i++){
            if (arr.get(i) != i + 1) {
                return false;
            }
        }
        return true;
    }
}
