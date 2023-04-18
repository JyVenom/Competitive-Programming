/*
ID: jerryya2
LANG: JAVA
TASK: fracdec
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class fracdec {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fracdec.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int d = Integer.parseInt(line[1]);

        StringBuilder dec = new StringBuilder(n / d + ".");
        int init = dec.length();
        int rem = (n % d) * 10;
        if (rem == 0) {
            dec.append("0");
        } else {
            boolean rep = true;
            HashSet<Integer> used = new HashSet<>();
            ArrayList<int[]> used2 = new ArrayList<>();
            int count = 0;
            while (!used.contains(rem)) {
                used.add(rem);
                used2.add(new int[]{count, rem});
                count++;
                dec.append(rem / d);
                rem = (rem % d) * 10;
                if (rem == 0) {
                    rep = false;
                    break;
                }
            }
            if (rep) {
                used2.sort(Comparator.comparingInt(a -> a[1]));
                int pos = binarySearch(used2, 0, used2.size() - 1, rem);
                dec.insert(used2.get(pos)[0] + init, "(");
                dec.append(")");
            }
        }

        if (dec.length() > 76) {
            while (dec.length() > 76) {
                pw.println(dec.substring(0, 76));
                dec.delete(0, 76);
            }
            if (dec.length() > 0) {
                pw.println(dec.toString());
            }
        }
        else {
            pw.println(dec.toString());
        }
        pw.close();
    }

    public static int binarySearch(ArrayList<int[]> arr, int first, int last, int key) {
        if (last >= first) {
            int mid = first + (last - first) / 2;
            if (arr.get(mid)[1] == key) {
                return mid;
            }
            if (arr.get(mid)[1] > key) {
                return binarySearch(arr, first, mid - 1, key);//search in left sub array
            } else {
                return binarySearch(arr, mid + 1, last, key);//search in right sub array
            }
        }
        return -1;
    }
}
