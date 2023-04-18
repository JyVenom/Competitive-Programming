import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ClosingTheFarm3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new File("closing.out"));

        String[] nM = br.readLine().split(" ");
        int n = Integer.parseInt(nM[0]);
        int m = Integer.parseInt(nM[1]);
        int[] order = new int[n];
        ArrayList<Integer>[] connections = new ArrayList[n];
        int cur = n;

        for (int i = 0; i < n; i++)
            connections[i] = new ArrayList<>();

        for (int i = 0; i < m; i++){
            String[] temp = br.readLine().split(" ");
            int p1 = Integer.parseInt(temp[0]) - 1;
            int p2 = Integer.parseInt(temp[1]) - 1;
            connections[p1].add(p2);
            connections[p2].add(p1);
        }

        for (int i = 0; i < n; i++){
            order[i] = Integer.parseInt(br.readLine()) - 1;
        }

        ArrayList<Integer> activeFarms = new ArrayList<>();
        for (int i = 0; i < n; i++){
            activeFarms.add(i);
        }

        int size = 0;
        ArrayList<Integer> used = new ArrayList<>();
        size = DFS(connections, activeFarms.get(0), activeFarms, used, size);
        if (size == n){
            out.println("YES");
        }
        else {
            out.println("NO");
        }
        for (int i = 0; i < order.length - 1; i++){
            long start = System.currentTimeMillis();
            cur--;
            int index = binarySearch1d(activeFarms, 0, activeFarms.size() - 1, order[i]);
            activeFarms.remove(index);
            size = 0;
            used = new ArrayList<>();
            size = DFS(connections, activeFarms.get(0), activeFarms, used, size);
            if (size == cur){
                out.println("YES");
            }
            else {
                out.println("NO");
            }
            long end = System.currentTimeMillis();
            if (end - start > 9){
                System.out.println(end - start);
            }
        }

        out.close();
    }

    private static int DFS (ArrayList<Integer>[] connections, int start, ArrayList<Integer> activeFarms, ArrayList<Integer> used, int size){
        size++;
        used.add(start);
        Collections.sort(used);
        for (int i = 0; i < connections[start].size(); i++){
            int other = connections[start].get(i);
            int active = binarySearch1d(activeFarms, 0, activeFarms.size() - 1, other);
            int contains = binarySearch1d(used, 0, used.size() - 1, other);
            if (active != -1 && contains == -1){
                size = DFS(connections, other, activeFarms, used, size);
            }
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
}
