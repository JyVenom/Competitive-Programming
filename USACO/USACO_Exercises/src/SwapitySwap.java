import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SwapitySwap {
    public static void main(String[] args) throws Exception {
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
        for (int i = 0; i < k; i++) {
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
        }

        for (int i = 0; i < n; i++){
            pw.println(order.get(i));
        }
        pw.close();
    }
}
