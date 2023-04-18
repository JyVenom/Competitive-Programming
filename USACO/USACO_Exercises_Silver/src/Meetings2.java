import java.io.*;
import java.util.*;

public class Meetings2 {
    private static int N;
    private static int L;
    private static int[] w;
    private static int[] x;
    private static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        int[][] temp = new int[N][2];
        w = new int[N];
        d = new int[N];
        x = new int[N];
        ArrayList<ArrayList<Double>> cows = new ArrayList<>();
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            x[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
            temp[i][0] = x[i];
            temp[i][1] = i;
        }
        Arrays.sort(temp, Comparator.comparingInt(arr -> arr[0]));

        int[] W = new int[N];
        int[] D = new int[N];
        int[] X = new int[N];
        for (int i = 0; i < temp.length; i++){
            int t = temp[i][1];
            W[i] = w[t];
            X[i] = x[t];
            D[i] = d[t];
        }
        int[] temp2 = w.clone();
        w = W.clone();
        W = temp2.clone();
        temp2 = x.clone();
        x = W.clone();
        X = temp2.clone();
        temp2 = d.clone();
        d = W.clone();
        D = temp2.clone();
        int t = getTime();
        LinkedList<Integer> rig = new LinkedList<>();
        int ans = 0;
        for (int i = (0); i < (N); ++i) {
            if (d[i] == -1) {
                while (rig.size() != 0 && rig.peek() + 2 * t < x[i]) {
                    rig.poll();
                }
                ans += rig.size();
            }
            else {
                rig.offer(x[i]);
            }
        }
        pw.print(ans);
        pw.close();
    }

    private static int getTime() {
//        ArrayList<Integer> lef = new ArrayList<Integer>();
//        ArrayList<Integer> rig = new ArrayList<Integer>();
//        for (int i = (0); i < (N); ++i)
//        {
//            if (d[i] == -1)
//            {
//                lef.add(x[i]);
//            }
//            else
//            {
//                rig.add(x[i]);
//            }
//        }
//        ArrayList<Pair<Integer,Integer>> v = new ArrayList<Pair<Integer,Integer>>();
//        for (int i = (0); i < ((int)lef.size()); ++i)
//        {
//            v.add({lef.get(i),w[i]});
//        }
//        for (int i = (0); i < ((int)rig.size()); ++i)
//        {
//            v.add(new Pair (L - rig.get(i),w[lef.size() + i]));
//        }
//        sort(v.iterator(), v.end());
//        int tot = 0;
//        for (tangible.Pair<Integer,Integer> t : v)
//        {
//            tot += t.second;
//        }
//        for (tangible.Pair<Integer,Integer> t : v)
//        {
//            tot -= 2 * t.second;
//            if (tot <= 0)
//            {
//                return t.first;
//            }
//        }
        return -1;
    }
}
