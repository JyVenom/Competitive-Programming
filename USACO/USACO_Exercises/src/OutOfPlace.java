import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OutOfPlace {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("outofplace.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("outofplace.out")); //or what it calls for ("promote.out")

        int cows = sc.nextInt();
        ArrayList<Integer> currentOrder = new ArrayList<>(cows);
        ArrayList<Integer> sorted = new ArrayList<>(cows);

        for (int i = 0; i < cows; i++){
            currentOrder.add(sc.nextInt());
            sorted.add(currentOrder.get(i));
        }
        Collections.sort(sorted);
        int swaps = -1;
        for(int i = 0; i < cows; i++) {
            if(!sorted.get(i).equals(currentOrder.get(i))) {
                swaps++;
            }
        }
        swaps = Math.max(0, swaps);
        out.println(swaps);
        out.close();
    }
}
