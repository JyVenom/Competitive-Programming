import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Photoshoot {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("photo.in"));
        PrintWriter out = new PrintWriter(new File("photo.out"));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] b = new int[n];
        for (int i = 0; i < n - 1; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int[] order = new int[n];
        ArrayList<Integer> used = new ArrayList<>();
        for (int i = 1; i < n; i++){
            order[0] = i;
            used.add(i);
            boolean done = true;
            for (int j = 1; j < n; j++){
                int now = b[j - 1] - order[j - 1];
                if (now > n){
                    order = new int[n];
                    used = new ArrayList<>();
                    done = false;
                    break;
                }
                if (binarySearch(used, now) == -1){
                    order[j] = now;
                    used.add(now);
                    Collections.sort(used);
                }
                else {
                    order = new int[n];
                    used = new ArrayList<>();
                    done = false;
                    break;
                }
            }
            if (done){
                break;
            }
        }
        for (int i = 0; i < n - 1; i++){
            out.print(order[i] + " ");
        }
        out.println(order[n - 1]);
        out.close();
    }

    private static int binarySearch(ArrayList<Integer> arr, int x) {
        int l = 0, r = arr.size()- 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr.get(m) == x)
                return m;

            // If x greater, ignore left half
            if (arr.get(m) < x)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }
}
