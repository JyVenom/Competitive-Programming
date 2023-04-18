import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class TrappedInTheHaybales {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("trapped.in"));
        PrintWriter out = new PrintWriter(new File("trapped.out"));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int pos = Integer.parseInt(line[1]);
        ArrayList<ArrayList<Integer>> data = new ArrayList<>(n);

        for (int i = 0; i < n; i++){
            line = br.readLine().split(" ");
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(Integer.parseInt(line[0]));
            temp.add(Integer.parseInt(line[1]));
            data.add(temp);
        }
        data.sort(Comparator.comparing(a -> a.get(1)));

        int energy = 0;

        out.close();
    }

    private static int binarySearch2d(ArrayList<ArrayList<Integer>> arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid).get(1) == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub array
            if (arr.get(mid).get(1) > x)
                return binarySearch2d(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right sub array
            return binarySearch2d(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return r;
    }
}

