import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoadIII2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("countcross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] roads = new int[r][2];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            roads[i][0] = a * n + b;
            roads[i][1] = c * n + d;
            if (roads[i][0] > roads[i][1]) {
                int temp = roads[i][0];
                roads[i][0] = roads[i][1];
                roads[i][1] = temp;
            }
        }
        Arrays.sort(roads, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(roads, Comparator.comparingInt(arr -> arr[0]));
        int cur = 1;
        int[] all = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (all[i * n + j] == 0) {
                    findComponents(roads, all, cur, i, j, n);
                    cur++;
                }
            }
        }
        int[] cows = new int[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            cows[i] = all[a * n + b];
        }

        int count = 0;
        for (int i = 0; i < k - 1; i++) {
            for (int j = i + 1; j < k; j++) {
                if (cows[i] != cows[j]) {
                    count++;
                }
            }
        }

        pw.println(count);
        pw.close();
    }

    private static void findComponents(int[][] roads, int[] all, int cur, int row, int col, int n) {
        all[row * n + col] = cur;
        if (col > 0 && all[row * n + col - 1] == 0 && binSearch(roads, 0, roads.length - 1, Math.min(row * n + col, row * n + col - 1), Math.max(row * n + col, row * n + col - 1)) == -1) { //left
            findComponents(roads, all, cur, row, col - 1, n);
        }
        if (col < n - 1 && all[row * n + col + 1] == 0 && binSearch(roads, 0, roads.length - 1, Math.min(row * n + col, row * n + col + 1), Math.max(row * n + col, row * n + col + 1)) == -1) { //right
            findComponents(roads, all, cur, row, col + 1, n);
        }
        if (row > 0 && all[(row - 1) * n + col] == 0 && binSearch(roads, 0, roads.length - 1, Math.min(row * n + col, (row - 1) * n + col), Math.max(row * n + col, (row - 1) * n + col)) == -1) { //up
            findComponents(roads, all, cur, row - 1, col, n);
        }
        if (row < n - 1 && all[(row + 1) * n + col] == 0 && binSearch(roads, 0, roads.length - 1, Math.min(row * n + col, (row + 1) * n + col), Math.max(row * n + col, (row + 1) * n + col)) == -1) { //down
            findComponents(roads, all, cur, row + 1, col, n);
        }
    }

    private static int binSearch(int[][] arr, int first, int last, int a, int b) {
        if (last >= first) {
            int mid = first + (last - first) / 2;
            if (arr[mid][0] == a && arr[mid][1] == b) {
                return mid;
            }
            if (arr[mid][0] > a || (arr[mid][0] == a && arr[mid][1] > b)) {
                return binSearch(arr, first, mid - 1, a, b);
            } else {
                return binSearch(arr, mid + 1, last, a, b);
            }
        }
        return -1;
    }
}
