import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BackAndForth {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("backforth.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("backforth.out")); //or what it calls for ("promote.out")

        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        buckets.add(new ArrayList<>(1));
        buckets.add(new ArrayList<>(1));
        int next;
        for (int i = 0; i < 10; i++){
            next = sc.nextInt();
            if (!buckets.get(0).contains(next)) {
                buckets.get(0).add(next);
            }
        }
        for (int i = 0; i < 10; i++){
            next = sc.nextInt();
            if (!buckets.get(1).contains(next)) {
                buckets.get(1).add(next);
            }
        }


        out.close();
    }
}
