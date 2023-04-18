import java.io.*;
import java.util.StringTokenizer;

public class SwapitySwapitySwap9 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("swap.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] to = new int[n];
        int[] l = new int[n];
        for (int i = 0; i < n; i++) l[i] = i;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            while (a < b) {
                int t = l[a];
                l[a] = l[b];
                l[b] = t;
                a++;
                b--;
            }
        }
        if (n >= 0) System.arraycopy(l, 0, to, 0, n);
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) ret[i] = i + 1;
        while (k > 0) {
            if (k % 2 == 1) {
                ret = apply(ret, to);
            }
            k /= 2;
            if (k > 0) to = apply(to, to);
        }
        for (int val : ret) pw.println(val);
        pw.close();
    }

    public static int[] apply(int[] l, int[] op) {
        int[] ret = new int[l.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = l[op[i]];
        }
        return ret;
    }
}
