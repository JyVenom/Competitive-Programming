import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BlockedBillboardII {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("billboard.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("billboard.out")); //or what it calls for ("promote.out")

        int[] lawnmower = new int[4];
        int[] cowFeed = new int[4];

        for (int i = 0; i < 4; i++){
            lawnmower[i] = sc.nextInt();
        }
        for (int i = 0; i < 4; i++){
            cowFeed[i] = sc.nextInt();
        }

        boolean coversLength = cowFeed[0] <= lawnmower[0] && cowFeed[2] >= lawnmower[2];
        boolean coversWidth = cowFeed[1] <= lawnmower[1] && cowFeed[3] >= lawnmower[3];
        if (coversLength && coversWidth){
            out.println(0);
        }else if (coversWidth){ //if cow feed billboard completely covers one side of the lawnmower billboard (so that all that is left is ONE RECTANGLE)
            if (cowFeed[0] <= lawnmower[0]){ //covers beginning
                int length = lawnmower[2] - cowFeed[2];
                int width = lawnmower[3] - lawnmower[1];
                out.println(length * width);
            }else if (cowFeed[2] >= lawnmower[2]){ //covers end
                int length = cowFeed[0] - lawnmower[0];
                int width = lawnmower[3] - lawnmower[1];
                out.println(length * width);
            }else {
                int length = lawnmower[2] - lawnmower[0];
                int width = lawnmower[3] - lawnmower[1];
                out.println(length * width);
            }
        }else if (coversLength){ //if cow feed billboard completely covers one side of the lawnmower billboard (so that all that is left is ONE RECTANGLE)
            if (cowFeed[3] >= lawnmower[3]){ //covers top
                int length = lawnmower[2] - lawnmower[0];
                int width = cowFeed[1] - lawnmower[1];
                out.println(length * width);
            }else if (cowFeed[1] <= lawnmower[1]){ //covers bottom
                int length = lawnmower[2] - lawnmower[0];
                int width = lawnmower[3] - cowFeed[3];
                out.println(length * width);
            }else {
                int length = lawnmower[2] - lawnmower[0];
                int width = lawnmower[3] - lawnmower[1];
                out.println(length * width);
            }
        }else {
            int length = lawnmower[2] - lawnmower[0];
            int width = lawnmower[3] - lawnmower[1];
            out.println(length * width);
        }
        out.close(); //VERY IMPORTANT!!!
    }
}
