import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MeasuringTraffic {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("traffic.in")); //or what it calls for ("promote.in")
        PrintWriter output = new PrintWriter(new File("traffic.out")); //or what it calls for ("promote.out")

        int[] mainRoadFinal = {Integer.MIN_VALUE, Integer.MAX_VALUE};
//        int[] adjust1 = {0,0};
//        int[] adjust2 = {0,0};
//        int[] end = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        int lastIndexOfMainRoad = Integer.MIN_VALUE;
//        int inIndex = Integer.MAX_VALUE;
//        int outIndex = Integer.MAX_VALUE;
        int lines = Integer.parseInt(sc.nextLine());
        String[][] data = new String[lines][3];
        for (int i = 0; i < lines; i++){
            String line = sc.nextLine();
            data[i][0] = line.substring(0, line.indexOf(' '));
            data[i][1] = line.substring(line.indexOf(' ')+1,line.indexOf(' ', line.indexOf(' ')+1));
            data[i][2] = line.substring(line.indexOf(' ', line.indexOf(' ')+1) + 1);
        }
        for (int i = 0; i < lines; i++){
            if (data[i][0].equals("none")){
                lastIndexOfMainRoad = i;
                if (Integer.parseInt(data[i][1]) > mainRoadFinal[0]){
                    mainRoadFinal[0] = Integer.parseInt(data[i][1]);
                }
                if (Integer.parseInt(data[i][2]) < mainRoadFinal[1]){
                    mainRoadFinal[1] = Integer.parseInt(data[i][2]);
                }
            }
        }
        int[] start = {mainRoadFinal[0], mainRoadFinal[1]};
        for (int i = 0; i < lastIndexOfMainRoad; i++){
            if (data[i][0].equals("on")){
                start[0] = start[0] - Integer.parseInt(data[i][2]);
                start[1] = start[1] - Integer.parseInt(data[i][1]);
//                if (inIndex == Integer.MAX_VALUE){
//                    inIndex = i;
//                }
            }
            else if (data[i][0].equals("off")){
                start[0] = start[0] + Integer.parseInt(data[i][1]);
                start[1] = start[1] + Integer.parseInt(data[i][2]);
//                if (inIndex == Integer.MAX_VALUE){
//                    inIndex = i;
//                }
            }
        }
        int[] end = {mainRoadFinal[0], mainRoadFinal[1]};
        for (int i = lastIndexOfMainRoad; i < lines; i++){
            if (data[i][0].equals("on")){
                end[0] = end[0] + Integer.parseInt(data[i][1]);
                end[1] = end[1] + Integer.parseInt(data[i][2]);
//                if (inIndex == Integer.MAX_VALUE){
//                    inIndex = i;
//                }
            }
            else if (data[i][0].equals("off")){
                end[0] = end[0] - Integer.parseInt(data[i][2]);
                end[1] = end[1] - Integer.parseInt(data[i][1]);
//                if (inIndex == Integer.MAX_VALUE){
//                    inIndex = i;
//                }
            }
        }
        output.println(start[0] + " " + start[1]);
        output.println(end[0] + " " + end[1]);
        output.close(); //VERY IMPORTANT!!!
    }
}
