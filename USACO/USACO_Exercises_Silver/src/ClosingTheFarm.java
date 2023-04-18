import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ClosingTheFarm {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new File("closing.out"));

        String[] nM = br.readLine().split(" ");
        int n = Integer.parseInt(nM[0]);
        int m = Integer.parseInt(nM[1]);
        int[] order = new int[n];
        int[][] connections = new int[m][2];
        int cur = n;

        for (int i = 0; i < m; i++){
            String[] temp = br.readLine().split(" ");
            int p1 = Integer.parseInt(temp[0]);
            int p2 = Integer.parseInt(temp[1]);
            if (p1 < p2){
                connections[i][0] = p1;
                connections[i][1] = p2;
            }
            else {
                connections[i][0] = p2;
                connections[i][1] = p1;
            }
        }
        Arrays.sort(connections, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(connections, Comparator.comparingInt(arr -> arr[0]));

        for (int i = 0; i < n; i++){
            order[i] = Integer.parseInt(br.readLine());
        }

        ArrayList<Integer> activeFarms = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            activeFarms.add(i);
        }
        int size = 0;
        boolean[] visited = new boolean[n];
        size = DFS(connections, activeFarms.get(0), activeFarms, visited, size);
        if (size == n){
            out.println("YES");
        }
        else {
            out.println("NO");
        }
        for (int i = 0; i < order.length - 1; i++){
            cur--;
            int index = binarySearch1d(activeFarms, 0, activeFarms.size() - 1, order[i]);
            activeFarms.remove(index);
            size = 0;
            visited = new boolean[n];
            size = DFS(connections, activeFarms.get(0), activeFarms, visited, size);
            if (size == cur){
                out.println("YES");
            }
            else {
                out.println("NO");
            }
        }

        out.close();
    }

    private static int DFS (int[][] connections, int start, ArrayList<Integer> activeFarms, boolean[] visited, int size){
        size++;
        visited[start] = true;
        int index = binarySearch2d(connections, 0, connections.length - 1, start);
        while (index != -1 && index < connections.length && connections[index][0] == start) {
            int other = connections[index][1];
            boolean active = binarySearch1d(activeFarms, 0, activeFarms.size() - 1, other) != -1;
            if (active && !visited[other]){
                size = DFS(connections, other, activeFarms, visited, size);
            }
            index++;
        }
        return size;
    }

    private static int binarySearch1d(ArrayList<Integer> arr, int l, int r, int x) {
        if (r >= l) {
//            if (step == 3) {
//                count++; //Jerry
//            }
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid) == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub array
            if (arr.get(mid) > x)
                return binarySearch1d(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right sub array
            return binarySearch1d(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    private static int binarySearch2d(int[][] arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr[mid][0] == x && (mid == 0 || arr[mid - 1][0] < x))
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub array
            if (arr[mid][0] > x || arr[mid][0] == x)
                return binarySearch2d(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right sub array
            return binarySearch2d(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }
}
