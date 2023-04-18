import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoad3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] cTime = new int[c];
        int[][] nTime = new int[n][2];
        for (int i = 0; i < c; i++) {
            cTime[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cTime);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            nTime[i][0] = Integer.parseInt(st.nextToken());
            nTime[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nTime, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(nTime, Comparator.comparingInt(arr -> arr[0]));

        ArrayList<ArrayList<Integer>> all = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < n; i++) {
            int a = nTime[i][0];
            int b = nTime[i][1];
            all.add(new ArrayList<>());
            for (int j = start; j < c; j++) {
                int curC = cTime[j];
                if (curC < a) {
                    start++;
                } else if (curC <= b) {
                    all.get(i).add(j);
                } else {
                    break;
                }
            }
        }

        boolean[] visited = new boolean[c];
        int max = tryAll(all, 0, 0, 0, visited);

        pw.println(max);
        pw.close();
    }

    private static int tryAll(ArrayList<ArrayList<Integer>> pos, int at, int count, int max, boolean[] visited) {
        ArrayList<Integer> arr = pos.get(at);
        if (at < pos.size() - 1) {
            max = tryAll(pos, at + 1, count, max, visited);
        }
        for (int cur : arr) {
            if (!visited[cur]) {
                visited[cur] = true;
                count++;
                if (count > max) {
                    max = count;
                }
                if (at < pos.size() - 1) {
                    max = tryAll(pos, at + 1, count, max, visited);
                }
                visited[cur] = false;
                count--;
            }
        }
        return max;
    }
}
