import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WhereAmI {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("whereami.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("whereami.out")); //or what it calls for ("promote.out)

        int n = Integer.parseInt(sc.nextLine());
        String road = sc.nextLine();

        int min = 100;
        for (int k = 0; k < n; k++){
            boolean good = true;
            for (int i = 2; i <= n - k; i++){
                String part = road.substring(i, i + k);
                if (road.indexOf(part) != road.lastIndexOf(part)){
                    good = false;
                    break;
                }
            }
            if (good){
                min = Math.min(min, k);
                break;
            }
        }

        out.println(min);
        out.close();
    }
}
