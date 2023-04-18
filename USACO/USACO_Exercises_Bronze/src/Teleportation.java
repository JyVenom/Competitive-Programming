import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Teleportation {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("teleport.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("teleport.out")); //or what it calls for ("promote.out")

        int start = sc.nextInt();
        int end = sc.nextInt();
        int teleportX = sc.nextInt();
        int teleportY = sc.nextInt();
        int useTeleporter = 0;
        int towWithTractor = Math.abs(start - end);

        useTeleporter += Math.min(Math.abs(start - teleportX), Math.abs(start - teleportY));
        useTeleporter += Math.min(Math.abs(end - teleportX), Math.abs(end - teleportY));

        out.println(Math.min(useTeleporter, towWithTractor));
        out.close();
    }
}
