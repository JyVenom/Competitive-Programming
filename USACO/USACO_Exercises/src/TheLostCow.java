import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TheLostCow {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("lostcow.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("lostcow.out")); //or what it calls for ("promote.out")

        int x = sc.nextInt();
        int y = sc.nextInt();

        int dist = 1;
        int count = 0;
        boolean done = false;
        while (!done) {
            for (int i = 0; i < (dist + dist / 2); i++) {
                if (x == y){
                    done = true;
                    break;
                }
                x += 1;
                count++;
            }
            if (!done) {
                dist *= 2;
                for (int i = 0; i < (dist + dist / 2); i++) {
                    if (x == y) {
                        done = true;
                        break;
                    }
                    x -= 1;
                    count++;
                }
                dist *= 2;
            }
        }

        out.println(count);
        out.close();
    }
}
