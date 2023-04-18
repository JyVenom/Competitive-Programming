import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoad6 {
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
            all.get(i).add(i);
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

        for (int i = 0; i < all.size(); i++) {
            int size = all.get(i).size();
            if (size == 1) {
                all.remove(i);
                i--;
            }
            else {
                all.get(i).add(0, size - 1);
            }
        }
        all.sort(Comparator.comparing(a -> a.get(0)));

        int count = 0;
        int[] visited = new int[c];
        Arrays.fill(visited, -1);
        ArrayList<Integer> notAdded = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            ArrayList<Integer> cur = all.get(i);
            int size = cur.size();
            if (size == 2) {
                int pos = cur.get(1);
                if (visited[pos] == -1) {
                    count++;
                    visited[pos] = i;
                    all.remove(i);
                    i--;
                }
            }
            else {
                boolean added = false;
                for (int j = 2; j < size; j++) {
                    int pos = cur.get(j);
                    if (visited[pos] == -1) {
                        added = true;
                        visited[pos] = i;
                        count++;
                        cur.remove(j);
                        cur.set(0, cur.get(0) - 1);
                        break;
                    }
                }
                if (!added) {
                    notAdded.add(i);
                }
            }
        }

        for (int cur : notAdded) {
            ArrayList<Integer> arr = all.get(cur);
            for (int i = 2; i < arr.size(); i++) {
                ArrayList<Integer> other = all.get(visited[arr.get(i)]);
                for (int j = 2; j < other.size(); j++) {
                    if (visited[other.get(j)] == -1) {
                        visited[other.get(j)] = arr.get(i);
                        visited[arr.get(i)] = cur;
                        count++;
                    }
                }
            }
        }
//        for (int i = 0; i < all.size(); i++) {
//            ArrayList<Integer> cur = all.get(i);
//            for (int j = 2; j < cur.size(); j++) {
//                if (visited[cur.get(j)] != -1) {
//                    cur.remove(j);
//                    j--;
//                    cur.set(0, cur.get(0) - 1);
//                }
//            }
//            if (cur.get(0) == 0) {
//                all.remove(i);
//                i--;
//            }
//        }
//        all.sort(Comparator.comparing(a -> a.get(0)));

        pw.println(count);
        pw.close();
    }
}
