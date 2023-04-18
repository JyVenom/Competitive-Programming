import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ConventionII3 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("convention2.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("convention2.out")); //or what it calls for ("promote.out)

        int cows = sc.nextInt();
        int[][] data = new int[cows][3];

        for (int i = 0; i < cows; i++){
            data[i][0] = i + 1;
            data[i][1] = sc.nextInt();
            data[i][2] = sc.nextInt();
        }
        Arrays.sort(data, Comparator.comparingInt(a -> a[1]));

        int maxWait = 0;
        int time = data[0][1] + data[0][2];
        int[][] temp = new int[data.length - 1][3];
        System.arraycopy(data, 1, temp, 0, data.length - 1);
        data = temp;

        ArrayList<int[]> times = new ArrayList<>();
        while (data.length > 0) {
            long startTime = System.nanoTime();
            if (times.size() > 0) {
                for (int i = times.size(); i < data.length; i++) {
                    if (data[i][1] < time) {
                        times.add(data[i]);
                    } else {
                        break;
                    }
                }
            }
            else {
                times.add(data[0]);
            }
            times.sort(Comparator.comparingInt(a->a[0]));
            maxWait = Math.max(maxWait, time - times.get(0)[1]);
            if (times.get(0)[1] > time){
                time = times.get(0)[1] + times.get(0)[2];
            }
            else {
                time += times.get(0)[2];
            }
            temp = new int[data.length - 1][3];
            int correct = 0;
            for (int i = 0; i < data.length; i++){
                if (data[i][0] == times.get(0)[0]){
                    correct = -1;
                    continue;
                }
                temp[i + correct] = data[i];
            }
            data = temp;
            times.remove(0);
            long endTime = System.nanoTime();
            System.out.println(endTime - startTime);
        }

        out.println(maxWait);
        out.close();
    }
}
