import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MeasuringTraffic3 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("traffic.in")); //or what it calls for ("promote.in")
        PrintWriter output = new PrintWriter(new File("traffic.out")); //or what it calls for ("promote.out")

        int[] noneRange = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        int lastIndexOfNone = Integer.MAX_VALUE;
        int firstIndexOfNone = Integer.MIN_VALUE;
        int lines = Integer.parseInt(sc.nextLine());
        String[][] data = new String[lines][3];
        for (int i = 0; i < lines; i++){
            String line = sc.nextLine();
            data[i][0] = line.substring(0, line.indexOf(' '));
            data[i][1] = line.substring(line.indexOf(' ')+1,line.indexOf(' ', line.indexOf(' ')+1));
            data[i][2] = line.substring(line.indexOf(' ', line.indexOf(' ')+1) + 1);
        }
        for (int i = lines - 1; i >= 0; i--){
            if (data[i][0].equals("none")){
                lastIndexOfNone = i;
                noneRange[0] = Integer.parseInt(data[i][1]);
                noneRange[1] = Integer.parseInt(data[i][2]);
                break;
            }
        }
        for (int i = lastIndexOfNone; i >= 0; i--){
            if (data[i][0].equals("on")){
                noneRange[0] = Math.max(noneRange[0] - Integer.parseInt(data[i][2]), 0);
                noneRange[1] = Math.max(noneRange[1] - Integer.parseInt(data[i][1]), 0);
            }
            else if (data[i][0].equals("off")){
                noneRange[0] = noneRange[0] + Integer.parseInt(data[i][1]);
                noneRange[1] = noneRange[1] + Integer.parseInt(data[i][2]);
            }
            else {
                if (Integer.parseInt(data[i][1]) > noneRange[0]){
                    noneRange[0] = Integer.parseInt(data[i][1]);
                }
                if (Integer.parseInt(data[i][2]) < noneRange[1]){
                    noneRange[1] = Integer.parseInt(data[i][2]);
                }
            }
        }
        output.println(noneRange[0] + " " + noneRange[1]);

//        useless since noneRange is changed on lines 57 and 58
//        noneRange[0] = Integer.MIN_VALUE;
//        noneRange[1] = Integer.MAX_VALUE;
        for (int i = 0; i < lines; i++){
            if (data[i][0].equals("none")){
                firstIndexOfNone = i;
                noneRange[0] = Integer.parseInt(data[i][1]);
                noneRange[1] = Integer.parseInt(data[i][2]);
                break;
            }
        }
        for (int i = firstIndexOfNone; i < lines; i++){
            if (data[i][0].equals("on")){
                noneRange[0] = noneRange[0] + Integer.parseInt(data[i][1]);
                noneRange[1] = noneRange[1] + Integer.parseInt(data[i][2]);
            }
            else if (data[i][0].equals("off")){
                noneRange[0] = Math.max(noneRange[0] - Integer.parseInt(data[i][2]), 0);
                noneRange[1] = Math.max(noneRange[1] - Integer.parseInt(data[i][1]), 0);
            }
            else {
                if (Integer.parseInt(data[i][1]) > noneRange[0]){
                    noneRange[0] = Integer.parseInt(data[i][1]);
                }
                if (Integer.parseInt(data[i][2]) < noneRange[1]){
                    noneRange[1] = Integer.parseInt(data[i][2]);
                }
            }
        }
        output.println(noneRange[0] + " " + noneRange[1]);
        output.close(); //VERY IMPORTANT!!!
    }
}
