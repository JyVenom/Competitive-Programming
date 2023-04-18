import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class RandomGenerator {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("random.out")));
        int n = Integer.parseInt(args[0]);
        int r = 100;

        ArrayList<int[]> all = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            all.add(new int[]{(int) (Math.random() * r), (int) (Math.random() * r)});
        }
        for (int[] cur : all) {
            pw.println(cur[0] + " " + cur[1]);
        }
        pw.close();

//        HashSet<Integer> all = new HashSet<>();
//        while (all.size() < n) {
//            all.add((int) (Math.random() * 100));
//        }
//
//        System.out.println(n);
//        ArrayList<Integer> copy = new ArrayList<>(all);
//        for (int num : copy) {
//            System.out.println(num);
//        }
    }
}
