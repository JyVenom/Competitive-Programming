import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ConventionII5 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("convention2.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("convention2.out")); //or what it calls for ("promote.out)
        int cows = sc.nextInt();
        ArrayList<int[]> data = new ArrayList<>(cows);
        for (int i = 0; i < cows; i++) {
            data.add(new int[3]);
        }

        for (int i = 0; i < cows; i++) {
            data.get(i)[0] = i + 1;
            data.get(i)[1] = sc.nextInt();
            data.get(i)[2] = sc.nextInt();
        }
        data.sort(Comparator.comparingInt(a -> a[1]));

        int maxWait = 0;
        int time = data.get(0)[1] + data.get(0)[2];
        data.remove(0);
        int smallest;
        for (int i = 0; i < cows - 1; i++) {
            smallest = 0;
            for (int j = 0; j < data.size(); j++) {
                if (data.get(j)[1] < time) {
                    if (data.get(j)[0] < data.get(smallest)[0]) {
                        smallest = j;
                    }
                } else {
                    break;
                }
            }
            maxWait = Math.max(maxWait, time - data.get(smallest)[1]);
            if (data.get(smallest)[1] > time) {
                time = data.get(smallest)[1] + data.get(smallest)[2];
            } else {
                time += data.get(smallest)[2];
            }
            data.remove(smallest);
        }
        out.println(maxWait);
        out.close();
    }
}
