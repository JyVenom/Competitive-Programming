/*
ID: jerryya2
LANG: JAVA
TASK: nuggets
*/

import java.io.*;
import java.util.ArrayList;

public class nuggets {
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
        int max = 67000;
//        int max = 0;
//        for (int i = 0; i < sizes.size(); i++) {
//            int M = sizes.get(i);
//            for (int j = i + 1; j < sizes.size(); j++) {
//                int N = sizes.get(j);
//                max = Math.max(max, (M * N) - M - N);
//            }
//        }
        boolean[] pos = new boolean[max + 1];
        for (int size : sizes) {
            pos[size] = true;
        }
        for (int i = 0; i < max; i++) {
            boolean cur = pos[i];
            if (cur) {
                for (int size : sizes) {
                    int next = i + size;
                    if (next > max) {
                        break;
                    }
                    pos[next] = true;
                }
            }
        }

        for (int i = max - 500; i >= 0; i--) {
            if (!pos[i]) {
                if (i < max - 1000) {
                    pw.println(i);
                } else {
                    pw.println(0);
                }
                break;
            }
        }
        pw.close();
    }
}
