import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SwapitySwapitySwap3 {
    public static void main(String[] args) throws Exception {
        long sta = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("swap.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<int[]> swaps = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            swaps.add(new int[2]);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            swaps.get(i)[0] = l;
            swaps.get(i)[1] = r;
        }
        br.close();

        ArrayList<ArrayList<Integer>> all = new ArrayList<>();
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            order.add(i);
        }
        ArrayList<Integer> prev = new ArrayList<>(order);
        all.add(prev);
//        for (int i = 0; i < m; i++) {
//            int start = swaps.get(i)[0];
//            int end = swaps.get(i)[1];
//            boolean good = true;
//            for (int j = 0; j < m; j++){
//                if (j == i){
//                    continue;
//                }
//                if (start >= swaps.get(j)[0] && start <= swaps.get(j)[1]){
//                    good = false;
//                    break;
//                }
//                if (end >= swaps.get(j)[0] && end <= swaps.get(j)[1]){
//                    good = false;
//                    break;
//                }
//            }
//            if (good){
//                int swi = k % 2;
//                if (swi == 1) {
//                    ArrayList<Integer> temp = new ArrayList<>();
//                    for (int l = swaps.get(i)[0]; l <= swaps.get(i)[1]; l++){
//                        temp.add(order.get(swaps.get(i)[0]));
//                        order.remove(swaps.get(i)[0]);
//                    }
//                    int size = temp.size();
//                    for (int l = 0; l < size; l++){
//                        order.add(swaps.get(i)[0],temp.get(0));
//                        temp.remove(0);
//                    }
//                }
//                swaps.remove(i);
//                i--;
//            }
//        }

        int num = -1;
        ArrayList<Integer> temp = new ArrayList<>();
        for (int j = 0; j < m; j++) {
            for (int l = swaps.get(j)[0]; l <= swaps.get(j)[1]; l++){
                temp.add(order.get(swaps.get(j)[0]));
                order.remove(swaps.get(j)[0]);
            }
            int size = temp.size();
            for (int l = 0; l < size; l++){
                order.add(swaps.get(j)[0],temp.get(0));
                temp.remove(0);
            }
        }
        prev = new ArrayList<>(order);
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
                num = i + 1;
                break;
            }
            order = new ArrayList<>(after);
            prev = new ArrayList<>(after);
            all.add(prev);
        }

        if (num != -1){
            int one = (k - 1) % num;
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
        System.out.println(end - sta);
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
