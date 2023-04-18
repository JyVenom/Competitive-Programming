/*
ID: jerryya2
LANG: JAVA
TASK: nuggets
*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class nuggets2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("nuggets.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sizes.add(Integer.parseInt(br.readLine()));
        }

        for (int i = 0; i < sizes.size(); i++) {
            int cur = sizes.get(i);
            for (int j = i + 1; j < sizes.size(); j++) {
                if (sizes.get(j) % cur == 0) {
                    sizes.remove(j);
                    j--;
                }
            }
        }
//        int[] possible = new int[2000000000];
        HashSet<Integer> pos = new HashSet<>();
        for (int cur : sizes) {
            int temp = cur;
            while (temp < 2000000001) {
                pos.add(temp);
                temp += cur;
            }
        }

        for (int i = 2000000000; i >= 0; i--) {
            if (!pos.contains(i)) {
                pw.println(i);
                break;
            }
        }
        pw.close();
    }
}
