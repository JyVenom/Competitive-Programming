import java.io.*;
import java.util.*;

public class PRA4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, String> map = new HashMap<>();
        HashMap<String, Integer> rev = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            map.put(i, temp);
            rev.put(temp, i);
        }
        int M = Integer.parseInt(br.readLine());
//        ArrayList<int[]> safe = new ArrayList<>();
        ArrayList<int[]> imposter = new ArrayList<>();
        boolean[] safe2 = new boolean[N];
//        boolean[] imp = new boolean[N];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] temp = new int[2];
            temp[0] = rev.get(st.nextToken());
            st.nextToken();
            temp[1] = rev.get(st.nextToken());
            st.nextToken();
            if (st.nextToken().equals("safe")) {
//                safe.add(temp);
                safe2[temp[1]] = true;
            }
            else {
                imposter.add(temp);
//                imp[temp[1]] = true;
            }
        }
//        safe.sort(Comparator.comparingInt(a -> a[1]));
//        safe.sort(Comparator.comparingInt(a -> a[0]));
//        imposter.sort(Comparator.comparingInt(a -> a[1]));
//        imposter.sort(Comparator.comparingInt(a -> a[0]));

        ArrayList<Integer> pos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (safe2[i]) {
                continue;
            }
//            else if (imp[i]) {
//                pos.add(i);
//                continue;
//            }

            boolean good = true;
            for (int[] ints : imposter) {
                if (ints[0] != i && ints[1] != i) {
                    good = false;
                    break;
                }
            }
            if (good) {
                pos.add(i);
            }
        }
        ArrayList<String> str = new ArrayList<>();
        for (Integer po : pos) {
            str.add(map.get(po));
        }
        Collections.sort(str);

        pw.println(str.size());
        for (String s : str) {
            pw.println(s);
        }
        pw.close();
    }
}
