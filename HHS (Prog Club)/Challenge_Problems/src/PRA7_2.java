import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class PRA7_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int temp = Integer.parseInt(st.nextToken());
        map.put(temp, new ArrayList<>());
        map.get(temp).add(0);
        int[][] nums = new int[n][3000];
        nums[0][temp]++;
        for (int i = 1; i < n; i++) {
            temp = Integer.parseInt(st.nextToken());
            if (!map.containsKey(temp)) {
                map.put(temp, new ArrayList<>());
            }
            map.get(temp).add(i);
            nums[i] = nums[i - 1].clone();
            nums[i][temp]++;
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
                for (int key : map.keySet()) {
                    int num;
                    if (a == 0) {
                        num = nums[b][key];
                    }
                    else {
                        num = nums[b][key] - nums[a - 1][key];
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

    private static int binSearchStart(ArrayList<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            } else if (arr.get(mid) == key) {
                return mid;
            }
        }
        return low;
    }

    private static int binSearchEnd(ArrayList<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            } else if (arr.get(mid) == key) {
                return mid;
            }
        }
        return low - 1;
    }
}
