import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BovineShuffle {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("shuffle.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("shuffle.out")); //or what it calls for ("promote.out")

        int cows = Integer.parseInt(sc.nextLine());
        int[] pos = new int[cows];
        int[] data = new int[cows];
        int prevIndex = -1;
        String line = sc.nextLine();
        for (int i = 0; i < cows-1; i++){
            pos[i] = Integer.parseInt(line.substring(prevIndex + 1, line.indexOf(' ', prevIndex + 1)));
            prevIndex = line.indexOf(' ', prevIndex + 1);
        }
        pos[cows - 1] = Integer.parseInt(line.substring(prevIndex + 1));
        prevIndex = -1;
        line = sc.nextLine();
        ArrayList<Integer> Final = new ArrayList<>(cows);
        for (int i = 0; i < cows - 1; i++){
            data[i] = Integer.parseInt(line.substring(prevIndex + 1, line.indexOf(' ', prevIndex + 1)));
            prevIndex = line.indexOf(' ', prevIndex + 1);
        }
        data[cows - 1] = Integer.parseInt(line.substring(prevIndex + 1));
        for (int i = 0; i < cows; i++){
            Final.add(data[i]);
        }
        for (int i = 0; i < 3; i++) {
            ArrayList<Integer> temp = new ArrayList<>(cows);
            for (int j = 0; j < cows; j++){
                temp.add(Final.get(j));
            }
            for (int j = 0; j < cows; j++) {
                Final.set(j, temp.get(pos[j] - 1));
            }
        }
        for (int i = 0; i < cows; i++){
            out.println(Final.get(i));
        }
        out.close();
    }
}
