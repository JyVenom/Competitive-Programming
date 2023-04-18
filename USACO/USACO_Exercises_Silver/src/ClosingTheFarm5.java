import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ClosingTheFarm5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new File("closing.out"));

        String[] nM = br.readLine().split(" ");
        int n = Integer.parseInt(nM[0]);
        int m = Integer.parseInt(nM[1]);
        int[] order = new int[n];
        ArrayList<Integer>[] connections = new ArrayList[n];

        //Connections starts empty, fill it in with empty array list to call and fill in later
        for (int i = 0; i < n; i++)
            connections[i] = new ArrayList<>();

        //Fill in connections with given/input data
        for (int i = 0; i < m; i++){
            String[] temp = br.readLine().split(" ");
            int p1 = Integer.parseInt(temp[0]) - 1;
            int p2 = Integer.parseInt(temp[1]) - 1;
            connections[p1].add(p2);
            connections[p2].add(p1);
        }

        //Sort each row in connections
        for (int i = 0; i < n; i++){
            Collections.sort(connections[i]);
        }

        //Fill in order with given/input data
        for (int i = 0; i < n; i++){
            order[i] = Integer.parseInt(br.readLine()) - 1;
        }

        //Does it start fully connected?
        ArrayList<Integer> empty = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if (connections[i].isEmpty()){
                empty.add(i);
            }
        }
        if (empty.isEmpty()){
            out.println("YES");
        }
        else {
            out.println("NO");
        }

        for (int i = 0; i < order.length - 2; i++){
            if (i == 42){
                System.out.println();
            }
            int cur = order[i];
            ArrayList<Integer> delete = connections[cur];
            //Go through the rows that cur is present in and delete cur from them
            for (int otherRow : delete) {
                ArrayList<Integer> other = connections[otherRow];
                int index = binarySearch1d(other, 0, other.size() - 1, cur);
                other.remove(index);
                //If the row is now empty, add it to empty
                if (other.isEmpty()) {
                    empty.add(otherRow);
                    Collections.sort(empty);
                }
            }

            //Remove the element to be deleted from the array list of empty rows if it is in there
            int index = binarySearch1d(empty, 0, empty.size() - 1, cur);
            if (index != -1){
                empty.remove(index);
            }

            //If the array list of empty rows is empty, print yes, otherwise print no
            if (empty.isEmpty()){
                out.println("YES");
            }
            else {
                out.println("NO");
            }
        }
        out.println("YES");
        out.close();
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
