import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class test7 {
    public static void main(String[] args) throws IOException {
        int n = 10000;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("time.in")));
        pw.println("1000 1998 1");
        pw.println("1000" + " 1000".repeat(999));
        for (int i = 2; i <= 1000; i++) {
            pw.println("1 " + i);
            pw.println(i + " 1");
        }
        pw.close();
    }
}
