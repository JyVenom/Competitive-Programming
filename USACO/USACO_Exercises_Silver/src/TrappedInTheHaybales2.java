import java.util.*;
import java.io.*;

public class TrappedInTheHaybales2 {
    private static int n;
    private static int bessiePos;
    private static pair[] bales;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("trapped.in"));
        PrintWriter out = new PrintWriter(new File("trapped.out"));

        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        bessiePos = Integer.parseInt(line[1]);

        // Read in the hay bales.
        bales = new pair[n];
        for (int i = 0; i < n; i++){
            line = br.readLine().split(" ");
            int size = Integer.parseInt(line[0]);
            int pos = Integer.parseInt(line[1]);
            bales[i] = new pair(size, pos);
        }

        // Solve and write out the result.
        out.println(solve());
        out.close();
    }

    public static int solve() {
        Arrays.sort(bales);
        int index = binarySearch(bales, 0, bales.length - 1, bessiePos);
        if (index == 0 || index == n) return -1;

        int leftIndex = index - 1, rightIndex = index, min = Integer.MAX_VALUE;
        int tempLeft = leftIndex - 1, tempRight = rightIndex + 1;
        while (leftIndex >= 0 && rightIndex < n) {
            int maxEnergy = bales[rightIndex].pos - bales[leftIndex].pos;
            if (bales[leftIndex].size >= maxEnergy && bales[rightIndex].size >= maxEnergy) return 0;
            else if (bales[leftIndex].size >= maxEnergy && bales[rightIndex].size < maxEnergy) {
                min = Math.min(min, maxEnergy - bales[rightIndex].size);
                rightIndex++;
            }
            else if (bales[leftIndex].size < maxEnergy && bales[rightIndex].size >= maxEnergy) {
                min = Math.min(min, maxEnergy - bales[leftIndex].size);
                leftIndex--;
            }
            else {
                while (tempLeft >= 0 && bales[tempLeft].size < (bales[rightIndex].compareTo(bales[tempLeft]))) tempLeft--;
                while (tempRight < n && bales[tempRight].size < (bales[tempRight].compareTo(bales[leftIndex]))) tempRight++;

                if (tempLeft < 0 && tempRight == n) {
                    if (min == Integer.MAX_VALUE) return -1;
                    return min;
                }

                if (tempLeft >= 0)
                    min = Math.min(min, bales[rightIndex].pos - bales[tempLeft].pos - bales[rightIndex].size);

                if (tempRight < n)
                    min = Math.min(min, bales[tempRight].pos - bales[leftIndex].pos - bales[leftIndex].size);

                // We can update both now.
                leftIndex--;
                rightIndex++;
            }
        }

        if (min == Integer.MAX_VALUE) return -1;
        return min;
    }

    static class pair implements Comparable<pair> {

        public int size;
        public int pos;

        public pair(int s, int p) {
            size = s;
            pos = p;
        }

        @Override
        public int compareTo(pair other) {
            return this.pos - other.pos;
        }
    }

    private static int binarySearch(pair[] data, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (data[mid].pos == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in leftIndex sub array
            if (data[mid].pos > x)
                return binarySearch(data, l, mid - 1, x);

            // Else the element can only be present
            // in rightIndex sub array
            return binarySearch(data, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return l;
    }
}

