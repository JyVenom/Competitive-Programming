import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class RandomGenerator {
    public static void main(String[] args) throws IOException {
        int n = 10000, m = 5000;

        ArrayList<Integer> a = new ArrayList<>(n);
        ArrayList<Integer> b = new ArrayList<>(m);

        for (int i = 1; i <= n; i++) {
            a.add(i);
        }
        for (int i = 1; i <= m; i++) {
            b.add(i);
        }

        Collections.shuffle(a);
        Collections.shuffle(b);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
        pw.println(n + " " + m);
        for (int i = 0; i < n; i++) {
            pw.println(a.get(i));
        }
        for (int i = 0; i < m; i++) {
            pw.println(b.get(i));
        }
        pw.close();
    }
}
