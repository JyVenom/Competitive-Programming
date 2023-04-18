import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Milking_Factory {
    public static void main(String[] args)  throws IOException{
        Scanner sc = new Scanner(new File("factory.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("factory.out")); //or what it calls for ("promote.out")

        int numberOfStations = Integer.parseInt(sc.nextLine());

        int[][] conveyorBelts = new int[numberOfStations - 1][2];

        for (int i = 0; i < numberOfStations - 1; i++){
            String line = sc.nextLine();
            conveyorBelts[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            conveyorBelts[i][1] = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        }

        boolean hasPrinted = false;
//        i represents the common station
        for (int i = 1; i <= numberOfStations; i++){
            boolean good = true;
//            this is a check that every station can get to station i
            for (int j = 1; j <= numberOfStations; j++){
                int currentStation = j;
                boolean hasRun = false;
                while (currentStation != i){
                    if (currentStation == j && hasRun){
                        break;
                    }
                    hasRun = true;
                    boolean hasNotChanged = true;
                    for (int k = 0; k < conveyorBelts.length; k++){
                        if (conveyorBelts[k][0] == currentStation){
                            currentStation = conveyorBelts[k][1];
                            hasNotChanged = false;
                        }
                    }
                    if (hasNotChanged){
                        break;
                    }
                }
                if (currentStation != i){
                    good = false;
                    break;
                }
            }
            if (good){
                out.println(i);
                hasPrinted = true;
                break;
            }
        }

        if (!hasPrinted){
            out.println(-1);
        }
        //INPUT
        //sc.nextLine();
        //sc.nextInt();
        //sc.next();

        //OUTPUT
        //out.println();
        //out.print();

        out.close(); //VERY IMPORTANT!!!
    }
}
