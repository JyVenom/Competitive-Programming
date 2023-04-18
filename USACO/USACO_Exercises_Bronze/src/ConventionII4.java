import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ConventionII4 {
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
        int smallest;
        int first;
        for (int i = 0; i < cows - 1; i++){
            smallest = 0;
            first = -1;
            for (int j = 0; j < data.length; j++) {
                if (data[j][0] == -1){
                    continue;
                }
                if (first == -1){
                    first = j;
                }
                if (data[j][1] < time) {
                    if (data[j][0] < data[smallest][0]){
                        smallest = j;
                    }
                } else {
                    break;
                }
            }
            if (smallest == 0 && data[smallest][0] == -1){
                smallest = first;
            }
            maxWait = Math.max(maxWait, time - data[smallest][1]);
            if (data[smallest][1] > time){
                time = data[smallest][1] + data[smallest][2];
            }
            else {
                time += data[smallest][2];
            }
            data[smallest][0] = -1;
        }
        out.println(maxWait);
        out.close();
    }
}
