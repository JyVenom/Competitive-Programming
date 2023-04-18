import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SwapitySwapitySwap {
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
        ArrayList<Integer> time = new ArrayList<>(order);
        int num = -1;
        for (int i = 0; i < k; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                for (int l = swaps[j][0]; l <= swaps[j][1]; l++){
                    temp.add(time.get(swaps[j][0]));
                    time.remove(swaps[j][0]);
                }
                int size = temp.size();
                for (int l = 0; l < size; l++){
                    time.add(swaps[j][0],temp.get(0));
                    temp.remove(0);
                }
            }
            if (inOrder(time)){
                num = i + 1;
                break;
            }
            ArrayList<Integer> prev = new ArrayList<>(time);
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
