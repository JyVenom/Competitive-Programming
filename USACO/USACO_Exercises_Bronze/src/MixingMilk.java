import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MixingMilk {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("mixmilk.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("mixmilk.out")); //or what it calls for ("promote.out")

        int[][] data = new int[3][2];
        String line;
        for (int i = 0; i < 3; i++){
            line = sc.nextLine();
            data[i][0] = Integer.parseInt(line.substring(0,line.indexOf(' ')));
            data[i][1] = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        }

        for (int i = 0; i < 33; i++){
            for (int j = 0; j < 3; j++){
                int remaining = data[(j + 1)%3][0] - data[(j+1)%3][1];
                if (data[j][1] < remaining){
                    remaining = data[j][1];
                }
                data[j][1] -= remaining;
                data[(j + 1)%3][1] += remaining;
            }
        }
        int remaining = data[1][0] - data[1][1];
        if (data[0][1] < remaining){
            remaining = data[0][1];
        }
        data[0][1] -= remaining;
        data[1][1] += remaining;

        for (int i = 0; i < 3; i++){
            out.println(data[i][1]);
        }
        out.close();
    }
}
