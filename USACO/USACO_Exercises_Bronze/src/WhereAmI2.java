import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WhereAmI2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("whereami.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("whereami.out")); //or what it calls for ("promote.out)

        int n = Integer.parseInt(sc.nextLine());
        String road = sc.nextLine();

        int min = 100;
        for (int i = 1; i <= n; i++){
            String part = road.substring(0, i);
            if (road.indexOf(part) == road.lastIndexOf(part)){
                min = Math.min(min, i);
                break;
            }
        }

        out.println(min);
        out.close();
    }
}
