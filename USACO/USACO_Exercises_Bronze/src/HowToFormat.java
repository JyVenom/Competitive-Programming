import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HowToFormat {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("promote.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("promote.out")); //or what it calls for ("promote.out")

//        = sc.nextLine();
//        = sc.nextInt();
//        = Integer.parseInt(sc.nextLine());
//
//        for (int i = 0; i < ; i++){
//            data[i][] =
//        }


        out.close(); //VERY IMPORTANT!!!
    }
}
