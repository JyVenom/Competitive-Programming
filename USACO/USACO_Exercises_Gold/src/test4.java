import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class test4 {
    public static void main(String[] args) throws IOException {
        int n = 10000;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowmbat.in")));
        pw.println(n + " " + " 26 2");
        pw.println("a".repeat(n));
        String temp = "0" + " 0".repeat(25);
        for (int i = 0; i < 26; i++) {
            pw.println(temp);
        }
        pw.close();
    }
}
