import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SwapitySwap2 {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("swap.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] a = new int[2];
        int[] b = new int[2];
        st = new StringTokenizer(br.readLine());
        a[0] = Integer.parseInt(st.nextToken()) - 1;
        a[1] = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        b[0] = Integer.parseInt(st.nextToken()) - 1;
        b[1] = Integer.parseInt(st.nextToken()) - 1;
        br.close();
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            order.add(i);
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for (int j = a[0]; j <= a[1]; j++){
            temp.add(order.get(a[0]));
            order.remove(a[0]);
        }
        int size = temp.size();
        for (int j = 0; j < size; j++){
            order.add(a[0],temp.get(0));
            temp.remove(0);
        }
        for (int j = b[0]; j <= b[1]; j++){
            temp.add(order.get(b[0]));
            order.remove(b[0]);
        }
        size = temp.size();
        for (int j = 0; j < size; j++){
            order.add(b[0], temp.get(0));
            temp.remove(0);
        }
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
            order = new ArrayList<>(after);
        }

        for (int i = 0; i < n; i++){
            pw.println(order.get(i));
        }
        pw.close();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
