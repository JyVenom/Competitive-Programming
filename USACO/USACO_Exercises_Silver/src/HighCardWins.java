import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class HighCardWins {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));

        int n = Integer.parseInt(br.readLine());
        HashSet<Integer> elsie2 = new HashSet<>();
        for (int i = 0; i < n; i++) {
            elsie2.add(Integer.parseInt(br.readLine()) - 1);
        }

        ArrayList<Integer> elsie = new ArrayList<>(elsie2);
        Collections.sort(elsie);
        ArrayList<Integer> bessie = new ArrayList<>();
        int n2 = 2 * n;
        for (int i = 0; i < n2; i++) {
            if (elsie2.contains(i)) {
                continue;
            }

            bessie.add(i);
        }

        int low = 0;
        int high = n - 1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int res = binSearch(bessie, elsie.get(i), low, high);
            if (res == n) {
                break;
            }
            else if (res == high) {
                count++;
                break;
            }
            else {
                low = res + 1;
                count++;
            }
        }

        pw.println(count);
        pw.close();
    }

    private static int binSearch(ArrayList<Integer> arr, int key, int low, int high) {
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            } else if (arr.get(mid) == key) {
                index = mid;
                break;
            }
        }
        if (index == Integer.MAX_VALUE) {
            return low;
        }
        else {
            return index;
        }
    }
}
