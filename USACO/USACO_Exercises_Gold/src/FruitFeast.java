import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FruitFeast {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("feast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        boolean[] pos = new boolean[t + 1];
        ArrayList<Integer> pos2 = new ArrayList<>();
        pos[0] = true;
        for (int i = 0; i <= t; i++) {
            if (pos[i]) {
                pos2.add(i);
                int sa = i + a;
                int sb = i + b;
                if (sa <= t) {
                    pos[sa] = true;
                }
                if (sb <= t) {
                    pos[sb] = true;
                }
            }
        }
        pos2.remove(0);
        int high = pos2.size() - 1;
        int max = pos2.get(high);
        if (max != t) {
            for (int first : pos2) {
                int half = first / 2;
                int rem = t - half;
                int temp;
                if (rem >= pos2.get(high)) {
                    temp = high;
                } else {
                    temp = binSearch(pos2, rem, high);
                }
                if (temp >= 0) {
                    int sum = half + pos2.get(temp);
                    if (sum > max) {
                        max = sum;
                    }
                    if (max == t) {
                        break;
                    }
                }
            }
        }

        pw.println(max);
        pw.close();
    }

    private static int binSearch(ArrayList<Integer> arr, int key, int high) {
        int low = 0;
        int max = high;
        int index = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) == key) {
                index = mid;
                break;
            } else if (arr.get(mid) < key && arr.get(Math.min(max, mid + 1)) > key) {
                index = mid;
                break;
            } else if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            }
        }
        return index;
    }
}
