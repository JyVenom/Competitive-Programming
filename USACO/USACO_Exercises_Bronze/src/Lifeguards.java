import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Lifeguards {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("lifeguards.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("lifeguards.out")); //or what it calls for ("promote.out")

        int cows = sc.nextInt();
        int[][] data = new int[cows][2];

        for (int i = 0; i < cows; i++){
            data[i][0] = sc.nextInt();
            data[i][1] = sc.nextInt();
        }

        int max = Integer.MIN_VALUE;
        ArrayList<Integer> covered = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            covered.add(0);
        }
        for (int omit = 0; omit < cows; omit++){
            for (int i = 0; i < cows; i++){
                if (i == omit){
                    continue;
                }
                for (int j = data[i][0]; j < data[i][1]; j++){
                    covered.set(j - 1, 1);
                }
            }
            int sum = 0;
            for (int i = 0; i < covered.size(); i++){
                sum += covered.get(i);
            }
            max = Math.max(max, sum);
            for (int i = 0; i < 1000; i++){
                covered.set(i, 0);
            }
        }

        out.println(max);
        out.close();
    }
}
