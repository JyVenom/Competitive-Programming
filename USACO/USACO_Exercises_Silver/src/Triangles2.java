import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Triangles2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));

        int n = Integer.parseInt(br.readLine());
        int[][] posts = new int[n][2];
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            posts[i][0] = Integer.parseInt(st.nextToken());
            posts[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(posts, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(posts, Comparator.comparingInt(arr -> arr[0]));


        ArrayList<ArrayList<Integer>> allXS = new ArrayList<>();
        ArrayList<ArrayList<Integer>> allYS = new ArrayList<>();
        for (int i = 0; i < posts.length; i++) {
            int x = posts[i][0];
            int y = posts[i][1];
            int res = binarySearch(allXS, 0, allXS.size() - 1, y);
            if (res == -1){
                ArrayList<Integer> xs = allX(posts, y);
                xs.add(0, y);
                allXS.add(xs);
                allXS.sort(Comparator.comparing(a -> a.get(0)));
            }
            res = binarySearch(allYS, 0, allYS.size() - 1, x);
            if (res == -1){
                ArrayList<Integer> ys = allY(posts, x);
                ys.add(0, x);
                allYS.add(ys);
                allYS.sort(Comparator.comparing(a -> a.get(0)));
            }
        }

        long total = 0;
        for (int i = 0; i < n; i++){
            int x = posts[i][0];
            int y = posts[i][1];
            int index = binarySearch(allXS, 0, allXS.size() - 1, y);
            ArrayList<Integer> xs = allXS.get(index);
            index = binarySearch(allYS, 0, allYS.size() - 1, x);
            ArrayList<Integer> ys = allYS.get(index);
//            int tx = 0;
//            for (int j = 1; j < xs.size(); j++) {
//                tx += Math.abs(xs.get(j) - x);
//            }
//            int ty = 0;
//            for (int j = 1; j < ys.size(); j++) {
//                ty += Math.abs(ys.get(j) - y);
//            }
//            total += tx * ty;
            for (int j = 1; j < xs.size(); j++) {
                if (xs.get(j) == x){
                    continue;
                }
                for (int k = 1; k < ys.size(); k++) {
                    if (ys.get(k) == y){
                        continue;
                    }
                    int t = Math.abs(xs.get(j) - x) * Math.abs(ys.get(k) - y);
                    total += t;
                }
            }
        }

        pw.println(total % 1000000007);
        pw.close();
    }

    private static ArrayList<Integer> allY (int[][] data, int x) {
        ArrayList<Integer> all = new ArrayList<>();
        for (int i = 0; i < data.length; i++){
            if (data[i][0] == x){
                all.add(data[i][1]);
            }
        }
        return all;
    }

    private static ArrayList<Integer> allX (int[][] data, int y) {
        ArrayList<Integer> all = new ArrayList<>();
        for (int i = 0; i < data.length; i++){
            if (data[i][1] == y){
                all.add(data[i][0]);
            }
        }
        return all;
    }

    // Returns index of x if it is present in arr[l..
    // r], else return -1
    private static int binarySearch(ArrayList<ArrayList<Integer>> arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid).get(0) == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr.get(mid).get(0) > x)
                return binarySearch(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }
}
