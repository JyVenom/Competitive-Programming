import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class PRA7_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        HashSet<Integer> map = new HashSet<>();
        int temp;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            temp = Integer.parseInt(st.nextToken());
            map.add(temp);
            nums[i] = temp;
        }

        HashMap<Integer, Integer> map3 = new HashMap<>();
        temp = 0;
        for (int key : map) {
            map3.put(key, temp);
            temp++;
        }
        HashMap<Integer, int[]> map2 = new HashMap<>();
        map2.put(0, new int[map.size()]);
        map2.get(0)[map3.get(nums[0])] = 1;
        for (int i = 1; i < n; i++) {
            map2.put(i, map2.get(i - 1).clone());
            map2.get(i)[map3.get(nums[i])]++;
        }
        int[][] ans = new int[n][n];
        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (ans[a][b] == 0) {
                int mode = 0;
                int modeVal = 0;
                boolean many = false;
                for (int key : map) {
                    int num;
                    if (a == 0) {
                        num = map2.get(b)[map3.get(key)];
                    } else {
                        num = map2.get(b)[map3.get(key)] - map2.get(a - 1)[map3.get(key)];
                    }
                    if (num != -1) {
                        if (num > mode) {
                            mode = num;
                            modeVal = key;
                            many = false;
                        } else if (num == mode) {
                            many = true;
                        }
                    }
                }
                ans[a][b] = many ? -1 : modeVal;
            }
            pw.println(ans[a][b] == -1 ? "many" : ans[a][b]);
        }

        pw.close();
    }
}
